package com.example.yournews.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yournews.model.Source
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Parcelize
@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
):Parcelable