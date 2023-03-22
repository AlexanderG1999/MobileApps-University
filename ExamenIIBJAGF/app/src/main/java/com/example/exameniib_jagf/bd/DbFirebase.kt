package com.example.exameniib_jagf.bd

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DbFirebase(
    private var firebaseDatabase: FirebaseDatabase,
    private var dataBaseReference: DatabaseReference,
    context: Context
) {
    init {
        FirebaseApp.initializeApp(context)
        firebaseDatabase = FirebaseDatabase.getInstance()
        dataBaseReference = firebaseDatabase.getReference()
    }

    fun getFirebaseDatabase(): FirebaseDatabase {
        return this.firebaseDatabase
    }

    fun getDataBaseReference(): DatabaseReference {
        return this.dataBaseReference
    }
}