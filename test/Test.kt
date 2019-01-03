import app.SkyscaperApp
import file.Filemanipulator
import input.InputChecker
import org.junit.Assert
import org.junit.Test


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
    fun `test the file manipulation functionality`() {

        var files = arrayOf("/Users/th/Desktop/tester/drawable-hdpi/tester_png.png", "/Users/th/Desktop/bla")
        Assert.assertTrue(Filemanipulator.perform(files))

        //files = arrayOf("/Users/th/Desktop/tester/drawable-hdpi/tester_png.png", "ddd")
        //Assert.assertTrue(Filemanipulator.perform(files))
    }

    @Test
    fun `test the app functionality`() {

        var files = arrayOf("/Users/th/Desktop/tester/drawable-hdpi/tester_png.png", "/Users/th/Desktop/bla")
        Assert.assertTrue(SkyscaperApp.perform(files))

        //files = arrayOf("/Users/th/Desktop/tester/drawable-hdpi/tester_png.png", "ddd")
        //Assert.assertTrue(Filemanipulator.perform(files))
    }


}