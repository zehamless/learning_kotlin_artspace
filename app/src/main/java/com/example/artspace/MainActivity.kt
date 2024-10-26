package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtspaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtspaceTheme {
                Surface { TipTimeLayout() }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    ArtspaceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) { TipTimeLayout() }
    }
}

@Composable
fun TipTimeLayout() {
    var now = remember { mutableStateOf(0) }
    val images = listOf(
        R.drawable.gemini_generated_image_c4vqgsc4vqgsc4vq,
        R.drawable.gemini_generated_image_ww6quwww6quwww6q,
        R.drawable.gemini_generated_image_kdcko6kdcko6kdck
    )
    val titles = listOf(
        "Random CPU 1",
        "Random CPU 2",
        "Random CPU 3"
    )
    val from = listOf(
        "Gemini AI 1",
        "Gemini AI 2",
        "Gemini AI 3"
    )
    val year = listOf(
        2021,
        2022,
        2023
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Art(img = images[now.value])
        Spacer(modifier = Modifier.weight(1f))
        Title(title = titles[now.value], from = from[now.value], year = year[now.value])
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { now.value = (now.value - 1 + images.size) % images.size },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.width(48.dp))
            Button(
                onClick = { now.value = (now.value + 1) % images.size },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
private fun Art(
    modifier: Modifier = Modifier,
    img: Int,
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .shadow(4.dp)
            .padding(16.dp)
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = Modifier.wrapContentSize() // Wrap content size to follow the image size
        )
    }
}

@Composable
private fun Title(
    modifier: Modifier = Modifier,
    title: String,
    from: String,
    year: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.wrapContentSize()
        )
        Row {
            Text(
                text = from,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "($year)",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
