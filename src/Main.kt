import app.SkyscaperApp

/**
 * Welcome to the skyscaper tool.
 *
 * The starting point of this application.
 *
 * Command line usage:
 * 1. specify the input file path A
 * 2. specify the desired file path B
 *
 * Example
 * `java -jar skyscraper ../src/drawable-mdpi/ic_hello ../src/othermodule/
 */
fun main(args: Array<String>) {

    SkyscaperApp.performAsync(args){}

}