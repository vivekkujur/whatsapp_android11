package com.game.whatsapp_android11

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var shareBtn = findViewById<Button>(R.id.shareBtn)
        shareBtn.setOnClickListener {
            share("test share")
        }


    }

    fun share(msg: String) {

        val imgBitmap = BitmapFactory.decodeResource(resources, R.drawable.share_image)
        val imgBitmapPath =
            MediaStore.Images.Media.insertImage(getContentResolver(), imgBitmap, "title", null)
        val imgBitmapUri = Uri.parse(imgBitmapPath)

        try {
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, msg)
            whatsappIntent.putExtra(Intent.EXTRA_STREAM, imgBitmapUri);
            whatsappIntent.setType("image/jpeg");
            whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(whatsappIntent)
        } catch (e: Exception) {

        }

    }

}