package com.kushalsharma.fairywallaapp.dao

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.kushalsharma.fairywallaapp.modals.User


class UserDao {

    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("Users")

    fun addUser(user: User?) {
        user?.let {

            GlobalScope.launch(Dispatchers.IO) {
                usersCollection.document(user.uid).set(it)
            }

        }
    }

    fun getUserById(uId: String): Task<DocumentSnapshot> {
        return usersCollection.document(uId).get()

    }


}
