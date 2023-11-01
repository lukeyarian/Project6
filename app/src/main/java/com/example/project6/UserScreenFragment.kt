package com.example.project6

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class UserScreenFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_screen, container, false)

        auth = FirebaseAuth.getInstance()

        val emailEditText: EditText = view.findViewById(R.id.emailEditText)
        val passwordEditText: EditText = view.findViewById(R.id.passwordEditText)

        val loginButton: Button = view.findViewById(R.id.loginButton)
        val signUpButton: Button = view.findViewById(R.id.signUpButton)
        val signOutButton: Button = view.findViewById(R.id.signOutButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            signIn(email, password)
        }

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            signUp(email, password)
        }

        signOutButton.setOnClickListener {
            signOut()
        }

        return view
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Successfully Signed In", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Successfully Registered", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signOut() {
        auth.signOut()
        Toast.makeText(context, "Successfully Signed Out", Toast.LENGTH_SHORT).show()
    }
}