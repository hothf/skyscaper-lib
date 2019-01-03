package app

import file.Filemanipulator
import input.InputChecker
import kotlinx.coroutines.*
import logger.Logger
import logger.Severity
import logger.SkyscaperLogger

/**
 * Listens for the finishing of the skyscaper task.
 */
interface CompletionListener {

    /**
     * Called on completion of the skyscaper task.
     *
     * @param successful true if successful, false otherwise
     */
    fun onCompleted(successful: Boolean)
}

/**
 * The skyscaper tool entry point.
 */
object SkyscaperApp {

    private const val TIMEOUT_MS = 5_000L

    var logger: Logger = SkyscaperLogger

    /**
     * Performs asynchronously the tooling. The optional callback informs about the success or failure.
     */
    fun performAsync(args: Array<String>, onComplete: CompletionListener? = null) {

        GlobalScope.launch {

            val job = withTimeoutOrNull(TIMEOUT_MS) {
                onComplete?.onCompleted(perform(args))
            }

            if (job == null) {
                SkyscaperApp.logger.log(
                        severity = Severity.ERROR,
                        message = "Experienced a $TIMEOUT_MS ms Timeout!")

                onComplete?.onCompleted(false)
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