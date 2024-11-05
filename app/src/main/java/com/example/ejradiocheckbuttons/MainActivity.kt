package com.example.ejradiocheckbuttons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejradiocheckbuttons.ui.theme.EjRadioCheckButtonsTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjRadioCheckButtonsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaPrincipal()
                }
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PantallaPrincipal(){

    var textoBoton by rememberSaveable {
        mutableStateOf("Presionar")
    }

    var Checked by rememberSaveable {
        mutableStateOf(false)
    }

    var isLoading by rememberSaveable {
        mutableStateOf(false)
    }

    var imageIndex by rememberSaveable {
        mutableStateOf(0)
    }

    var isSwitchSelected by rememberSaveable{
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            isSwitchSelected = mySwitch()
        }

        if (isSwitchSelected){
            Column() {
                myRadioGroup()
            }
        }

        Button(
            modifier = Modifier.background(Color.White),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White),
            onClick = {
                imageIndex++
                if (imageIndex == 3){
                    imageIndex = 0
                }
            }
        ){
            when (imageIndex){
                0 -> myImage1()
                1 -> myImage2()
                2 -> myImage3()
            }

        }


        Spacer(modifier = Modifier.height(50.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {

            Button(
                modifier = Modifier
                    .size(120.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color(0xFFFFEE58)),

                onClick = {
                    textoBoton = "Botón presionado"
                    isLoading = true
                }

            ) {

                Text(text = textoBoton)

            }


            if (isLoading) {
                LaunchedEffect(isLoading) {
                    delay(5000)
                    isLoading = false
                    textoBoton = "Presionar"
                }
            }

            myCircularProgressIndicator(isLoading)

        }

        Spacer(modifier = Modifier.height(40.dp))

        Box() {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Checkbox(
                    checked = Checked,
                    onCheckedChange = {
                        Checked = it
                    }
                )
                Text(
                    text = "Activar",
                    fontSize = 15.sp,
                    color = Color.Red,
                    fontFamily = FontFamily.Monospace
                )
            }

        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(verticalAlignment = Alignment.CenterVertically){
            if (Checked) {
                Text(
                    text = "Mensaje oculto",
                    textAlign = TextAlign.End,
                    color = Color.Magenta,
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp
                )
            }

        }

        Spacer(modifier = Modifier.height(40.dp))

        Box() {
            Row(verticalAlignment = Alignment.CenterVertically) {

                myIcon()

            }

        }


    }


}

@Composable
fun myRadioGroup(){
    var selectedOption by rememberSaveable { mutableStateOf("Opción 1") }

    myRadioButton("Opción 1")
    myRadioButton("Opción 2")
    myRadioButton("Opción 3")

}

@Composable
fun myRadioButton(opcion:String){

    var isSelected by rememberSaveable {
        mutableStateOf(false)
    }

    Row (verticalAlignment = Alignment.CenterVertically){

        RadioButton(
            selected = isSelected,
            onClick = {
                if (!isSelected) {
                    isSelected = true
                }else{
                    isSelected = false
                }
            },
            colors = RadioButtonDefaults.colors(Color.Cyan)
        )



        Text(
            text = opcion,
            color = Color.DarkGray)
    }
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
fun myIcon(){

    Icon(
        imageVector = Icons.Default.AccountBox,
        contentDescription = "User",
        tint = Color.LightGray,
        modifier = Modifier.size(80.dp)
    )

}


@Composable
fun myImage1(){

    Image(
        painter = painterResource(id = R.drawable.android),
        contentDescription = "Android",
        modifier = Modifier.size(150.dp)
    )

}


@Composable
fun myImage2() {
    Image(
        painter = painterResource(id = R.drawable.elefun),
        contentDescription = "Elefan",
        modifier = Modifier.size(150.dp)
    )

}


@Composable
fun myImage3(){

    Image(
        painter = painterResource(id = R.drawable.stich),
        contentDescription = "Stich",
        modifier = Modifier.size(150.dp)

    )

}


@Composable
fun mySwitch(): Boolean {

    var estado by rememberSaveable {
        mutableStateOf(false)
    }

    Switch(
        checked = estado,
        onCheckedChange = { estado = it },
        colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.White,
            checkedThumbColor = Color.White,
            uncheckedBorderColor = Color.Gray,
            checkedBorderColor = Color.Gray,
            uncheckedTrackColor = Color.Black,
            checkedTrackColor = Color.Green
        )
    )

    if (estado){
        return true
    }else{
        return false
    }
}


@Composable
fun myCircularProgressIndicator(isLoading: Boolean){


    if (isLoading) {
        // Iniciar el temporizador
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.White),
            contentAlignment = Alignment.CenterEnd

        ) {
            CircularProgressIndicator(
                color = Color.DarkGray
            )
        }

    }

}
