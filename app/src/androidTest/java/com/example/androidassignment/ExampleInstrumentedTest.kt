package com.example.androidassignment

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Problem Statement: Build a app to render a custom UI. The app will have 2 modules:
Network Module
• Create a Network interface with 2 methods as
- fetchCustomUI (String URL): This will connect to a URL and return the JSON object found in the URL.
- fetchImage (String URL): This will connect to a URL and return the image data.
• Interface will be called by the App module to perform the operations.
• Create a jar/aar file and include this in the app module.
App Module:
• This contains all the UI elements and includes the jar/aar file.
• There will be 2 activities in the app.
• Activity 1 will call fetchCustomUI() and render the UI based on the JSON received.
• UI elements are to be sent to Activity 2 in the fields mentioned in the custom JSON
• Activity 2 will display all the elements passed by Activity 1.
• Errors and exceptions to be handled gracefully.
• Custom UI URL: https://demo.ezetap.com/mobileapps/android_assignment.json
Bonus:
• Be creative in the layouts.
• Use the latest android technologies
• Use the latest coding guidelines
• Happy Codin
 *
 *
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.androidassignment", appContext.packageName)
    }
}