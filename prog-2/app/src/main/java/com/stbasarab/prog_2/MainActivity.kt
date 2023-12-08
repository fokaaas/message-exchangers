package com.stbasarab.prog_2

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val vector = getVector()
    showVectors(vector)
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    setIntent(intent)
    val vector = getVector()
    showVectors(vector)
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

  private fun showVectors(vector: DoubleArray) {
    val formattedVector = vector.map { "%.2f".format(it) }
    val verticalVector = formattedVector.joinToString("\n")
    val horizontalVector = formattedVector.joinToString(" ")
    showVectorBox(findViewById(R.id.top_box), verticalVector)
    showVectorBox(findViewById(R.id.bottom_box), horizontalVector)
  }
}