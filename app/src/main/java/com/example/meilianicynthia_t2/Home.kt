package com.example.meilianicynthia_t2

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.items

@Composable
fun Home(navController: NavHostController) {
    val item = remember {
        MainActivity.items
    }
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ){


    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
    items(items = item, key = {it.id}, itemContent = {
        RowItem(item = it){
            itemid ->  navController.navigate(Screens.DetailView.screen +"/$itemid")
        }
    } )

    }
        Spacer(modifier = Modifier.height(20.dp))

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(items = item, key = {it.id}, itemContent = {
            ColumnItem(item = it){
                    itemid ->  navController.navigate(Screens.DetailView.screen +"/$itemid")
            }
        } )

    }
    }
}

@Composable
fun RowItem(item: ListItem, OnItemClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .height(350.dp)
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .clickable { OnItemClick(item.id) }
                .fillMaxWidth()
                .height(300.dp),
            painter = painterResource(id = item.image),
            contentDescription = item.title,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = item.title, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun ColumnItem(item: ListItem, OnItemClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { OnItemClick(item.id) }
                .height(200.dp),
            painter = painterResource(id = item.image),
            contentDescription = item.title,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = item.title, fontWeight = FontWeight.SemiBold)
    }
}
