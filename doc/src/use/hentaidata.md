I referred to HentaiData trait in the Gallery definition, and it is a really simple thing.
I contains the name of the data, and the amount of doujin with it on nhentai.
i.e. `[femdom (13830)]`.

It implements the `JSONTransformable` and `XmlTransformable` traits, thus behaves exactly like the Gallery class. An
example return value is in the ScalaDoc on the toJson and toXml methods.

It also overrides toString to give that form I've shown some lines above.
