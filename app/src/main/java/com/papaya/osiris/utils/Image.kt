package com.papaya.osiris.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

private fun createTempFile(context: Context): File {
    val cacheDir = context.cacheDir
    return File.createTempFile("tempFile", null, cacheDir)
}

private fun writeInputStreamToFile(inputStream: InputStream, outputFile: File) {
    val outputStream = FileOutputStream(outputFile)
    val buffer = ByteArray(1024)
    var read: Int

    while (inputStream.read(buffer).also { read = it } != -1) {
        outputStream.write(buffer, 0, read)
    }

    inputStream.close()
    outputStream.flush()
    outputStream.close()
}

fun convertContentUriToFile(context: Context, contentUri: Uri): File? {
    val contentResolver: ContentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(contentUri)
    val file = createTempFile(context)

    if (inputStream != null) {
        writeInputStreamToFile(inputStream, file)
        return file
    }

    return null
}