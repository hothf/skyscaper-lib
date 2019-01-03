
# Skyscaper Resource Management Tool

Written purely with Kotlin and the kotlinx.coroutines library.


## Build

Build with IntelliJ IDE ("Build artifact") or via command line with:

`$ cd src/`

`$ kotlinc . -cp ../lib/kotlinx-coroutines-core-1.0.1.jar -include-runtime -d skyscaper.jar`

Note that the IDE approach generates a full jar for standalone usage.

## Usage

Given the build project as the "skyscaper.jar"-file and in the current
directory:

`$ java -jar skyscaper.jar /Users/you/Desktop/source/drawable-hdpi/tester.png", "/Users/you/Desktop/destination/res"`

will move all images named *tester.png* in up to two parent folders to the specified destination folder. This will also copy the parent folder of the corresponding source file.

**This means:** You can select a file from within one of many drawable-x folders and put the destination folder to the res folder of an android project. This will ensure, that any drawable-x folder will also be present at the destination path.
