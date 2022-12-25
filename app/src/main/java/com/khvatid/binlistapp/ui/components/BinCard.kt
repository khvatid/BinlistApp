package com.khvatid.binlistapp.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khvatid.binlistapp.domain.model.BinlistModel
import com.khvatid.binlistapp.ui.theme.BinlistAppTheme
import com.khvatid.binlistapp.R.string as rString

private const val NULL = "???"

@Composable
fun BinCard(
    model: BinlistModel,
    modifier: Modifier = Modifier,
    onPhoneClick: () -> Unit = {},
    onUrlClick: () -> Unit = {},
    onLocationClick: () -> Unit = {}
) {
    Card(modifier = modifier) {
        Column {
            Row(
                modifier = Modifier.padding(10.dp),
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    PrettyData(label = rString.scheme, text = model.scheme ?: NULL)
                    PrettyData(label = rString.brand, text = model.brand ?: NULL)
                    PrettyNumber(number = model.number)
                }
                Column(modifier = Modifier.weight(1f)) {
                    PrettyData(label = rString.type, text = model.type ?: NULL)
                    PrettyData(
                        label = rString.prepaid,
                        text = if (model.prepaid != null) {
                            if (model.prepaid == true) "Yes" else "No"
                        } else NULL
                    )
                    PrettyCountry(country = model.country, onLocationClick = onLocationClick)
                }
            }
            Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {
                PrettyBank(bank = model.bank, onPhoneClick = onPhoneClick, onUrlClick = onUrlClick)
            }
        }
    }
}

@Composable
private fun PrettyLabel(
    @StringRes text: Int, style: TextStyle = MaterialTheme.typography.labelLarge
) {
    Text(
        text = stringResource(id = text), style = style
    )
}


@Composable
private fun PrettyData(@StringRes label: Int, text: String) {
    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        PrettyLabel(text = label)
        Text(
            text = text, style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
private fun PrettyNumber(number: BinlistModel.Companion.Number?) {
    AnimatedVisibility(visible = number != null) {
        if (number != null) {
            Column() {
                PrettyLabel(text = rString.number)
                Row() {
                    Column(Modifier.weight(1f)) {
                        PrettyLabel(
                            text = rString.length, style = MaterialTheme.typography.labelSmall
                        )
                        Text(
                            text = if (number.length != null) number.length.toString() else NULL,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        PrettyLabel(
                            text = rString.luhn, style = MaterialTheme.typography.labelSmall
                        )
                        Text(
                            text = if (number.luhn != null) {
                                if (number.luhn == true) "Yes" else "No"
                            } else NULL,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PrettyCountry(
    country: BinlistModel.Companion.Country?,
    onLocationClick: () -> Unit = {}
) {
    AnimatedVisibility(visible = country != null) {
        if (country != null) {
            Column {
                PrettyLabel(text = rString.country)
                Text(
                    text = "${country.emoji} ${country.name}",
                    style = MaterialTheme.typography.bodyMedium
                )
                ClickableText(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(MaterialTheme.colorScheme.onBackground)) {

                            append("(latitude:")
                            withStyle(MaterialTheme.typography.bodySmall.toSpanStyle()) {
                                append(" ${country.latitude}")
                            }
                            append(", longitude:")
                            withStyle(MaterialTheme.typography.bodySmall.toSpanStyle()) {
                                append(" ${country.longitude}")
                            }
                            append(')')
                        }
                    },
                    style = MaterialTheme.typography.labelSmall,

                    onClick = {
                        if (country.latitude != null && country.longitude != null)
                            onLocationClick()
                    }
                )
            }
        }
    }
}

@Composable
private fun PrettyBank(
    bank: BinlistModel.Companion.Bank?, onPhoneClick: () -> Unit = {}, onUrlClick: () -> Unit = {}
) {
    AnimatedVisibility(visible = bank != null) {
        if (bank != null) {
            Column {
                PrettyLabel(text = rString.bank)
                Text(
                    text = "${bank.name}, ${bank.city ?: NULL}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Row() {

                    ClickableText(modifier = Modifier.weight(1f), text = AnnotatedString(
                        text = bank.phone ?: "???",
                        spanStyle = MaterialTheme.typography.labelMedium.toSpanStyle()
                            .copy(color = MaterialTheme.colorScheme.onBackground)
                    ), onClick = {
                        if (!bank.phone.isNullOrEmpty()) onPhoneClick()
                    })
                    ClickableText(modifier = Modifier.weight(1f), text = AnnotatedString(
                        text = bank.url ?: "???",
                        spanStyle = MaterialTheme.typography.labelMedium.toSpanStyle()
                            .copy(color = MaterialTheme.colorScheme.primary)
                    ), onClick = {
                        if (!bank.url.isNullOrEmpty()) onUrlClick()
                    })
                }
            }
        }
    }

}


@Composable
@Preview
private fun Preview() {
    BinlistAppTheme() {
        BinCard(
            model = BinlistModel(
                number = BinlistModel.Companion.Number(16, true),
                scheme = "Visa",
                type = "Debit",
                brand = "Visa/Dankort",
                prepaid = false,
                country = BinlistModel.Companion.Country(
                    numeric = "208",
                    alpha2 = "DK",
                    name = "Denmark",
                    emoji = "🇩🇰",
                    currency = "DKK",
                    latitude = 56,
                    longitude = 10
                ),
                bank = BinlistModel.Companion.Bank(
                    name = "Jyske Bank",
                    url = "www.jyskebank.dk",
                    phone = "+4589893300",
                    city = "Hjørring"
                )
            )
        )
    }

}
