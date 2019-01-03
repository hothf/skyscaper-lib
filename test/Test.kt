import app.CompletionListener
import app.SkyscaperApp
import file.Filemanipulator
import input.InputChecker
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import javax.security.auth.callback.Callback


class Test {

    @Test
    fun `test the input checking`() {

        var input = arrayOf("")
        Assert.assertFalse(InputChecker.checkInput(input))

        input = arrayOf("", "")
        Assert.assertTrue(InputChecker.checkInput(input))

        input = arrayOf("", "", "")
        Assert.assertFalse(InputChecker.checkInput(input))
    }

    @Test
    fun `test the file manipulation functionality`() = runBlocking {

        val files = arrayOf("/Users/th/Desktop/tester/drawable-hdpi/tester_png.png", "/Users/th/Desktop/bla")

        Assert.assertTrue(Filemanipulator.perform(files))
    }

    @Test
    fun `test the app functionality async`() = runBlocking {

        val files = arrayOf("/Users/th/Desktop/tester/drawable-hdpi/tester_png.png", "/Users/th/Desktop/bla")

        var hasCompleted = false

        launch {

            SkyscaperApp.performAsync(
                    files,
                    object : CompletionListener {
                        override fun onCompleted(successful: Boolean) {
                            hasCompleted = true
                            Assert.assertTrue(successful)
                            println("Async Test completed")
                        }
                    })

            while (!hasCompleted) {
                delay(100)
            }
        }

        println("Async Test executed")
    }

    @Test
    fun `test the app functionality`() = runBlocking {

        val files = arrayOf("/Users/th/Desktop/tester/drawable-hdpi/tester_png.png", "/Users/th/Desktop/bla")

        Assert.assertTrue(SkyscaperApp.perform(files))
    }

}