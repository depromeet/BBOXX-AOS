package com.depromeet.bboxx.util;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

/**
 * A class which reads audio input from the mic in a background thread and
 * passes it to the caller when ready.
 * <p>
 * To use this class, your application must have permission RECORD_AUDIO.
 */
public class AudioReaderJava {
    // ******************************************************************** //
    // Public Classes.
    // ******************************************************************** //

    /**
     * Listener for audio reads.
     */
    public static abstract class Listener {
        /**
         * Audio read error code: no error.
         */
        public static final int ERR_OK = 0;

        /**
         * Audio read error code: the audio reader failed to initialise.
         */
        public static final int ERR_INIT_FAILED = 1;

        /**
         * Audio read error code: an audio read failed.
         */
        public static final int ERR_READ_FAILED = 2;

        /**
         * An audio read has completed.
         *
         * @param
         */
        public abstract void onReadComplete(int decibel);

        /**
         * An error has occurred. The reader has been terminated.
         *
         * @param error ERR_XXX code describing the error.
         */
        public abstract void onReadError(int error);
    }

    public int calculatePowerDb(short[] sdata, int off, int samples) {
        double sum = 0;
        double sqsum = 0;
        for (int i = 0; i < samples; i++) {
            final long v = sdata[off + i];
            sum += v;
            sqsum += v * v;
        }

        // sqsum is the sum of all (signal+bias)², so
        // sqsum = sum(signal²) + samples * bias²
        // hence
        // sum(signal²) = sqsum - samples * bias²
        // Bias is simply the average value, i.e.
        // bias = sum / samples
        // Since power = sum(signal²) / samples, we have
        // power = (sqsum - samples * sum² / samples²) / samples
        // so
        // power = (sqsum - sum² / samples) / samples
        double power = (sqsum - sum * sum / samples) / samples;

        // Scale to the range 0 - 1.
        power /= MAX_16_BIT * MAX_16_BIT;

        // Convert to dB, with 0 being max power. Add a fudge factor to make
        // a "real" fully saturated input come to 0 dB.
        double result = Math.log10(power) * 10f + FUDGE;
        int desibel = (int) result + 88;
        return desibel;
    }

    // ******************************************************************** //
    // Constructor.
    // ******************************************************************** //

    /**
     * Create an AudioReader instance.
     */
    public AudioReaderJava() {
        // audioManager = (AudioManager)
        // app.getSystemService(Context.AUDIO_SERVICE);
    }

    // ******************************************************************** //
    // Run Control.
    // ******************************************************************** //

    /**
     * Start this reader.
     *
     * @param rate     The audio sampling rate, in samples / sec.
     * @param block    Number of samples of input to read at a time. This is
     *                 different from the system audio buffer size.
     * @param listener Listener to be notified on each completed read.
     */
    public void startReader(int rate, int block, Listener listener) {
        Log.i(TAG, "Reader: Start Thread");
        synchronized (this) {
            // Calculate the required I/O buffer size.
            int audioBuf = AudioRecord.getMinBufferSize(rate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT) * 2;

            // Set up the audio input.
            audioInput = new AudioRecord(MediaRecorder.AudioSource.MIC, rate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, audioBuf);
            inputBlockSize = block;
            sleepTime = (long) (1000f / ((float) rate / (float) block));
            inputBuffer = new short[2][inputBlockSize];
            inputBufferWhich = 0;
            inputBufferIndex = 0;
            inputListener = listener;
            running = true;
            readerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    readerRun();
                }
            }, "Audio Reader");
            readerThread.start();
        }
    }

    /**
     * Stop this reader.
     */
    public void stopReader() {
        Log.i(TAG, "Reader: Signal Stop");
        synchronized (this) {
            running = false;
        }
        try {
            if (readerThread != null)
                readerThread.join();
        } catch (InterruptedException e) {
            ;
        }
        readerThread = null;

        // Kill the audio input.
        synchronized (this) {
            if (audioInput != null) {
                audioInput.release();
                audioInput = null;
            }
        }

        Log.i(TAG, "Reader: Thread Stopped");
    }

    // ******************************************************************** //
    // Main Loop.
    // ******************************************************************** //

    /**
     * Main loop of the audio reader. This runs in our own thread.
     */
    private void readerRun() {
        short[] buffer;
        int index, readSize;

        int timeout = 200;
        try {
            while (timeout > 0 && audioInput.getState() != AudioRecord.STATE_INITIALIZED) {
                Thread.sleep(50);
                timeout -= 50;
            }
        } catch (InterruptedException e) {
        }

        if (audioInput.getState() != AudioRecord.STATE_INITIALIZED) {
            Log.e(TAG, "Audio reader failed to initialize");
            readError(Listener.ERR_INIT_FAILED);
            running = false;
            return;
        }

        try {
            Log.i(TAG, "Reader: Start Recording");
            audioInput.startRecording();
            while (running) {
                long stime = System.currentTimeMillis();

                if (!running)
                    break;

                readSize = inputBlockSize;
                int space = inputBlockSize - inputBufferIndex;
                if (readSize > space)
                    readSize = space;
                buffer = inputBuffer[inputBufferWhich];
                index = inputBufferIndex;

                synchronized (buffer) {


                    int nread = 0;
                    if(audioInput != null) {
                        nread = audioInput.read(buffer, index, readSize);
                    }
                    boolean done = false;
                    if (!running)
                        break;

                    if (nread < 0) {
                        Log.e(TAG, "Audio read failed: error " + nread);
                        readError(Listener.ERR_READ_FAILED);
                        running = false;
                        break;
                    }
                    int end = inputBufferIndex + nread;
                    if (end >= inputBlockSize) {
                        inputBufferWhich = (inputBufferWhich + 1) % 2;
                        inputBufferIndex = 0;
                        done = true;
                    } else
                        inputBufferIndex = end;

                    if (done) {
                        readDone(buffer);

                        // Because our block size is way smaller than the audio
                        // buffer, we get blocks in bursts, which messes up
                        // the audio analyzer. We don't want to be forced to
                        // wait until the analysis is done, because if
                        // the analysis is slow, lag will build up. Instead
                        // wait, but with a timeout which lets us keep the
                        // input serviced.
                        long etime = System.currentTimeMillis();
                        long sleep = sleepTime - (etime - stime);
                        if (sleep < 5)
                            sleep = 5;
                        try {
                            buffer.wait(sleep);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        } finally {
            Log.i(TAG, "Reader: Stop Recording");
            if (audioInput != null && audioInput.getState() == AudioRecord.RECORDSTATE_RECORDING)
                audioInput.stop();
        }

    }

    /**
     * Notify the client that a read has completed.
     *
     * @param buffer Buffer containing the data.
     */
    private void readDone(short[] buffer) {
        synchronized (this) {
            audioData = buffer;
            ++audioSequence;

            short[] buffer2 = null;
            if (audioData != null && audioSequence > audioProcessed) {
                audioProcessed = audioSequence;
                buffer2 = audioData;
            }

            if (buffer2 != null) {
                final int len = buffer2.length;
                inputListener.onReadComplete(calculatePowerDb(buffer2, 0, len));
                buffer2.notify();
            }


        }
    }

    /**
     * Notify the client that an error has occurred. The reader has been
     * terminated.
     *
     * @param
     */
    private void readError(int code) {
        inputListener.onReadError(code);
    }

    // ******************************************************************** //
    // Class Data.
    // ******************************************************************** //

    // Debugging tag.
    private static final String TAG = "WindMeter";

    // ******************************************************************** //
    // Private Data.
    // ******************************************************************** //

    // Our audio input device.
    private AudioRecord audioInput;

    // Our audio input buffer, and the index of the next item to go in.
    private short[][] inputBuffer = null;
    private int inputBufferWhich = 0;
    private int inputBufferIndex = 0;

    // Size of the block to read each time.
    private int inputBlockSize = 0;

    // Time in ms to sleep between blocks, to meter the supply rate.
    private long sleepTime = 0;

    // Listener for input.
    private Listener inputListener = null;

    // Flag whether the thread should be running.
    private boolean running = false;

    // The thread, if any, which is currently reading. Null if not running.
    private Thread readerThread = null;


    private short[] audioData;
    private long audioSequence = 0;
    private long audioProcessed = 0;

    private static final float MAX_16_BIT = 32768;
    private static final float FUDGE = 0.6f;
}