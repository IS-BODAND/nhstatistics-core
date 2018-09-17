The basic Gallery class represents one doujin on the nhentai site.
It has the following fields, without getters and setters, because this is in Scala, and all are immutable, and it 
makes no sense to make only getters:
 - name: String - The name of the doujin
 - japName: String - The japanese or secondary name of the doujin, "" if it doesn't have one.
 - parodies: Array\[HentaiParody\] - All the anime this doujin makes a parody of. HentaiParody implements HentaiData.
 - characters: Array\[HentaiCharacter\] - All the characters appear in the doujin. HentaiCharacter implements HentaiData.
 - tags: Array\[HentaiTag\] - All the fantasies this doujin fulfills. HentaiTag implements HentaiData.
 - artists: Array\[HentaiArtist\] - All the artist who made this doujin. HentaiArtist implements HentaiData.
 - groups: Array\[HentaiGroup\] - All the groups that contain this doujin. HentaiGroup implements HentaiData.
 - languages: Array\[HentaiLanguage\] - The languages this doujin is written in. HentaiLanguage implements HentaiData.
 - category: HentaiCategory - Whether this doujin is a doujinshi or a manga. HentaiCategory implements HentaiData.
 - pageCount: Int - How long this doujin is.
 - uploadDate: String - To be changed, currently stores either the iso compliant or normal date directly taken from the site.
 - id: Int - ID of the doujin.

And with the toXml, and toJson methods it can turn itself into some nice xml/json.
The download() method dowloads it to jarDir/doujin/\<id\>/
