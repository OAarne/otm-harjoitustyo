# Software Architechture of zhuLi

zhuLi uses a three-tier architecture, with a UI Layer, a Service Layer, and a Data Access Layer.

The UI layer is driven by the SourceController, which is a questionable decision, and I might just eliminate it/merge with SourceListModel.
A Model-View-ViewModel pattern is used to mediate between the view and the service layer.

The core of the service layer is the SourceService, which mediates between the dao and the UI, and contains business logic.

The dao layer consists of a SourceListDao interface.
It is implemented by the abstract class JsonSourceListDao, which serializes and deserializes the source list to and from JSON. It's abstract load() and save() methods are implemented by JsonSourceListFileDao do read from a local file.
In the future, this could be replaced with a different implementation of JsonSourceListDao, tha e.g. loads the data from an online location or API.

<img src="https://raw.githubusercontent.com/OAarne/otm-harjoitustyo/master/zhuLi/documentation/images/classes.png" width="800">