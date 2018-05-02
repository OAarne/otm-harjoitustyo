# Software Architechture of zhuLi

zhuLi uses a three-tier architecture, with a UI layer, A Service/Model layer, and a DAO layer.

The UI layer is driven by the SourceController, which is a questionable decision, and I might just eliminate it/merge with SourceListModel.
A Model-View-ViewModel pattern is used to mediate between the view and the model/controller/service layer.

The core of the service layer is the SourceListModel, which mediates between the dao and the UI.

The dao layer consists of a SourceListDao interface.
It is currently only implemented by JsonSourceListDao, which serializes and deserializes the source list to and from JSON, and writes and reads the JSON from a file.
A future implementation of the Dao might involve something, which communicates with an online storage location rather than a local file.
Such an implementation would probably use most of the same JSON parsing as the current implementation, so I should make JsonSourceListDao into an interface which is implemented by JsonSourceListFileDao and a possible JsonSourceListNetDao?