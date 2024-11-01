import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assistx.R
import com.example.assistx.navigation.AssistX_Screens

@Composable
fun SplashScreen(navController: NavController) {
    val bounceAnim = remember { Animatable(0f) }
    val slideAnim = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        bounceAnim.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(durationMillis = 1500, easing = LinearOutSlowInEasing)
        )
        slideAnim.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f - slideAnim.value * 0.6f)
                .background(Color.White, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                painter = painterResource(id = R.drawable.logo),
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(start = 10.dp, bottom = 4.dp)
                    .size(120.dp),
                contentDescription = "Notifications",
                tint = Color(0xFF3B3A6D)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Your Accessibility Guide for Confident Travel\n",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color(0xFF4E8A81),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate(AssistX_Screens.LoginScreen.name) },
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF15636D))
            ) {
                Text(text = "LOGIN", color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFFB0BEC5))) {
                        append("Don't have an account? ")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFF027373), fontWeight = FontWeight.Bold)) {
                        append("Sign up!")
                    }
                },
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable {
                    navController.navigate(AssistX_Screens.SignUpScreen.name)

                }
            )
        }

        Image(
            painter = painterResource(id = R.drawable.img_1),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-50 * bounceAnim.value).dp)
        )
    }
}
