The basic Gallery class represents one doujin on the nhentai site.
The following fields are most likely useful:
 - name: String - The name of the doujin
 - tags: Array\[HentaiTag\] - All the fantasies this doujin fulfills. HentaiTag implements HentaiData.
 - languages: Array\[HentaiLanguage\] - The languages this doujin is written in. HentaiLanguage implements HentaiData.
 - id: Int - ID of the doujin.

With the toXml, and toJson methods it can turn itself into some nice xml/json.
