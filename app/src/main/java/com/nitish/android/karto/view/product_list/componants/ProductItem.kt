package com.nitish.android.karto.view.product_list.componants

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nitish.android.karto.domain.products.model.Product

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onProductClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable(onClick = { onProductClick(product.id) })
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = product.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                //TODO add thumbnail image using Glide lib, do research LATER NOT NOW
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${product.price}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${product.discountPercentage}",
                style = MaterialTheme.typography.bodyMedium
            )
            //TODO add rating view
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    ProductItem(
        modifier = Modifier,
        product = Product(
            id = 1,
            title = "iPhone 9",
            description = "An apple mobile which is nothing like apple",
            price = 549.0f,
            discountPercentage = 12.96f,
            rating = 4.69f,
            stock = 94,
            brand = "Apple",
            category = "smartphones",
            thumbnail = "",
            images = listOf(),
            tags = listOf(),
            sku = "",
            reviews = listOf(),
            returnPolicy = "",
            minimumOrderQuantity = 0
        ),
        onProductClick = {},
    )
}