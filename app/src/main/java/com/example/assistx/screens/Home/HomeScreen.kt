import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.assistx.R
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = NavHostController(LocalContext.current))
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3A6D8C))
            .padding(16.dp)
    ) {
        GreetingSection()
        Spacer(modifier = Modifier.height(16.dp))
        HelpAssistanceCard()
        Spacer(modifier = Modifier.height(16.dp))
        ServicesSection()
        Spacer(modifier = Modifier.height(16.dp))
        TopPlacesSection()
    }
}


@Composable
fun GreetingSection() {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Greeting Message
        Column(modifier = Modifier.statusBarsPadding()) {
            Text(text = "Hey There!", color = Color(0xFF1D2366), fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "How are you feeling today?", color = Color(0xFFEAD8B1), fontSize = 14.sp)
        }
        // Notification and Profile Icons
        Row(modifier = Modifier.padding(top = 15.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {
                //todo
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.img),
                    modifier = Modifier
                        .height(28.dp)
                        .width(33.dp),
                    contentDescription = "Notifications",
                    tint = Color(0xFF1D2366)
                )
            }
            IconButton(onClick = {
                Toast.makeText(context, "No new Notification", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.img_4),
                    modifier = Modifier
                        .height(28.dp)
                        .width(33.dp),
                    contentDescription = "Notifications",
                    tint = Color(0xFF1D2366)
                )
            }
        }
    }
}

@Composable
fun HelpAssistanceCard() {
    Spacer(modifier = Modifier.height(30.dp))
    Box(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            .width(345.dp)
            .height(150.dp)
            .background(Color(0xFF1D2366), RoundedCornerShape(25.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.img_5),
                contentDescription = "Doctor Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(130.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(text = "   Travel Freely\nAdvocate Boldly", color = Color.White, fontSize = 16.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* Book Now Action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text(text = "Get Now", color = Color(0xFF1E3A5F), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ServicesSection() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedService by remember { mutableStateOf("") }

    Column {
        Text(text = "Services", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ServiceIcon(iconRes = R.drawable.strea, label = "Stretcher") {
                selectedService = "Stretcher"
                showDialog = true
            }
            ServiceIcon(iconRes = R.drawable.img_3, label = "WheelChair") {
                selectedService = "WheelChair"
                showDialog = true
            }
            ServiceIcon(iconRes = R.drawable.amb, label = "Ambulance") {
                selectedService = "Ambulance"
                showDialog = true
            }
            ServiceIcon(iconRes = R.drawable.others, label = "Other") {
                selectedService = "Other"
                showDialog = true
            }
        }

        when (selectedService) {
            "Stretcher" -> if (showDialog) StretcherDialog { showDialog = false }
            "WheelChair" -> if (showDialog) WheelChairDialog { showDialog = false }
            "Ambulance" -> if (showDialog) AmbulanceDialog { showDialog = false }
            "Other" -> if (showDialog) OtherServiceDialog { showDialog = false }
        }
    }
}

@Composable
fun ServiceIcon(iconRes: Int, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.White, CircleShape)
                .padding(12.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                tint = Color(0xFF4E8A81),
                modifier = Modifier.size(26.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, color = Color.White, fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun StretcherDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Stretcher Service", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Call on 93049XXXX to get assistance with stretcher services anywhere within 20 minutes.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onDismiss) {
                    Text("Close")
                }
            }
        }
    }
}

@Composable
fun WheelChairDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Wheelchair Service", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Call on 84056XXXX to get a wheelchair delivered quickly to your location.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onDismiss) {
                    Text("Close")
                }
            }
        }
    }
}

@Composable
fun AmbulanceDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Ambulance Service", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Call on 102 for immediate ambulance support.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onDismiss) {
                    Text("Close")
                }
            }
        }
    }
}

@Composable
fun OtherServiceDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Other Services", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "For other services, call our support at +91-62044XXX.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onDismiss) {
                    Text("Close")
                }
            }
        }
    }
}



@Composable
fun TopPlacesSection() {
    val places = listOf(
        Place("FM Mall", "Vijayawada", 4.9, 1280),
        Place("PVP Mall", "Vijayawada", 4.8, 1108),
        Place("DLF Avenue", "New Delhi", 4.6, 950),
        Place("BKC", "Mumbai", 4.6, 880),
        Place("LEPL Centro", "Vijayawada", 4.5, 530),
        Place("Trendset Mall", "Vijayawada", 4.2, 280)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Most Rated Places",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(places) { place ->
                PlaceCard(
                    name = place.name,
                    location = place.location,
                    rating = place.rating,
                    reviews = place.reviews
                )
            }
        }
    }
}

data class Place(val name: String, val location: String, val rating: Double, val reviews: Int)

@Composable
fun PlaceCard(name: String, location: String, rating: Double, reviews: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Place Image",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = name, color = Color(0xFF1E3A5F), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = location, color = Color.Gray, fontSize = 14.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.img_2),
                        contentDescription = "Rating",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "$rating ", color = Color.Gray, fontSize = 14.sp)
                    Text(text = "($reviews Reviews)", color = Color.Gray, fontSize = 12.sp)
                }
            }
        }
    }
}