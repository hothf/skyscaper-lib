package app

import file.Filemanipulator
import input.InputChecker
import logger.Logger
import logger.SkyscaperLogger

object SkyscaperApp {

    var logger: Logger = SkyscaperLogger

    fun perform(args: Array<String>): Boolean{
        if (InputChecker.checkInput(args)){
            return Filemanipulator.perform(args)
        }
        return false
    }
}