package com.nitish.android.karto.view.product_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
) {
    Column {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = "Hello Navigation !",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview() {
    ProductListScreen()
}