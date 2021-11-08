package com.depromeet.bboxx.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Timeline::class], version = 1)
abstract class TimelineDatabase : RoomDatabase() {
    abstract fun timelineListDao(): TimelineDao

}