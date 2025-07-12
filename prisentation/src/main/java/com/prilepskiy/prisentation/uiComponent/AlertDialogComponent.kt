package com.prilepskiy.prisentation.uiComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun FullScreenDialogFilter(showDialog: MutableState<Boolean>,selectedGender: MutableState<String>, selectedStatus: MutableState<String>,goToFilter: () -> Unit,) {
    val radioOptions = listOf("All","Female", "Male", "Genderless","unknown")
    val radioOptions2 = listOf("All","Alive", "Dead", "unknown")

    if (showDialog.value)
    {
        Dialog(onDismissRequest = {showDialog.value = false},
            properties = DialogProperties(usePlatformDefaultWidth = false))
        {
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(0.dp),
                color = Color.Gray.copy(9f)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("FILTER GENDER")
                    Column {
                        radioOptions.forEach { option ->
                            Row(
                                Modifier
                                    .clickable { selectedGender.value = option }
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (option == selectedGender.value) {
                                    Icon(
                                        Icons.Default.Check,
                                        contentDescription = "Selected option",
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.Red
                                    )
                                } else {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "Unselected option",
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.Gray
                                    )
                                }
                                Text(
                                    text = option,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }

                    Text("FILTER STATUS")
                    Column {
                        radioOptions2.forEach { option ->
                            Row(
                                Modifier
                                    .clickable { selectedStatus.value = option }
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (option == selectedStatus.value) {
                                    Icon(
                                        Icons.Default.Check,
                                        contentDescription = "Selected option",
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.Red
                                    )
                                } else {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "Unselected option",
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.Gray
                                    )
                                }
                                Text(
                                    text = option,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                    Button(
                        onClick = goToFilter,
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Ok")
                    }
                }


                }
            }
        }
    }
