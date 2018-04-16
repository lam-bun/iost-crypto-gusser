package com.lambun.gusser.utils

import android.os.Environment
import com.lambun.gusser.BuildConfig
import com.wanhupai.dati.android.utils.LogUtils
import java.io.File

class FileUtils {

    companion object {

        private val EXTERNAL_STORAGE_DIRECTORY = Environment.getExternalStorageDirectory().toString()
        private val BASE_DIRECTORY = EXTERNAL_STORAGE_DIRECTORY + File.separator + "." + BuildConfig.APPLICATION_ID
        val FILE_PATH_KEY = EXTERNAL_STORAGE_DIRECTORY + File.separator + "key"

        init {
            mkdirs(BASE_DIRECTORY)
            mkdirs(FILE_PATH_KEY)
        }

        @JvmStatic
        fun mkdirs(file: File) {
            if (!file.exists()) {
                val flag = file.mkdirs()
                LogUtils.i("create directory [" + file.absolutePath + "] --> " + (if (flag) "succeed" else "failed"))
            }
        }

        @JvmStatic
        fun mkdirs(filePath: String) {
            mkdirs(File(filePath))
        }

        @JvmStatic
        fun isDirectoryEmpty(path: String): Boolean {
            val file = File(path)
            if (file.exists() && file.isDirectory) {
                val childs = file.listFiles()
                return !(childs != null && childs.isNotEmpty())
            }
            return false
        }

        @JvmStatic
        fun createNewFile(file: File) {
            if (!file.exists()) {
                val flag = file.createNewFile()
                LogUtils.i("create file [" + file.absolutePath + "] --> " + (if (flag) "succeed" else "failed"))
            }
        }

    }
}