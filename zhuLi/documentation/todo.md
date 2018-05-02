## Things left to do for week 6 deadline

The plan:
Focus primarily on developing the app until wednesday, which will be dedicated to testing/documentation etc.

### Necessary

#### The actual app
* Keyboard shortcuts for saving and other common functions.
* Add the rest of the fields:
	* an editable list of authors
	* a note file
	* 
* Add a config file for setting data and document locations. (see example app)
* Try to figure out the architechture and package structure better
	* Maybe just get rid of the controller, and move all the logic into the listmodel?
* Figure out how to link to local files or something
	* Possibility: Set a directory (default ~/Documents) from which files are searched recursively
	* Maybe have a designated file where all the files (or symlinks) are loaded from
	* Possivly just have a field with a path, but that pah can also be relative to some set of directories listed in some config file

#### Other stuff
* Keep the hours logged __continuously__.
* Write javadocs
* Add pretty pictures to architecture document (also make architecture pretty-picture worthy)
* Make README.md nice, compare to example app.

### Extra Credit
* Make a sequence diagram of a central part of the program's functionality (making and saving a change?)
* Generate UML diagram of core classes and embed it into architecture.md in documentation

### Done
* Add a more detailed user's manual
* Actually write an architechture document
* min 20% test coverage
* Exclude UI code from test report
* Keep the hours logged
* Make a release
* Add a form for adding and editing sources (Inc. a date picker?)
* Make gradle check pass
* Get test coverage above 60%
