package input

import app.SkyscaperApp
import logger.Severity

object InputChecker {

    /**
     * Checks the given input for validity for the skyscraper tooling.
     *
     * This makes sure that the given input contains only two well formatted string arguments.
     */
    fun checkInput(input: Array<String>): Boolean {

        SkyscaperApp.logger.log(message = "Checking input ...")

        if (input.size != 2) {
            SkyscaperApp.logger.log(severity = Severity.ERROR, message = "Only two input arguments are allowed.")
            return false
        }

        SkyscaperApp.logger.log(message = "Input valid!")

        return true
    }

}
