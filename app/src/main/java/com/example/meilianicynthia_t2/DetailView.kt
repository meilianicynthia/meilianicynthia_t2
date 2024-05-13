package com.example.meilianicynthia_t2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meilianicynthia_t2.MainActivity.Companion.items

@Composable
fun Detailview (Itemid : Int?){
    val newitemlist = items.filter { 
        it.id == Itemid
    }
    Column {
        if (Itemid != null && newitemlist.isNotEmpty()){
            DetailLayout(newitemlist = newitemlist)
        }
    }
}

@Composable
fun DetailLayout(newitemlist: List<ListItem>) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), verticalArrangement = Arrangement.Top
            , horizontalAlignment = Alignment.CenterHorizontally
        
    ){
        newitemlist.forEach {
        Image(painter = painterResource(id = it.image), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = it.title
        )
    }
    }
}