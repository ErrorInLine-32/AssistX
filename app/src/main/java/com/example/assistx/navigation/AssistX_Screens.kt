package com.example.assistx.navigation

enum class AssistX_Screens {
    PreSplashScreen,
    SplashScreen,
    LoginScreen,
    SignUpScreen,
    HomeScreen,
    DetailsScreen;
    companion object {
        fun fromRoute(route: String) : AssistX_Screens
                = when(route?.substringBefore("/")) {
            PreSplashScreen.name -> PreSplashScreen
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            SignUpScreen.name -> SignUpScreen
            HomeScreen.name -> HomeScreen
            DetailsScreen.name -> DetailsScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route is not recognised")
        }
    }
}
