package com.example.login.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.ui.theme.Shape
import com.example.login.ui.theme.black
import com.example.login.ui.theme.white

@Composable
fun ButtonEdit(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier
) {

    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = black
        ),
        shape = Shape.medium,
        border = BorderStroke(2.dp, white),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        )
    )
    {
        Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = white)
    }
}
