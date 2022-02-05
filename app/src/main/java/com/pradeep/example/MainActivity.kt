package com.pradeep.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pradeep.garudable.GarudaBel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val garudaBel = GarudaBel(this)
        garudaBel.startBleScan()

    }
}