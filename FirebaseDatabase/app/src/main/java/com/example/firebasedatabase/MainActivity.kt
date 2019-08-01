package com.example.firebasedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val db by lazy{
        FirebaseDatabase.getInstance().reference
    }

    val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var uid =""
        auth.addAuthStateListener {
            if(it.currentUser == null){
                auth.signInWithEmailAndPassword("abc@gmail.com","123456")
            }
            else{
                uid = it.uid ?: ""
                Toast.makeText(this,"${it.uid}",Toast.LENGTH_LONG).show()
            }
        }

        val ref = db.child("message/user/$uid")

        db.child("message")
            .child("user1")
            //.child("text")

    //second implement this
            .addChildEventListener(object :ChildEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }
                override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                }
                override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                    //if values are changed of a child
                }
                override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
                    tv.text = "${tv.text}    ${snapshot.getValue(Todo::class.java)?.text}"
                    //to get value of child key
                    //snapshot.key
                }
                override fun onChildRemoved(p0: DataSnapshot) {
                }
            })

    //first implement this
            /*.addValueEventListener(object: ValueEventListener {
                override fun onCancelled(p0: DatabaseError){

                }
                override fun onDataChange(snapshot: DataSnapshot){
                    //tv.text = snapshot.value.toString()
                    tv.text = snapshot.getValue(Todo::class.java)?.text
                }
            })*/

        btn.setOnClickListener {
            //ref.setValue("Hello")
            val todo = Todo()
            todo.text = et1.text.toString()
            todo.time = System.currentTimeMillis().toString()
            val key = ref.push().key
            ref.child(key!!).setValue(todo)
        }

    }
}


class Todo{
    var text: String = ""
    var time: String = ""
}








/*
{
    */
/* Visit https://firebase.google.com/docs/database/security to learn more about security rules. *//*

    "rules": {
    ".read": "auth.uid !== null",
    ".write": "auth.uid !== null"
}
}*/
