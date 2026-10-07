package com.dgomesdev.littlelemonexercise.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.dgomesdev.littlelemonexercise.R
import com.dgomesdev.littlelemonexercise.domain.model.MenuEntity
import com.dgomesdev.littlelemonexercise.ui.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

private val LittleLemonGreen = Color(0xFF495E57)
private val LittleLemonYellow = Color(0xFFF4CE14)
private val LittleLemonGray = Color(0xFFEDEFEE)
private val LittleLemonDark = Color(0xFF333333)

@Composable
fun Home(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    onNavigation: () -> Unit,
) {
    val menuItems by viewModel.filteredMenuItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val categories by viewModel.categories.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HomeHeader(onProfileClick = onNavigation)

        HeroSection(
            searchQuery = searchQuery,
            onSearchQueryChange = { viewModel.updateSearchQuery(it) }
        )

        CategoryFilterSection(
            categories = categories.ifEmpty { listOf("starters", "mains", "desserts", "drinks") },
            selectedCategory = selectedCategory,
            onCategorySelected = { viewModel.selectCategory(it) }
        )

        HorizontalDivider(color = LittleLemonGray, thickness = 1.dp)

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading && menuItems.isEmpty()) {
                CircularProgressIndicator(color = LittleLemonGreen)
            } else if (menuItems.isEmpty()) {
                Text(
                    text = "No menu items found",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(menuItems, key = { it.id }) { menuItem ->
                        MenuItemRow(menuItem = menuItem)
                        HorizontalDivider(
                            color = LittleLemonGray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeHeader(
    onProfileClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .height(40.dp)
                .align(Alignment.Center)
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Profile",
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable { onProfileClick() }
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
private fun HeroSection(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LittleLemonGreen)
            .padding(16.dp)
    ) {
        Text(
            text = "Little Lemon",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonYellow
        )
        Text(
            text = "Chicago",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                color = Color.White,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Hero Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.2f))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = { Text("Enter search phrase", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search_icon),
                    contentDescription = "Search",
                    tint = LittleLemonDark
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = LittleLemonYellow,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = LittleLemonDark,
                unfocusedTextColor = LittleLemonDark
            ),
            singleLine = true
        )
    }
}

@Composable
private fun CategoryFilterSection(
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = "ORDER FOR DELIVERY!",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = LittleLemonDark,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                val isSelected = selectedCategory.equals(category, ignoreCase = true)
                CategoryChip(
                    categoryName = category.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString() },
                    isSelected = isSelected,
                    onClick = { onCategorySelected(category) }
                )
            }
        }
    }
}

@Composable
private fun CategoryChip(
    categoryName: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) LittleLemonGreen else LittleLemonGray
        ),
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = categoryName,
            color = if (isSelected) Color.White else LittleLemonGreen,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun MenuItemRow(
    menuItem: MenuEntity
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = menuItem.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = LittleLemonDark
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = menuItem.description,
                color = Color.Gray,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$${String.format(Locale.US, "%.2f", menuItem.price)}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = LittleLemonGreen
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        AsyncImage(
            model = menuItem.image,
            contentDescription = menuItem.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(LittleLemonGray)
        )
    }
}
