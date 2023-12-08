package com.stbasarab.prog_1

import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  private lateinit var n: EditText
  private lateinit var min: EditText
  private lateinit var max: EditText
  private lateinit var sendButton: Button

  private val textWatcher = object : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
      sendButton.isEnabled = n.text.isNotEmpty() && min.text.isNotEmpty() && max.text.isNotEmpty()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    n = findViewById(R.id.n)
    min = findViewById(R.id.min)
    max = findViewById(R.id.max)
    sendButton = findViewById(R.id.send_button)

    n.addTextChangedListener(textWatcher)
    min.addTextChangedListener(textWatcher)
    max.addTextChangedListener(textWatcher)
    sendButton.isEnabled = false
  }

  fun onSendClick(view: View) {
    val intent = packageManager.getLaunchIntentForPackage("com.stbasarab.prog_2")
    intent!!.addFlags(FLAG_ACTIVITY_SINGLE_TOP)
    intent.putExtra("n", n.text.toString().toInt())
    intent.putExtra("min", min.text.toString().toDouble())
    intent.putExtra("max", max.text.toString().toDouble())
    startActivity(intent)
  }
}