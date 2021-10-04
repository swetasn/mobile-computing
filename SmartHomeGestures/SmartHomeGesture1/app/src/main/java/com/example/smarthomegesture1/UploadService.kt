package com.example.smarthomegesture1

import android.content.Context
import android.net.Uri
import android.preference.PreferenceManager

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException

class UploadService {
    private val client = OkHttpClient()

    interface UploadListener {
        fun onComplete()
        fun onFail()
    }

    private var listener: UploadListener? = null

    fun upload(context: Context, gesture: String, file: Uri): UploadService {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val user = pref.getString("username", "NAYAK")
        val server = pref.getString("server", "http://192.168.1.9:5000/upload")

        val cursor = context.contentResolver.query(
            file,
            Array<String>(1) { android.provider.MediaStore.Video.VideoColumns.DATA },
            null,
            null,
            null
        )
        cursor!!.moveToFirst()
        val filePath = cursor.getString(0)
        cursor.close()
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("user", user!!)
            .addFormDataPart("gesture", gesture)
            .addFormDataPart(
                "file", "file.mp4",
                File(filePath).asRequestBody("video/mp4".toMediaType())
            )
            .build()

        val request = Request.Builder()
            .url(server!!)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                listener!!.onFail()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    listener!!.onComplete()
                }
            }
        })

        return this
    }

    fun onCompleteListener(l: UploadListener) {
        listener = l
    }
}
