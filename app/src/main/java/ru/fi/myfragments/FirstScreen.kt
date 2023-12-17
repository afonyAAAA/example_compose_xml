package ru.fi.myfragments

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.lang.Exception


@Composable
fun FirstScreen(
    onClick: () -> Unit
){
    var bitmap : Bitmap? by remember { mutableStateOf(null) }
    var messageError : String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(text = "Привет, фрагмент!")

        if(bitmap != null){
            Image(bitmap = bitmap!!.asImageBitmap(), contentDescription = "")
        }

        if(messageError.isNotEmpty()){
            Text(text = messageError)
        }

        Button(onClick = onClick) {
            Text(text = "Открыть сканер")
        }

        Button(onClick = {

            try {
                val barcodeEncoder = BarcodeEncoder()
                bitmap = barcodeEncoder.encodeBitmap("012345678905", BarcodeFormat.UPC_A, 400, 400)
            }catch (e : Exception){
                messageError = e.message.toString()
            }

        }) {
            Text(text = "Создать штрих код")
        }



    }
}