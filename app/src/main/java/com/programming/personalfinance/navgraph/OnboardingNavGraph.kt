package com.programming.personalfinance.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.programming.personalfinance.screen.onboarding.LoginScreen
import com.programming.personalfinance.screen.onboarding.OnboardingScreen1
import com.programming.personalfinance.screen.onboarding.OnboardingScreen2

sealed class OnboardScreen(val route: String) {
    object Onboarding1 : OnboardScreen("onboarding1")
    object Onboarding2 : OnboardScreen("onboarding2")
    object Login : OnboardScreen("login")
}

@Composable
fun OnboardingNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = OnboardScreen.Onboarding1.route
    ) {
        composable(OnboardScreen.Onboarding1.route) {
            OnboardingScreen1 {
                navHostController.navigate(OnboardScreen.Onboarding2.route) {
                    popUpTo(OnboardScreen.Onboarding1.route) { inclusive = true }
                }
            }
        }
        composable(OnboardScreen.Onboarding2.route) {
            OnboardingScreen2 {
                navHostController.navigate(OnboardScreen.Login.route) {
                    popUpTo(OnboardScreen.Onboarding2.route) { inclusive = true }
                }
            }
        }
        composable(OnboardScreen.Login.route) {
            LoginScreen {

            }
        }
    }
}
