package file

import app.SkyscaperApp
import kotlinx.coroutines.yield
import logger.Severity
import java.io.File

object Filemanipulator {

    /**
     * Performs the task of copying the first input path file (args[0]) to the the given path directory (args[1]) in
     * the second argument. Also will copy all file occurrences in up to two parent folders of the file and copy it with
     * the corresponding parent folder.
     *
     * The name of the file can be changed optionally with the fileName parameter. Leave null for no name changing.
     *
     */
    suspend fun perform(args: Array<String>, fileName: String? = null): Boolean {

        SkyscaperApp.logger.log(message = "Checking files ...")

        val originFile = File(args[0])

        if (originFile.isDirectory) {
            SkyscaperApp.logger.log(Severity.ERROR, "Source \"${args[0]}\" is a directory.")
            return false
        }

        if (!File(args[1]).isDirectory) {
            SkyscaperApp.logger.log(Severity.ERROR, "Destination \"${args[1]}\" is not a directory.")
            return false
        }

        val destinationDir = File(args[1]).apply { mkdir() }

        val copiedFiles = mutableListOf<File>()

        originFile.parentFile.parentFile.walkTopDown().forEach {

            yield()

            if (it.name == originFile.name) {
                SkyscaperApp.logger.log(message = "Found \"${originFile.name}\" in \"${it.parentFile.path}\"")

                val dir = File(destinationDir.path + File.separator + it.parentFile.name).apply { mkdir() }

                val destinationFile = File(dir.path + File.separator + (fileName ?: it.name))

                it.copyTo(destinationFile, true)

                SkyscaperApp.logger.log(message = "Copied \"${destinationFile.name}\" to \"${dir.path}\".")

                copiedFiles.add(it)
            }
        }

        copiedFiles.forEach { it.delete() }

        SkyscaperApp.logger.log(message = "Clean up: source files deleted.")

        return true
    }

}