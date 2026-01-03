package com.nitish.android.karto.view.product_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nitish.android.karto.domain.products.model.Review
import com.nitish.android.karto.view.common.RatingStars

@Composable
fun ReviewCard(
    review: Review,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = review.reviewerName,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = review.dateForUi,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            RatingStars(rating = review.rating.toFloat())

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = review.comment,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))

        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewReviewCard() {
    ReviewCard(
        review = Review(
            rating = 4,
            comment = "This is a great product! Highly recommend it.",
            date = "2023-01-15",
            reviewerName = "John Doe",
            reviewerEmail = "john.doe@example.com",
            dateForUi = "2023-01-15"
        )
    )
}
