package com.example.android.memeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var currentImageUrl : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMeme()
    }


    private fun loadMeme(){
        currentImageUrl = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, currentImageUrl, null,
            { response ->
                val url = response.getString("url")
                Glide.with(this).load(url).into(imageView)

            },
            { error ->
                Toast.makeText(this, "Something went wrong !!!", Toast.LENGTH_LONG).show()

            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


    fun shareMeme(view: android.view.View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Checkout this cool meme i got on reddit... $currentImageUrl")
        val chooser = Intent.createChooser(intent , "Share this meme using....")
        startActivity(chooser)

    }
    fun nextMeme(view: android.view.View) {
        loadMeme()
    }

}