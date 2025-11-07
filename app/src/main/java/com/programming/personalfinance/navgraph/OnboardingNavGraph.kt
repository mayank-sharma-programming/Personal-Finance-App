package com.programming.personalfinance.navgraph

sealed class OnboardScreen(val route: String) {
    object Onboarding1 : OnboardScreen("onboarding1")
    object Onboarding2 : OnboardScreen("onboarding2")
    object Login : OnboardScreen("login")
}
