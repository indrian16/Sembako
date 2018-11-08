package io.indrian16.sembako.database.repository.sembako

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "sembako-item")
data class Sembako(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long? = null,

        @ColumnInfo(name = "barcode")
        var barcode: Int = 0,

        @ColumnInfo(name = "title")
        var title: String = "",

        @ColumnInfo(name = "price")
        var price: Int = 0,

        @ColumnInfo(name = "stock")
        var stock: Int = 0,

        @ColumnInfo(name = "create_at")
        var createdAt: Date? = Date(),

        @ColumnInfo(name = "update_at")
        var updateAt: Date? = Date()
)