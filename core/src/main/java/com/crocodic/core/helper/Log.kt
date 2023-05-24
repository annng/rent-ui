package com.crocodic.core.helper

import android.util.Log
import java.util.*

class Log {
    companion object {


        var logger: ILog = object : ConCreateLog() {
            override val tag: String
                get() = "vv"

            override val isDebug: Boolean
                get() = false
        }

        fun d(msg: Any) {
            logger.d(msg.toString())
        }

        fun w(e: Throwable?) {
            logger.w(e)
        }

        fun e(msg: Any) {
            logger.e(msg.toString())
        }

        fun e(msg: Any, e: Throwable?) {
            logger.e(msg.toString(), e)
        }


        private val EXCLUDE_CLASSES =
            Arrays.asList(
                "dalvik.system.VMStack", "java.lang.Thread",
                Log::class.java.canonicalName
            )

        private fun getMethodName(ignoreDepth: Int): String? {
            var ignoreDepth = ignoreDepth
            val stacks =
                Thread.currentThread().stackTrace
            var find = false
            for (i in stacks.indices) {
                val stack = stacks[i]
                val stackClass = stack.className
                if (!find && !EXCLUDE_CLASSES.contains(stackClass)) {
                    find = true
                }
                if (find && ignoreDepth == 0) {
                    return stackToString(stack)
                } else if (find) {
                    ignoreDepth--
                }
            }
            return null
        }

        private fun stackToString(stack: StackTraceElement): String {
            return stack.fileName
                .replace(".java", "") +
                    "#" +
                    stack.methodName +
                    "/L" +
                    stack.lineNumber
        }
    }


    interface ILog {
        fun d(msg: String)
        fun w(e: Throwable?)
        fun e(msg: String)
        fun e(msg: String, e: Throwable?)
        val tag: String
        val isDebug: Boolean
        fun preProcessMessage(msg: String): String
    }

    abstract class ConCreateLog : ILog {
        override fun d(msg: String) {
            if (isDebug) Log.d(tag, preProcessMessage(msg))
        }

        override fun w(e: Throwable?) {
            if (isDebug) Log.d(tag, preProcessMessage(""), e)
        }

        override fun e(msg: String) {
            if (isDebug) Log.e(tag, preProcessMessage(msg))
        }

        override fun e(msg: String, e: Throwable?) {
            if (isDebug) Log.e(tag, preProcessMessage(msg), e)
        }

        override fun preProcessMessage(msg: String): String {
            return getMethodName(3) + " " + msg
        }
    }
}