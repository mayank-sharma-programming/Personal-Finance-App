package com.programming.personalfinance.utils

import androidx.compose.ui.graphics.Color
import java.util.Locale
import java.util.UUID

object AppUtils {
    val DB_NAME = "ExpensesInfo"
    val USERS = "users"
    val pieChartColors = mapOf(
        "Saving" to Color(0xFF1F77B4), // Dark Blue
        "Investment" to Color(0xFF9A4C07), // Dark Orange
        "Debt Payments" to Color(0xFF167816), // Dark Green
        "Healthcare" to Color(0xFF930B0C), // Dark Red
        "Shopping" to Color(0xFF660FB6), // Dark Purple
        "Entertainment" to Color(0xFF0E5F9A), // Brown
        "Education" to Color(0xFF820962), // Pink
        "Fitness & Sports" to Color(0xFF444141), // Gray
        "Transportation" to Color(0xFF646506), // Olive Green
        "House" to Color(0xFF045057), // Teal
        "Food" to Color(0xFF1E2176), // Dark Navy
        "Travel" to Color(0xFF805807)  // Dark Gold
    )

    fun generateRandomId(): String {
        return UUID.randomUUID().toString().replace("-", "").take(32)
    }
}