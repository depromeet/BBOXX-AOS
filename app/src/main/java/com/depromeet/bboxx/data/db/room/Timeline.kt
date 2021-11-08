package com.depromeet.bboxx.data.db.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Timeline")
class Timeline(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "message") var message: String?
){
    constructor() : this(null, "")
}