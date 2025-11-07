package com.programming.personalfinance.repository

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.programming.personalfinance.model.User
import com.programming.personalfinance.utils.AppUtils.DB_NAME
import com.programming.personalfinance.utils.AppUtils.USERS
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase,
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : UserRepository {

    companion object {
        var IS_LOGGED = "is_logged"
        var USER = "user"
    }

    override fun signInAccount(
        user: User,
        onResult: (success: Boolean, message: String) -> Unit
    ) {
        val databaseReference = firebaseDatabase.getReference(DB_NAME)
        val authTask = firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
        var listener: ValueEventListener? = null

        authTask
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = firebaseAuth.currentUser
                    val uid = firebaseUser?.uid

                    if (uid.isNullOrEmpty()) {
                        onResult(false, "User not exist...")
                        return@addOnCompleteListener
                    }
                    listener = object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val user = snapshot.getValue(User::class.java)
                            if (user != null) {
                                saveUser(user)
                                onResult(true, "Successfully Logged In")
                            } else {
                                onResult(false, "User data not found")
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            onResult(false, error.message)
                        }
                    }
                    databaseReference.child(USERS).child(uid)
                        .addListenerForSingleValueEvent(listener)
                } else {
                    onResult(false, task.exception?.message.toString())
                }
            }
            .addOnFailureListener { error ->
                onResult(false, "Failed to sign-in ${error.message}")
            }
    }

    override fun saveUser(user: User) {
        val userJson = gson.toJson(user)
        sharedPreferences.edit().putString(USER, userJson).putBoolean(IS_LOGGED, true).apply()
    }

    override fun saveUserAuth(
        user: User,
        onResult: (success: Boolean, message: String) -> Unit
    ) {
        val databaseReference = firebaseDatabase.getReference(DB_NAME).child(USERS)
        var listener: ValueEventListener? = null
        firebaseAuth
            .createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    val exception = task.exception
                    val errorMessage = when (exception) {
                        is FirebaseAuthInvalidCredentialsException -> "Invalid email format."
                        is FirebaseAuthUserCollisionException -> "Email is already in use."
                        else -> exception?.localizedMessage ?: "Sign-up failed. Please try again."
                    }
                    onResult(false, errorMessage)
                    return@addOnCompleteListener
                }
                // Sign-up success
                val uid = firebaseAuth.currentUser?.uid
                if (uid == null) {
                    onResult(false, "UID is null")
                    return@addOnCompleteListener
                }
                listener = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            onResult(false, "Email ID already exists.")
                            return
                        }
                        databaseReference.child(uid).setValue(user)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    onResult(true, "Your information saved successfully.")
                                } else {
                                    onResult(false, "Database Error: ${task.exception?.message}")
                                }
                            }.addOnFailureListener { error ->
                                onResult(false, "Failed to signup ${error.message}")
                            }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        onResult(false, "Database Error: " + error.message)
                    }
                }

                databaseReference.child(uid).addListenerForSingleValueEvent(listener)

            }

    }
}