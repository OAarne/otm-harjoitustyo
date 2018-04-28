# Requirements

## Purpose
The app allows tracking of sources and associated notes for multiple research projects.

## Features

### Completed
* Loading sources from JSON file
* Adding sources, each of which has:
	* title
	* date added
	* publication date
	* authors
	* a bibtex reference
* Editing source data with a form

### Core
* Saving sources to JSON file
* Adding sources, each of which has:
    * file(s?)
    * a markdown file for notes on the source
    * source URL
    * date of latest edit (note file or metadata)

### Additional
* Allow adding tags to sources to indicate which project(s) they are associated with
* sort/show sources by project
* to-read list
* Automatic generation of BibTex reference from manually added metadata (and vice versa?)
* Automatic generation of a .bib file from a selected set of sources
* display markdown note files compiled
* Allow barebones editing of note files