package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculator()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculator() {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var bmi by remember { mutableStateOf(0.0) }
    var bmiCategory by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = {
                val weightInKg = weight.toDoubleOrNull() ?: 0.0
                val heightInCm = height.toDoubleOrNull() ?: 0.0
                bmi = if (heightInCm > 0) {
                    weightInKg / (heightInCm * heightInCm) * 10000
                } else {
                    0.0
                }
                bmiCategory = when {
                    bmi < 18.5 -> "Underweight"
                    bmi < 25 -> "Normal"
                    bmi < 30 -> "Overweight"
                    else -> "Obese"
                }
            }
        ) {
            Text("Calculate BMI")
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text("BMI: $bmi")
        Text("Category: $bmiCategory")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        BMICalculator()
    }
}