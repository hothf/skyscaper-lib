package logger

/**
 * A skyscaper logger uses the default print mechanics for printing info to the console.
 */
object SkyscaperLogger : Logger {

    var debug: Boolean = true

    private var lastSeverity : Severity? = null

    private val filter = arrayListOf<Severity>()

    override fun getSeverityFilter(): ArrayList<Severity> {
        return filter
    }

    override fun log(severity: Severity, message: String) {

        if (filter.contains(severity)) {
            return
        }

        if (debug) {

            val prefix = when (severity) {
                Severity.INFO -> "||-> Info: "
                Severity.DEBUG -> "||-> Debug: "
                Severity.WARNING -> "||-> WARNING: "
                Severity.ERROR -> "||-> ERROR: "
            }

            if (lastSeverity != severity){
                println()
            }

            lastSeverity = severity


            println(prefix + message)

        }

    }

}