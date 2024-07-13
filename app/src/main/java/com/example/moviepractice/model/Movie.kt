package com.example.moviepractice.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie_table")
data class Movie(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("overview")
    @ColumnInfo("overview")val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    @ColumnInfo("poster_path")val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    @ColumnInfo("title") val title: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null
)
