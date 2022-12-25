package com.khvatid.binlistapp.data.storage.room.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "binlist")
data class BinlistEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "key")
    val key: String,

    @ColumnInfo(name = "number_length")
    val length: Int?,

    @ColumnInfo(name = "number_luhn")
    val luhn: Boolean?,

    @ColumnInfo(name = "scheme")
    val scheme: String?,

    @ColumnInfo(name = "type")
    val type: String?,

    @ColumnInfo(name = "brand")
    val brand: String?,

    @ColumnInfo(name = "prepaid")
    val prepaid: Boolean?,

    @ColumnInfo(name = "bank_name")
    val bankName: String?,

    @ColumnInfo(name = "bank_url")
    val url: String?,

    @ColumnInfo(name = "bank_phone")
    val phone: String?,

    @ColumnInfo(name = "bank_city")
    val city: String?,

    @ColumnInfo(name = "country_numeric")
    val numeric: String?,

    @ColumnInfo(name = "country_alpha2")
    val alpha2: String?,

    @ColumnInfo(name = "country_name")
    val countryName: String?,

    @ColumnInfo(name = "country_emoji")
    val emoji: String?,

    @ColumnInfo(name = "country_currency")
    val currency: String?,

    @ColumnInfo(name = "country_latitude")
    val latitude: Int?,

    @ColumnInfo(name = "country_longitude")
    val longitude: Int?,
)