package zhuLi.ui.views

import tornadofx.View
import tornadofx.action
import tornadofx.bind
import tornadofx.button
import tornadofx.datepicker
import tornadofx.field
import tornadofx.fieldset
import tornadofx.form
import tornadofx.hbox
import tornadofx.label
import tornadofx.listview
import tornadofx.textarea
import tornadofx.textfield
import tornadofx.toProperty
import tornadofx.vbox
import zhuLi.ui.SourceViewModel

class SourceEditor : View() {
    private val model: SourceViewModel by inject()

    override val root = form {
        fieldset {
            field("Title") {
                textfield(model.title)
            }
            field("Authors\n(Right click to delete)") {
                vbox {
                    textfield {
                        promptText = "Add author"
                        action {
                            model.addAuthor(text)
                            clear()
                        }
                    }
                    listview(model.authors) {
                        setOnContextMenuRequested { model.removeAuthor(this.selectionModel.selectedItem) }
                    }
                }
            }
            field("File") {
                // TODO: Make it display the selected file in some comprehensible format
                vbox {
                    label() {
                        bind(model.file.toProperty().asString())
                    }
                    hbox {
                        button("Choose File") {
                            action { model.setFile() }
                        }
                        button("Open File") {
                            action { model.openFile() }
                        }
                    }
                }
            }
            field("Authors") {
            }
            field("Publication Date") {
                datepicker(model.pubDate)
            }
            field("BibTex") {
                textarea(model.bibTex)
            }
            field("publisher") {
                textfield(model.publisher)
            }
            vbox {
                button("Save Changes") {
                    action { model.commit() }
                }
                button("Discard Changes") {
                    action { model.rollback() }
                }
            }
        }

        this.prefWidth = 350.0
    }
}