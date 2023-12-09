package com.stbasarab.prog_3

import android.content.ClipboardManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    showVectors()
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    showVectors()
  }

  private fun showVectorBox(linearLayout: LinearLayout, text: String) {
    for (i in 0 until linearLayout.childCount) {
      val textView = linearLayout.getChildAt(i) as TextView
      textView.text = text
    }
  }

  private fun showVectors() {
    findViewById<LinearLayout>(R.id.main).post {
      val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
      val vector = clipboard.primaryClip?.getItemAt(0)?.text
      val sortedVector = vector!!.split(" ").sorted()
      showVectorBox(findViewById(R.id.top_box), sortedVector.joinToString("\n"))
      showVectorBox(findViewById(R.id.bottom_box), sortedVector.joinToString(" "))
      Toast.makeText(this, getString(R.string.sort_alert), Toast.LENGTH_SHORT).show()
    }
  }
}