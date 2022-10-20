package com.example.intentimplicite

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.app.ShareCompat

class MainActivity : AppCompatActivity() {
    lateinit var mWebsiteEditText: EditText
    lateinit var mLocationEditText: EditText
    lateinit var mShareTextEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);
    }

    fun openWebsite(view: View?) {
        // Get the URL text.
        val url = mWebsiteEditText!!.text.toString()
        // Parse the URI and create the intent.
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }
    fun openLocation(view: View?) {
        // Get the string indicating a location. Input is not validated; it is
        // passed to the location handler intact.
        // Get the string indicating a location. Input is not validated; it is
        // passed to the location handler intact.
        val loc = mLocationEditText.text.toString()

        // Parse the location and create the intent.

        // Parse the location and create the intent.
        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)

        // Find an activity to handle the intent, and start that activity.

        // Find an activity to handle the intent, and start that activity.
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }

    }
    fun shareText(view: View?) {
        val txt: String = mShareTextEditText.getText().toString()
        val mimeType = "text/plain"
        val shareIntent = ShareCompat.IntentBuilder(this)
        shareIntent.setType(mimeType)
            .setChooserTitle(R.string.share_text_with)
            .setText(txt)
            .startChooser()
    }
}