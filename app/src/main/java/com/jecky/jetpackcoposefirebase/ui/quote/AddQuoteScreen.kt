package com.jecky.jetpackcoposefirebase.ui.quote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.jecky.jetpackcoposefirebase.model.TextData
import com.jecky.jetpackcoposefirebase.repository.model.Category
import com.jecky.jetpackcoposefirebase.repository.model.Quote
import com.jecky.jetpackcoposefirebase.ui.category.CategoryViewModel
import com.jecky.jetpackcoposefirebase.ui.category.CategoryViewModelFactory
import com.jecky.jetpackcoposefirebase.ui.theme.md_theme_light_onPrimary
import com.jecky.jetpackcoposefirebase.util.AppConstants.QUOTE_LENGTH

@OptIn(ExperimentalMaterial3Api::class)
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
            style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.W700)
        )
        Spacer(modifier = Modifier.height(20.dp))
        val quoteTextData by remember {
            mutableStateOf(TextData())
        }
        val quoteAuthorData by remember {
            mutableStateOf(TextData())
        }
        val categoryViewModel: CategoryViewModel = viewModel(factory = CategoryViewModelFactory())
        CommonTextField(quoteTextData, height = 180.dp, label = "Write quote here")
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = quoteTextData.enteredText.length.toString().plus("/").plus(QUOTE_LENGTH),
            modifier = Modifier
                .align(Alignment.End)
                .padding(horizontal = 15.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        CommonTextField(
            textData = quoteAuthorData, height = Dp.Unspecified, label = "Quote author name"
        )
        Spacer(modifier = Modifier.height(15.dp))
        var selectedOptionText by remember { mutableStateOf("Select Category") }
        var selectedCategoryId by remember {
            mutableStateOf("")
        }
        CategoryDropdown(categoryViewModel, selectedOptionText, onCategorySelect = {
            selectedOptionText = it.name
            selectedCategoryId = it.id
        })
        Spacer(modifier = Modifier.height(10.dp))
        val quoteViewModel: QuoteViewModel = viewModel(factory = QuoteViewModelFactory())

        Button(
            onClick = {
                quoteViewModel.addQuote(
                    Quote(
                        quote = quoteTextData.enteredText,
                        author = quoteAuthorData.enteredText,
                        categoryId = selectedCategoryId,
                        userId = FirebaseAuth.getInstance().currentUser?.uid!!
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            enabled = quoteAuthorData.enteredText.isNotEmpty() && quoteTextData.enteredText.isNotEmpty() && (selectedOptionText == "Select Category").not()
        ) {

            if (quoteViewModel.loading) {
                CircularProgressIndicator(color = md_theme_light_onPrimary)
            } else Text(text = "Submit")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(textData: TextData, height: Dp, label: String) {
    OutlinedTextField(value = textData.enteredText,
        onValueChange = {
            if (it.length <= QUOTE_LENGTH) textData.enteredText = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(10.dp),
        label = {
            Text(text = label)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdown(
    categoryViewModel: CategoryViewModel,
    selectedOptionText: String,
    onCategorySelect: (Category) -> Unit
) {
    val categories by categoryViewModel.categories.observeAsState(emptyList())

    // val options = listOf("Option 1", "Option 2", "Option 3")
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        categoryViewModel.getCategories()
    }
    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = { expanded = !expanded },

        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()

    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
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
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.name) },
                    onClick = {
                        onCategorySelect(category)
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

