package com.stbasarab.prog_2

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
  private lateinit var vector: DoubleArray

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    vector = getVector()
    showVectors(vector)
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    setIntent(intent)
    vector = getVector()
    showVectors(vector)
  }

  fun onSortClick(view: View) {
    val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("vector", doubleArrayToString(vector, " "))
    clipboard.setPrimaryClip(clip)
    val intent = packageManager.getLaunchIntentForPackage("com.stbasarab.prog_3")
    intent!!.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    startActivity(intent)
  }

  private fun getVector(): DoubleArray {
    val n = intent!!.extras!!.getInt("n")
    val min = intent.extras!!.getDouble("min")
    val max = intent.extras!!.getDouble("max")
    val random = Random.Default
    return DoubleArray(n) { random.nextDouble(min, max) }
  }

  private fun showVectorBox(linearLayout: LinearLayout, text: String) {
    for (i in 0 until linearLayout.childCount) {
      val textView = linearLayout.getChildAt(i) as TextView
      textView.text = text
    }
  }

  private fun doubleArrayToString(doubleArray: DoubleArray, separator: String): String {
    val stringList = doubleArray.map { "%.2f".format(it) }
    return stringList.joinToString(separator)
  }

  private fun showVectors(vector: DoubleArray) {
    val verticalVector = doubleArrayToString(vector, "\n")
    val horizontalVector = doubleArrayToString(vector, " ")
    showVectorBox(findViewById(R.id.top_box), verticalVector)
    showVectorBox(findViewById(R.id.bottom_box), horizontalVector)
  }
}