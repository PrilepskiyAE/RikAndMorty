package com.prilepskiy.prisentation.uiComponent

import android.app.Dialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties


@Composable
fun AlertDialogComponent(showDialog: MutableState<Boolean> ){

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                confirmButton = {
                    Button(onClick = { showDialog.value = false }) {
                        // set button text
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog.value= false }) {
                        Text("Dismiss")
                    }
                },
                icon = {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "info Icon" )
                },
                title = {
                    Text(text = "Alert Dialog Title", color = Color.Black)
                },
                text = {
                    Text(text = "This is the content of the alert dialog.", color = Color.DarkGray)
                },
                modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                containerColor = Color.White,
                iconContentColor = Color.Red,
                titleContentColor = Color.Black,
                textContentColor = Color.DarkGray,
                tonalElevation = 8.dp,
                properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
            )
        }
    }
}
