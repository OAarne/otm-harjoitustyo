package zhuLi.ui.views

import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.datepicker
import tornadofx.field
import tornadofx.fieldset
import tornadofx.form
import tornadofx.textarea
import tornadofx.textfield

class SourceEditor : View() {
    private val model: SourceViewModel by inject()

    override val root = form {
        fieldset {
            field("Title") {
                textfield(model.title)
            }
            field("Authors") {
            }
            field("Publication Date") {
                datepicker(model.pubDate)

            }
            // TODO: make this a bigger field
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

    }
}