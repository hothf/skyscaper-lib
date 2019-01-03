package app

import file.Filemanipulator
import input.InputChecker
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import logger.Logger
import logger.Severity
import logger.SkyscaperLogger

object SkyscaperApp {

    const val TIMEOUT_MS = 2_000L

    var logger: Logger = SkyscaperLogger

    fun perform(args: Array<String>): Boolean {

        var success = false

        runBlocking {

            val result = withTimeoutOrNull(TIMEOUT_MS) {
                if (InputChecker.checkInput(args)) {
                    success = performJob(args)
                }
            }

            if (result == null) {
                SkyscaperApp.logger.log(
                        severity = Severity.ERROR,
                        message = "Experienced a $TIMEOUT_MS ms Timeout!")
            }
        }
        return success
    }


    suspend fun performJob(args: Array<String>): Boolean {
        delay(3000)
        return Filemanipulator.perform(args)
    }
}