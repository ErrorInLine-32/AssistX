import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.assistx.R
import com.example.assistx.navigation.AssistX_Screens

@Composable
fun PreSplashScreen(navController: NavController) {
    val context = LocalContext.current
    val preferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val isLoggedIn = preferences.getBoolean("isLoggedIn", false)

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
    val progress by animateLottieCompositionAsState(composition = composition)

    LaunchedEffect(progress) {
        if (progress == 1f) {
            navController.navigate(
                if (isLoggedIn) AssistX_Screens.HomeScreen.name
                else AssistX_Screens.SplashScreen.name
            )
        }
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF387478)) {
        LottieAnimation(composition = composition, speed = 1f)
    }
}


