package file

import app.SkyscaperApp
import logger.Severity
import java.io.File

object Filemanipulator {

    fun perform(args: Array<String>): Boolean {

        SkyscaperApp.logger.log(message = "--- Performing copy and delete start---")

        SkyscaperApp.logger.log(message = "Checking files ...")

        val originFile = File(args[0])

        if (originFile.isDirectory) {
            SkyscaperApp.logger.log(Severity.ERROR, "${args[0]} is a directory.")
            return false
        }

        val destinationDir = File(args[1]).apply { mkdir() }

        val copiedFiles = mutableListOf<File>()

        originFile.parentFile.parentFile.walkTopDown().forEach {

            if (it.name == originFile.name) {
                SkyscaperApp.logger.log(message = "Found \"${originFile.name}\" in \"${it.parentFile.path}\"")

                val dir = File(destinationDir.path + File.separator + it.parentFile.name).apply { mkdir() }

                val destinationFile = File(dir.path + File.separator + it.name)

                originFile.copyTo(destinationFile, true)

                SkyscaperApp.logger.log(message = "Copied \"${destinationFile.name}\" to \"${dir.path}\".")

                copiedFiles.add(it)
            }
        }

        copiedFiles.forEach { it.delete() }

        SkyscaperApp.logger.log(message = "Clean up: source files deleted.")

        SkyscaperApp.logger.log(message = "--- Performing copy and delete completed ---")

        return true
    }

}