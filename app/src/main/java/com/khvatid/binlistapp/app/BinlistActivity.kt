package com.khvatid.binlistapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.khvatid.binlistapp.ui.theme.BinlistAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BinlistActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BinlistAppTheme {
                BinlistApp()
            }
        }
    }
}
