package com.nitish.android.karto.view.product_list.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nitish.android.karto.domain.products.model.Product
import com.nitish.android.karto.view.common.RatingStars

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onProductClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = { onProductClick(product.id) }),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = product.thumbnail,
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(
                            color = Color.Red.copy(alpha = 0.4F),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.TopStart)
                ) {
                    Text(
                        text = "-${product.discountPercentage}%",
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            RatingStars(product.rating)
            Text(
                text = product.category,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.W400
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "₹${product.price}",
                    style = MaterialTheme.typography.labelSmall.copy(
                        textDecoration = TextDecoration.LineThrough
                    ),
                    color = Color.Gray,
                )
                Text(
                    text = "₹${product.discountPrice}",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProductItemPreview() {
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
            category = "smart phones",
            thumbnail = "",
            images = listOf(),
            tags = listOf(),
            sku = "",
            reviews = listOf(),
            returnPolicy = "",
            minimumOrderQuantity = 0,
            discountPrice = 1.0F,
        ),
        onProductClick = {},
    )
}