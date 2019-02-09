package com.clay.informhalal

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.clay.informhalal", appContext.packageName)

        val jsonFromAsset = loadJSONFromAsset(appContext)

        println("jsonFromAsset = ${jsonFromAsset}")
        Log.d("test", jsonFromAsset)
    }

    fun loadJSONFromAsset(context: Context): String {
        var json: String = "FAILED 0"
        try {
            val input = context.assets.open("response.json")
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            json = String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            return "FAILED -1"
        }
        return json
    }
}
