package com.example.login.datasource

import com.example.login.listener.Listener
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class DataSource @Inject constructor() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun signUp(name: String, email: String, password: String, listener: Listener){
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
            listener.onFailure("Complete all fields")
        } else{
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {signup ->
                if (signup.isSuccessful){
                    val userID = FirebaseAuth.getInstance().currentUser?.uid.toString()

                    val map = hashMapOf(
                        "name" to name,
                        "email" to email,
                        "userid" to userID
                    )

                    db.collection("users").document(userID).set(map).addOnCompleteListener {
                        listener.onSuccess("Successful registration", "loginScreen")
                    }.addOnFailureListener {
                        listener.onFailure("Unexpected error")
                    }
                }
            }.addOnFailureListener {exception ->
                val error = when(exception){
                    is FirebaseAuthUserCollisionException -> "This account has already been registered"
//                    is FirebaseAuthInvalidCredentialsException -> ""
                    is FirebaseAuthWeakPasswordException -> "The password must contain at least 6 characters"
                    is FirebaseNetworkException -> "No internet connection"
                    else -> "Invalid email"
                }
                listener.onFailure(error)
            }
        }
    }

    fun login(email: String, password: String, listener: Listener){
        if (email.isEmpty() || password.isEmpty()){
            listener.onFailure("Complete all fields")
        }else{
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    listener.onSuccess("Login completed", "listScreen")
                }
            }.addOnFailureListener {exception ->
                val error = when(exception){
                    is FirebaseAuthInvalidCredentialsException -> "Incorrect password"
                    is FirebaseNetworkException -> "No internet connection"
                    else -> "Invalid email"
                }
                listener.onFailure(error)
            }
        }
    }

    fun recoverPassword(email: String, listener: Listener){
        if (email.isEmpty()){
            listener.onFailure("Complete all fields")
        }else {
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    listener.onSuccess("Check your email", "loginScreen")
                }
            }.addOnFailureListener {exception ->
                val error = when(exception){
                    is FirebaseAuthUserCollisionException -> "This account has already been registered"
                    is FirebaseAuthInvalidCredentialsException -> "Invalid email"
                    is FirebaseNetworkException -> "No internet connection"
                    else -> "Error"
                }
                listener.onFailure(error)
            }
        }
    }
}