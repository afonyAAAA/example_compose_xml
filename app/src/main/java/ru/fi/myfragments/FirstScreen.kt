package ru.fi.myfragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun FirstScreen(
    onClick: () -> Unit
){

    val soapHelper = SoapRequestHelper()
    var scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        var response by remember { mutableStateOf("") }
        var a by remember { mutableStateOf("") }
        var b by remember { mutableStateOf("") }

        OutlinedTextField(value = a, onValueChange = {
            a = it
        })

        OutlinedTextField(value = b, onValueChange = {
            b = it
        })

        Button(onClick = {
            val parameter1 = Pair("intA", a.toInt())
            val parameter2 = Pair("intB", b.toInt())
            scope.launch(Dispatchers.IO) {
                response = soapHelper.makeSoapRequest("Add", listOf(parameter1, parameter2)) ?: "Ошибка"
            }
        }) {
            Text(text = "Посчитать")
        }

        if(response.isNotEmpty()){
            Row {
                Text(text = "Ответ: ")
                Text(text = response)
            }
        }

    }
}