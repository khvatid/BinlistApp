package com.khvatid.binlistapp.data.repository

import com.khvatid.binlistapp.data.storage.DatabaseStorage
import com.khvatid.binlistapp.data.storage.room.entity.BinlistEntity
import com.khvatid.binlistapp.domain.model.BinlistModel
import com.khvatid.binlistapp.domain.repository.DatabaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DatabaseRepositoryImp(private val storage: DatabaseStorage) : DatabaseRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun save(key: String, model: BinlistModel?) {
        coroutineScope.launch(Dispatchers.IO) {

        }
        storage.insert(model.toBinlistEntity(key))
    }

    override suspend fun getMap(): Map<String, BinlistModel> {
        return coroutineScope.async(Dispatchers.IO) {
            storage.getAll().associate { it.key to it.toBinlistModel() }
        }.await()
    }

    override fun deleteOne(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            storage.deleteOne(id)

        }
    }

    override fun deleteAll() {
        coroutineScope.launch(Dispatchers.IO) {
            storage.deleteAll()

        }
    }


    private fun BinlistModel?.toBinlistEntity(key: String): BinlistEntity {
        return if (this != null)
            BinlistEntity(
                key = key,
                length = this.number?.length,
                luhn = this.number?.luhn,
                scheme = this.scheme,
                type = this.type,
                brand = this.brand,
                prepaid = this.prepaid,
                bankName = this.bank?.name,
                url = this.bank?.url,
                phone = this.bank?.phone,
                city = this.bank?.city,
                numeric = this.country?.numeric,
                alpha2 = this.country?.alpha2,
                countryName = this.country?.name,
                emoji = this.country?.emoji,
                currency = this.country?.currency,
                latitude = this.country?.latitude,
                longitude = this.country?.longitude,
            ) else BinlistEntity(
            key = key,
            length = null,
            luhn = null,
            scheme = null,
            type = null,
            brand = null,
            prepaid = null,
            bankName = null,
            url = null,
            phone = null,
            city = null,
            numeric = null,
            alpha2 = null,
            countryName = null,
            emoji = null,
            currency = null,
            latitude = null,
            longitude = null
        )
    }

    private fun BinlistEntity.toBinlistModel(): BinlistModel {
        val bank: BinlistModel.Companion.Bank? = if (this.bankName == null &&
            this.url == null &&
            this.phone == null &&
            this.city == null
        ) null else BinlistModel.Companion.Bank(
            name = this.bankName,
            url = this.url,
            phone = this.phone,
            city = this.city
        )
        val country: BinlistModel.Companion.Country? = if (
            this.numeric == null &&
            this.alpha2 == null &&
            this.countryName == null &&
            this.emoji == null &&
            this.currency == null &&
            this.latitude == null &&
            this.longitude == null
        ) null else BinlistModel.Companion.Country(
            numeric = this.numeric,
            alpha2 = this.alpha2,
            name = this.countryName,
            emoji = this.emoji,
            currency = this.currency,
            latitude = this.latitude,
            longitude = this.longitude
        )


        return BinlistModel(
            number = BinlistModel.Companion.Number(length = this.length, luhn = this.luhn),
            scheme = this.scheme,
            type = this.type,
            brand = this.brand,
            prepaid = this.prepaid,
            bank = bank,
            country = country
        )
    }

}