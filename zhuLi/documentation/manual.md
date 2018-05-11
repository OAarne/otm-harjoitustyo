# User's Manual

## Running the program

Unzip the distribution wherever and run `bin/zhuLi`.

## Using the program

By default, zhuLi will save the source data in a file called sources.json in the directory where it was run.
The sources will be loaded from there in the future.
The path of the .json file can be set by creating a file called `config.properties` in the working directory. The contents of the file should be `sourcePath=<path>`.

The form on the right can be used to edit the selected source.
New sources can be added by clicking "Add source" at the bottom of the screen.
Once changes have been made, they need to be committed to the table by clicking "Save Changes".
To save the data to the .json file, click "Save" at the bottom of the screen.

Clicking "Choose file" open a file explorer window to allow you to select a .pdf or .epub file to be associated with the source.
This file can then be opened in the default viewer by clicking "Open File".