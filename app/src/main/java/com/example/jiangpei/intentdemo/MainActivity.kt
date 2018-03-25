package com.example.jiangpei.intentdemo

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    val ACTION = "shnu.myaction"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSecondActivityClicked(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("username", editText1.text)

        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = data?.extras?.get("result").toString()

        textView4.text = result
    }

    fun onSimpleActionClicked(view: View) {
        val intent = Intent("shnu.myaction")
        //intent.putExtra("username", editText1.text)
        //Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    fun onDialerClicked(view: View) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:12345678"))
        startActivity(intent)
    }

    fun onCallClicked(view: View) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:12345678"))
        startActivity(intent)
    }

    fun onSmsClicked(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:123456"))
        intent.putExtra("sms_body", "Hello")

        startActivity(intent)
    }

    fun onEmailClicked(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:jiang.pei@shnu.edu.cn"))
        startActivity(intent)
    }

    fun onSendEmailClicked(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        val tos = arrayOf("jiang.pei@shnu.edu.cn", "18917944949@189.cn")

        intent.putExtra(Intent.EXTRA_EMAIL, tos)
        intent.putExtra(Intent.EXTRA_TEXT, "Hello, world!")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello")
        intent.setType("message/rfc882")

        startActivity(intent)
    }

    fun onWebBrowerClicked(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.shnu.edu.cn"))
        startActivity(intent)
    }

    fun onMapClicked(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:33.77,133.33"))
        startActivity(intent)
    }

    fun onMP3Clicked(view: View) {

        try {
            val myIntent = Intent(android.content.Intent.ACTION_VIEW)
            val file = File(Environment.getExternalStorageDirectory(), "test_cbr.mp3")
            val extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString())
            val mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)

            toast(mimetype)
            myIntent.setDataAndType(Uri.fromFile(file), mimetype)
            startActivity(myIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun onCameraClicked(view: View) {
//        val sdDir = Environment.getExternalStorageDirectory()
//        val files = sdDir.listFiles()
//        if (files == null) textView5.text = "empty"
//        else textView5.text = files[0].name
//        toast(sdDir.absolutePath)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val time = Calendar.getInstance().timeInMillis

        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath + "/pic" + time + ".jpg")))

        startActivityForResult(intent, 0)
    }

    fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
