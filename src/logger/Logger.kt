package logger

enum class Severity { INFO, DEBUG, WARNING, ERROR }

/**
 * A interface for different logger implementations.
 */
interface Logger {

    fun getSeverityFilter(): ArrayList<Severity>

    /**
     * Logs a message with the given severity
     */
    fun log(severity: Severity = Severity.INFO, message: String)
}



