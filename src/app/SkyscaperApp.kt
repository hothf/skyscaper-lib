package app

import file.Filemanipulator
import input.InputChecker
import kotlinx.coroutines.*
import logger.Logger
import logger.Severity
import logger.SkyscaperLogger

/**
 * The skyscaper tool entry point.
 */
object SkyscaperApp {

    private const val TIMEOUT_MS = 2_000L

    var logger: Logger = SkyscaperLogger

    /**
     * Performs asynchronously the tooling. The callback informs about the success or failure.
     */
    fun performAsync(args: Array<String>, callback: (Boolean) -> Unit) {

        GlobalScope.launch {

            val job = withTimeoutOrNull(TIMEOUT_MS) {
                callback(perform(args))
            }

            if (job == null) {
                SkyscaperApp.logger.log(
                        severity = Severity.ERROR,
                        message = "Experienced a $TIMEOUT_MS ms Timeout!")
                callback(false)
            }
        }
    }

    /**
     * Performs the tooling.
     */
    fun perform(args: Array<String>): Boolean {
        if (InputChecker.checkInput(args)) {
            return Filemanipulator.perform(args)
        }
        return false
    }
}