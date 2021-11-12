package com.depromeet.bboxx.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//class DatabaseModule {
//    @Singleton
//    @Provides
//    fun provideTimelineDatabase(
//        @ApplicationContext app: Context
//    ) = Room.databaseBuilder(
//        app,
//        TimelineDatabase::class.java,
//        "timeline.db"
//    ).build()
//
//    @Singleton
//    @Provides
//    fun provideTimelineDao(db: TimelineDatabase) = db.timelineListDao()
//}