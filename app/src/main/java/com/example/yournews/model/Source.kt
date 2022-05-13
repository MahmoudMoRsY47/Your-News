package com.example.yournews.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Parcelize
data class Source(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
):Parcelable