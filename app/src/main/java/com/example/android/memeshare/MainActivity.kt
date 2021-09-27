package com.example.android.memeshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMeme()
    }


    private fun loadMeme(){
        val currentImageUrl = "https://i.redd.it/5t2246khdwp71.png"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, currentImageUrl, null,
            { response ->
                val url = response.getString("url")



            },
            { error ->
                Toast.makeText(this, "Something went wrong !!!", Toast.LENGTH_LONG).show()

            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


    fun shareMeme(view: android.view.View) {

    }
    fun nextMeme(view: android.view.View) {
        
    }

}