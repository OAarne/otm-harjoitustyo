package zhuLi.ui.views

import tornadofx.ItemViewModel
import zhuLi.domain.Source

class SourceViewModel : ItemViewModel<Source>() {
    val id = bind(Source::idProperty)
    val title = bind(Source::titleProperty)
    val pubDate = bind(Source::pubDateProperty)
    val addDate = bind(Source::addDateProperty)
    val bibTex = bind(Source::bibTexProperty)
    val publisher = bind(Source::publisherProperty)

    // TODO: Source editing logic goes here?
}