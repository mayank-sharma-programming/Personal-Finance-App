package com.programming.personalfinance.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreen2(moveToNext: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "\uD83D\uDCC8 Reach Your Financial Goals",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1E1E)
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Set budgets, plan savings, and get insights that help you stay on track and build a better financial future.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF4A4A4A)
                ),
                textAlign = TextAlign.Center,
                fontSize = TextUnit(14f, TextUnitType.Sp),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
        Button(
            onClick = moveToNext,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(30), // fully rounded corners
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50), // soft green
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text = "Get Started",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun OnboardingScreen2Preview() {
    OnboardingScreen2 {

    }
}