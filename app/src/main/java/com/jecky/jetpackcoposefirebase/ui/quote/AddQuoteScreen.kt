package com.jecky.jetpackcoposefirebase.ui.quote

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jecky.jetpackcoposefirebase.model.TextData

@Composable
fun AddQuoteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Add Your Quote",
            style = TextStyle(fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        val quoteTextData by remember {
            mutableStateOf(TextData())
        }
        val quoteAuthorData by remember {
            mutableStateOf(TextData())
        }
        CommonTextField(quoteTextData, height = 150.dp, label = "Write quote here")
        Spacer(modifier = Modifier.height(10.dp))
        CommonTextField(quoteAuthorData, height = Dp.Unspecified, label = "Quote author name")
        Spacer(modifier = Modifier.height(15.dp))
        CategoryDropdown()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(textData: TextData, height: Dp, label: String) {
    OutlinedTextField(
        value = textData.enteredText, onValueChange = {
            textData.enteredText = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(10.dp),
        label = {
            Text(text = label)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdown() {
    /*  var expanded by remember {
          mutableStateOf(false)
      }
      val items = listOf("Latest", "Philosophy", "Motivation")
      var selectedIndex by remember {
          mutableIntStateOf(0)
      }

      DropdownMenu(expanded = expanded, onDismissRequest = { *//*TODO*//* },
    modifier = Modifier.fillMaxWidth()) {
        items.forEachIndexed { index, s ->
            DropdownMenuItem(
                text = { Text(text = s) },
                onClick = { *//*TODO*//* })
        }
    }*/
    val options = listOf("Option 1", "Option 2", "Option 3")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },

        modifier = Modifier
            .padding(horizontal = 15.dp)
            .border(
                border = BorderStroke(width = 1.dp, color = Color.Gray),
                shape = RoundedCornerShape(5.dp)
            ).fillMaxWidth()

    ) {
        TextField(
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAddQuoteScreen() {
    AddQuoteScreen()
}

