This class acts as the core of nhstatistics-core, IO operations are available on it, and as a bonus to the library, 
there is a GalleryDownloader class to download these Galleries and store them in a directory/folder somewhere. 
It represents one doujin on the the nhentai.net site. All data one can gather by opening the doujin page on nhentai is 
in this class so it is useful for safe for work data gathering application.  
The most likely to be useful fields are:

- `name` - The name of the doujin. It's stored in a String.
- `tags` - The tags present on this doujin. It's an array of HentaiTag objects.
- `id` - One of the infamous id-s that represent one doujin. It's an Int in type.
- `languages` - Language on nhentai, including the `Translated` and `Rewrite` tags, just as on the webpage.     

The class implements the `JSONTransformable` and `XmlTransformable` traits thus it can return a legal JSON or XML by
calling toJson or toXml respectively. It is returned in a string to reduce library overhead, you need to process it with
your preferred JSON/XML parser library.
