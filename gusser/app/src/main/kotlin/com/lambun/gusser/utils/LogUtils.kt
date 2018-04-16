package com.wanhupai.dati.android.utils

import android.util.Log
import com.lambun.gusser.BuildConfig

/**
 * Created by lambun on 15/10/2017.
 */
class LogUtils private constructor() {

    companion object {
        private var sClassName: String? = null
        private var sMethodName: String? = null
        private var sLineNumber: Int = 0

        private fun isDebuggable(): Boolean {
            return BuildConfig.DEBUG
        }

        private fun createLog(log: String): String {
            val buffer = StringBuffer()
            buffer.append("[")
            buffer.append(sMethodName)
            buffer.append(":")
            buffer.append(sLineNumber)
            buffer.append("]")
            buffer.append(log)
            return buffer.toString()
        }

        private fun getMethodNames(sElements: Array<StackTraceElement>) {
            sClassName = sElements[1].fileName
            sMethodName = sElements[1].methodName
            sLineNumber = sElements[1].lineNumber
        }

        fun e(message: String) {
            if (!isDebuggable())
                return
            // Throwable instance must be created before any methods
            getMethodNames(Throwable().stackTrace)
            Log.e(sClassName, createLog(message))
        }

        fun e(e: Throwable) {
            if (!isDebuggable())
                return
            // Throwable instance must be created before any methods
            getMethodNames(Throwable().stackTrace)
            Log.e(sClassName, Log.getStackTraceString(e))
        }

        fun i(message: String) {
            if (!isDebuggable())
                return
            getMethodNames(Throwable().stackTrace)
            Log.i(sClassName, createLog(message))
        }

        fun d(message: String) {
            if (!isDebuggable())
                return
            getMethodNames(Throwable().stackTrace)
            Log.d(sClassName, createLog(message))
        }

        fun v(message: String) {
            if (!isDebuggable())
                return
            getMethodNames(Throwable().stackTrace)
            Log.v(sClassName, createLog(message))
        }

        fun w(message: String) {
            if (!isDebuggable())
                return
            getMethodNames(Throwable().stackTrace)
            Log.w(sClassName, createLog(message))
        }

        fun wtf(message: String) {
            if (!isDebuggable())
                return
            getMethodNames(Throwable().stackTrace)
            Log.wtf(sClassName, createLog(message))
        }
    }
}