package com.depromeet.bboxx.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.depromeet.bboxx.R
import com.depromeet.bboxx.domain.enums.PlatformType
import com.depromeet.bboxx.domain.enums.SnsVerifyEvent
import com.depromeet.bboxx.presentation.base.BaseActivity
import com.depromeet.bboxx.presentation.event.SnsErrorEvent
import com.depromeet.bboxx.presentation.ui.rxbus.RxBus
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task

class GoogleLoginActivity: BaseActivity(R.layout.activity_sns_login), GoogleApiClient.OnConnectionFailedListener {
    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

    private var socialUserId = ""
    private var userEmail = ""
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            userEmail = account?.email.toString()
            val familyName = account?.familyName.toString()
            val givenName = account?.givenName.toString()
            val displayName = account?.displayName.toString()
            socialUserId = account.id

            Log.d("account", userEmail)
            Log.d("account", socialUserId)
            Log.d("account", givenName)
            Log.d("account", displayName)

            RxBus.send(
                SnsVerifyEvent(
                    userEmail, "",
                    socialUserId, PlatformType.GOOGLE, accessToken = ""
                )
            )
            finish()

        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("failed", "signInResult:failed code=" + e.statusCode)
            if(e.statusCode != GoogleSignInStatusCodes.SIGN_IN_CANCELLED){
                RxBus.send(SnsErrorEvent)
                finish()
            }
            else{
                finish()
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }
}