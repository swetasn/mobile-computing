package com.example.smarthomegesture1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class PreviewActivity : AppCompatActivity() {
    private val REQUEST_VIDEO_CAPTURE = 1
    var path = ""
    var gesture: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        gesture = intent.getStringExtra("gesture")

        var i = 1
        var gestureVdoPreview: VideoView?
        gestureVdoPreview = findViewById<View>(R.id.videoView) as VideoView
        when (gesture) {
            "Digit 0" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.digit_0
                    )
                )
            }
            "Digit 1" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.digit_1
                    )
                )
            }
            "Digit 2" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.digit_2
                    )
                )
            }
            "Digit 3" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.digit_3
                    )
                )
            }
            "Digit 4" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.digit_4
                    )
                )
            }
            "Digit 5" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.digit_5
                    )
                )
            }
            "Digit 6" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.digit_6
                    )
                )
            }
            "Digit 7" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.digit_7
                    )
                )
            }
            "Digit 8" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.digit_8
                    )
                )
            }
            "Digit 9" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.digit_9
                    )
                )
            }
            "Turn On Fan" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.fan_on
                    )
                )
            }
            "Turn Off Fan" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.fan_off
                    )
                )
            }
            "Decrease Fan Speed" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.decrease_fan_speed
                    )
                )
            }
            "Increase Fan Speed" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.increase_fan_speed
                    )
                )
            }
            "Turn Off Light" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.light_off
                    )
                )
            }
            "Turn On Light" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.light_on
                    )
                )
            }
            "Set Thermostat To Specified Temperature" -> {
                gestureVdoPreview!!.setVideoURI(
                    Uri.parse(
                        "android.resource://"
                                + packageName + "/" + R.raw.set_thermo
                    )
                )
            }
            else -> { // Note the block
                print("x is neither 1 nor 2")
            }
        }

        gestureVdoPreview!!.start()

        // Replay button
        val replay: Button = findViewById(R.id.button_replay)
        // Click handler function for the Replay button
        replay.setOnClickListener {
            i++
            gestureVdoPreview!!.start()
        }

        // Back button
        val backButton: Button = findViewById(R.id.button_back)
        // Click handler function for the 'Back' button
        backButton.setOnClickListener {
            finish()
        }

        // Practice Button
        val practiceButton: Button = findViewById(R.id.button_practice)
        // Click handler function for the 'Practice' button
        practiceButton.setOnClickListener {
            startRecording()
        }
    }

    /**
     * Function to start the camera to record the practice video
     */
    fun startRecording() {
        Toast.makeText(this, "Record your video.", Toast.LENGTH_SHORT).show()
        val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 3)
        startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            if (data != null) {
                path = getMediaUriPath(data.data)
            }
        }
        if (data != null) {
            UploadService().upload(this, gesture!!, data.data!!)
                .onCompleteListener(object : UploadService.UploadListener {
                    override fun onFail() {
                        this@PreviewActivity.runOnUiThread(Runnable {
                            Toast.makeText(this@PreviewActivity, "Failed to upload the video. Please Try Again.", Toast.LENGTH_SHORT).show()
                        })
                    }

                    override fun onComplete() {
                        this@PreviewActivity.runOnUiThread(Runnable {
                            Toast.makeText(this@PreviewActivity, "Video Uploaded Successfully!", Toast.LENGTH_SHORT).show()
                            //navigateUpTo(Intent(baseContext, MainActivity::class.java))
                        })
                    }
                })
        }

    }

    fun getMediaUriPath(uri: Uri?): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri!!, proj, null, null, null)
        val colIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

        cursor.moveToFirst()
        path = cursor.getString(colIndex)
        cursor.close()

        return path
    }
}