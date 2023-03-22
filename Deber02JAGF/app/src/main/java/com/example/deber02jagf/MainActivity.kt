package com.example.deber02jagf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.deber02jagf.model.Email
import com.example.deber02jagf.model.Emails
import java.util.concurrent.RecursiveAction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_main)
        inicializarRecyclerView(Emails.fakeEmails(), recyclerView)
    }

    fun inicializarRecyclerView(
        lista: List<Email>,
        recyclerView: RecyclerView
    ) {
        val adaptador = EmailAdapter(
            lista
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }
}
