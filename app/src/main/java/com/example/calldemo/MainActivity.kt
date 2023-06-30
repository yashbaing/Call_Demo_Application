package com.example.calldemo

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.app.ActivityCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    val REQUEST_PHONE_CALL=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val callBtn=findViewById<FloatingActionButton>(R.id.floatingActionButton)
        callBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                if(ActivityCompat.checkSelfPermission(this@MainActivity,android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL)
                }
                else
                {
                    makeCall()
                }
            }
        })
    }

    private fun makeCall()
    {
        val phone=findViewById<EditText>(R.id.textPhone)
        val intent=Intent(Intent.ACTION_CALL, Uri.fromParts("tel",phone.text.toString(),null))
        startActivity(intent)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==REQUEST_PHONE_CALL)
        {
            makeCall()
        }
    }
}