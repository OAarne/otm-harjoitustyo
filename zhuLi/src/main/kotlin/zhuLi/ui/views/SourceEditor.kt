package zhuLi.ui.views

import javafx.beans.property.SimpleObjectProperty
import javafx.stage.FileChooser
import tornadofx.FileChooserMode
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.chooseFile
import tornadofx.datepicker
import tornadofx.field
import tornadofx.fieldset
import tornadofx.form
import tornadofx.textarea
import tornadofx.textfield
import java.awt.Desktop
import kotlin.concurrent.thread

class SourceEditor : View() {
    private val model: SourceViewModel by inject()

    override val root = form {
        fieldset {
            field("Title") {
                textfield(model.title)
            }
            field("File") {
                // TODO: Add some sort of indicator for what file is selected
                button("Choose File") {
                    //                model.file.bind(observableFile)
                    setOnAction {
                        val fileList = chooseFile("Choose File", arrayOf(FileChooser.ExtensionFilter("documents", listOf("*.pdf", "*.epub"))), FileChooserMode.Single, null, {})
                        if (fileList.isNotEmpty()) {
                            var observableFile = SimpleObjectProperty(fileList[0])
                            model.file.bind(observableFile)
                        }
                    }
                }
                button("Open File") {
                    setOnAction {
                        // TODO: I think this counts as logic in the view.
                        if (Desktop.isDesktopSupported()) {
                            thread(true, false, null, "zhuLi-viewer", -1, {
                                Desktop.getDesktop().open(model.file.value)
                            })
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
//                textfield(model.bibTex)
            }
            field("publisher") {
                textfield(model.publisher)
            }
            button("Save Changes") {
                action {
                    model.commit()
                }
            }
        }
        this.prefWidth = 350.0

    }
}