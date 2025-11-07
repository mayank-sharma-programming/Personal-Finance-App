package com.programming.personalfinance.screen.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.programming.personalfinance.navgraph.OnboardScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController= rememberNavController()
            OnboardingNavGraph(navController)
        }
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
                LoginScreen(this@OnBoardingActivity)
            }
        }
    }
}
