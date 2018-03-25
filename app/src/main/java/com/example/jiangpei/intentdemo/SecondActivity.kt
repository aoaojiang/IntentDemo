package com.example.jiangpei.intentdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        val username = if (intent.extras["username"] == null) "John Doe" else intent.extras.get("username")
//        textView3.text = "Welcome, " + username + "!"
    }

    fun onReturnClicked(view: View) {
        val intent = Intent()
        intent.putExtra("result", "Result: from Second Activity.")
        setResult(0, intent)

        finish()
    }
}
