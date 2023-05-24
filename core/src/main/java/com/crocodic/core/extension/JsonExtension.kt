package com.crocodic.core.extension

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.io.IOException
import java.nio.charset.Charset

inline fun <reified ITEM> String.toObject(gson: Gson): ITEM {
    return gson.fromJson(this, ITEM::class.java)
}

inline fun <reified T> T.toJson(gson: Gson): String {
    return gson.toJson(this)
}

inline fun <reified ITEM> String.toList(gson: Gson): List<ITEM> {
    val vList = ArrayList<ITEM>()
    val arry = JsonParser().parse(this).asJsonArray
    for (jsonElement in arry) {
        vList.add(gson.fromJson(jsonElement, ITEM::class.java))
    }
    return vList
}

inline fun <reified ITEM> String.toList(): List<ITEM> {
    val vList = ArrayList<ITEM>()
    val arry = JsonParser().parse(this).asJsonArray
    for (jsonElement in arry) {
        vList.add(Gson().fromJson(jsonElement, ITEM::class.java))
    }
    return vList
}

fun loadJSONFromAsset(context: Context, fileName: String): String? {
    return try {
        val `is` = context.assets.open(fileName)
        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        String(buffer, Charset.defaultCharset())
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }

}