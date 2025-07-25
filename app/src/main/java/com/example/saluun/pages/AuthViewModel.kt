package com.example.saluun.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel: ViewModel() {

    private val _auth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun checkAuthState() {
        if(_auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        }else {
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(email: String, password: String){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return

        }
        _auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Unknown error")
                }
            }
    }

    fun signup(email: String, password: String){

        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return

        }
        _auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Unknown error")
                }
            }
    }

    fun signOut(){
        _auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }


}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}