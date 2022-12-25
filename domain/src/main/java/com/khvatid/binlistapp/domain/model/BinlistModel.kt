package com.khvatid.binlistapp.domain.model

data class BinlistModel(
    val number: Number? = null,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    val country: Country? = null,
    val bank: Bank? = null
) {
    companion object {
        data class Number(
            val length: Int?,
            val luhn: Boolean?
        )

        data class Country(
            val numeric: String?,
            val alpha2: String?,
            val name: String?,
            val emoji: String?,
            val currency: String?,
            val latitude: Int?,
            val longitude: Int?,
        )

        data class Bank(
            val name: String?,
            val url: String?,
            val phone: String?,
            val city: String?
        )
    }
}

