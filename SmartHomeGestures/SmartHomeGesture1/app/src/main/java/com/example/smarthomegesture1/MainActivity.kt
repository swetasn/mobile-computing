package com.example.smarthomegesture1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.Spinner1)
        val languages = resources.getStringArray(R.array.gestureItems)
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)

        // Gesture Items Dropdown
        spinner.adapter=adapter
        spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                when (languages[position]) {
                    "Digit 0" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Digit 1" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Digit 2" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Digit 3" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Digit 4" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Digit 5" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Digit 6" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Digit 7" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Digit 8" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Digit 9" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Turn On Fan" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Turn Off Fan" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Decrease Fan Speed" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Increase Fan Speed" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Turn Off Light" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Turn On Light" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    "Set Thermostat To Specified Temperature" -> {
                        intent = Intent(this@MainActivity, PreviewActivity::class.java)
                        intent.putExtra("gesture",languages[position])
                        Toast.makeText(this@MainActivity,
                                getString(R.string.video_preview) + " " +
                                        "" + languages[position], Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Note the block
                        print("x is neither 1 nor 2")
                    }
                }
                startActivity(intent)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }


}
