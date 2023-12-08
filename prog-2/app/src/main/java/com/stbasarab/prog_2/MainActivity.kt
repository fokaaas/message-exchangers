package com.stbasarab.prog_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
  private var vector: DoubleArray? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val vector = getVector()
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    val vector = getVector()
  }

  private fun getVector(): DoubleArray {
    val n = intent!!.extras!!.getInt("n")
    val min = intent.extras!!.getDouble("min")
    val max = intent.extras!!.getDouble("max")
    val random = Random.Default
    return DoubleArray(n) { random.nextDouble(min, max) }
  }

  private fun showVector(vector: DoubleArray) {

  }
}