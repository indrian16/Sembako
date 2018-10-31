package io.indrian16.sembako.database.repository.sembako

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "sembako-item")
data class Sembako(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long?,

        @ColumnInfo(name = "barcode")
        var barcode: Int?,

        @ColumnInfo(name = "title")
        var title: String?,

        @ColumnInfo(name = "price")
        var price: Int?,

        @ColumnInfo(name = "stock")
        var stock: Int?,

        @ColumnInfo(name = "create_at")
        var createdAt: Date?,

        @ColumnInfo(name = "update_at")
        var updateAt: Date?


) { constructor() : this(null, null, null, null, null, null, null) }