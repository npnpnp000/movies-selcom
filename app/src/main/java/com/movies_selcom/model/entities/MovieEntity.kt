package com.movies_selcom.model.entities



import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")

data class MovieEntity(
    @ColumnInfo val name: String,
   @PrimaryKey val id: Int = -1,
    @ColumnInfo val image_path: String,
    @ColumnInfo val overview: String
) {
    override fun equals(other: Any?): Boolean {
        return (other as MovieEntity).id == id
    }

    override fun hashCode(): Int {
        return id
    }
}

