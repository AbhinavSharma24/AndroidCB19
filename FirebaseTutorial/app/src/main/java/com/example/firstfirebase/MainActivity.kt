package com.example.firstfirebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    var verificationId:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*auth.addAuthStateListener {
            if(it.currentUser != null){
                signupbtn.isVisible = false
                signoutbtn.isVisible = true
            }
            else{
                signupbtn.isVisible = true
                signoutbtn.isVisible = false
            }
        }*/

        signoutbtn.setOnClickListener {
            //Toast.makeText(this,"Signed Out Successfully",Toast.LENGTH_LONG).show()
            //auth.signOut()
            val phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId,password.editText?.text.toString())
            createAccount(phoneAuthCredential)
        }

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
                Toast.makeText(this@MainActivity,"Verification Completed", Toast.LENGTH_LONG).show()
                createAccount(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
                Toast.makeText(this@MainActivity,"Verification Failed", Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(p0, p1)
                verificationId = p0!!
                Toast.makeText(this@MainActivity,"Code Sent", Toast.LENGTH_LONG).show()
            }
        }


            signupbtn.setOnClickListener {
            /*if (email.editText?.text.isNullOrEmpty()) {
                email.isErrorEnabled = true
                email.error = "Cannot be Empty"
            } else if (password.editText?.text.isNullOrEmpty()) {
                password.isErrorEnabled = true
                password.error = "Cannot be Empty"
            } else {
                auth.createUserWithEmailAndPassword(
                    email.editText?.text.toString(),
                    password.editText?.text.toString()
                ).addOnCompleteListener {
                    signupbtn.isEnabled = false
                }.addOnSuccessListener {
                    signupbtn.isEnabled = true
                    Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_LONG).show()
                    email.editText?.setText("")
                    password.editText?.setText("")
                }.addOnFailureListener {
                    signupbtn.isEnabled = true
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                    if(it.localizedMessage.contains("use",true)){
                        loginUser()
                    }
                    email.editText?.setText("")
                    password.editText?.setText("")
                }
            }*/

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91${email.editText?.text.toString()}", // Phone number to verify
                60, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                this, // Activity (for callback binding)
                callbacks) // OnVerificationStateChangedCallbacks
        }

}

    private fun createAccount(p0: PhoneAuthCredential?) {
        auth.signInWithCredential(p0!!).addOnCompleteListener {

        }.addOnSuccessListener {

        }.addOnFailureListener {

        }
    }


    /*private fun loginUser(){
        auth.signInWithEmailAndPassword(
            email.editText?.text.toString(),
            password.editText?.text.toString()
        ).addOnCompleteListener {
            signupbtn.isEnabled = false
        }.addOnSuccessListener {
            signupbtn.isEnabled = true
            Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            signupbtn.isEnabled = true
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
            if(it.localizedMessage.contains("use",true)){
                loginUser()
            }
        }

    }*/
}
