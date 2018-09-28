/*******************************************************************************
 Copyright 2018 bodand

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ******************************************************************************/

package tk.iscorp.nhs.core.test.datagetter

import org.apache.commons.io.FileUtils
import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.Gallery
import tk.iscorp.nhs.core.data.hentai._
import tk.iscorp.nhs.core.datagetter.HtmlResponseProcessor

import java.io.File

@RunWith(classOf[JUnitRunner])
class HtmlResponseProcessorTest extends WordSpec {
  private val dummyGallery = Gallery.dummy()
  private val testGallery = new Gallery("(C71) [Arisan-Antenna (Koari)] Eat The Rich! (Sukatto Golf Pangya)",
       /*it is easy to copy this*/      "(C71) [ありさんアンテナ (小蟻)] Eat The Rich! (スカッとゴルフ パンヤ)",
      /*where a non-dummy gallery*/     Array(new HentaiParody("pangya", 78)),
             /*is needed*/              Array(new HentaiCharacter("kooh", 41)),
                                        Array(new HentaiTag("lolicon", 45693),
                                              new HentaiTag("catgirl", 5796),
                                              new HentaiTag("gymshorts", 176)),
                                        Array(new HentaiArtist("koari", 46)),
                                        Array(new HentaiGroup("arisan-antenna", 34)),
                                        Array(new JapaneseHentai(129652)),
                                        new DoujinshiHentai(138514),
                                        14, "June 28, 2014, 2:12 p.m.", 1, 9)
  private val testGalleryWithISODate = new Gallery("(C71) [Arisan-Antenna (Koari)] Eat The Rich! (Sukatto Golf Pangya)",
       /*it is easy to copy this*/      "(C71) [ありさんアンテナ (小蟻)] Eat The Rich! (スカッとゴルフ パンヤ)",
      /*where a non-dummy gallery*/     Array(new HentaiParody("pangya", 78)),
             /*is needed*/              Array(new HentaiCharacter("kooh", 41)),
                                        Array(new HentaiTag("lolicon", 45693),
                                              new HentaiTag("catgirl", 5796),
                                              new HentaiTag("gymshorts", 176)),
                                        Array(new HentaiArtist("koari", 46)),
                                        Array(new HentaiGroup("arisan-antenna", 34)),
                                        Array(new JapaneseHentai(129652)),
                                        new DoujinshiHentai(138514),
                                        14, "2014-06-28T14:12:16.640420+00:00", 1, 9)
  private var htp: HtmlResponseProcessor = _
  "An HtmlResponseProcessor" when {
    "created" should {
      "initialize" in {
        htp = new HtmlResponseProcessor
      }
    }
    "asked to process data" should {
      "return dummy" when {
        "data is emtpy" in {
          assertEquals(dummyGallery, htp.processHtmlToGallery(""))
        }
        "html doesn't contain needed data" in {
          assertEquals(dummyGallery, htp.processHtmlToGallery("<!DOCTYPE html><!--[if IEMobile 7 ]> <html " +
                                                                 "lang=\"en_US\" class=\"no-js iem7\"> <![endif]--><!--[if lt IE 7]> <html lang=\"en_US\" class=\"no-js ie6 lt-ie10 lt-ie9 lt-ie8 lt-ie7\"><![endif]--><!--[if IE 7]>    <html lang=\"en_US\" class=\"no-js ie7 lt-ie10 lt-ie9 lt-ie8\"> <![endif]--><!--[if IE 8]>    <html lang=\"en_US\" class=\"no-js ie8 lt-ie10 lt-ie9  has-zcm\"><![endif]--><!--[if IE 9]>    <html lang=\"en_US\" class=\"no-js ie9 lt-ie10 has-zcm\"> <![endif]--><!--[if (gte IE 9)|(gt IEMobile 7)|!(IEMobile)|!(IE)]><!--><html class=\"no-js has-zcm  \"><!--<![endif]--><head><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge, chrome=1\"><meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"><title>prettify json at DuckDuckGo</title><link rel=\"stylesheet\" href=\"/s1680.css\" type=\"text/css\"><link rel=\"stylesheet\" href=\"/r1680.css\" type=\"text/css\"><link rel=\"manifest\" href=\"manifest.json\"><meta name=\"robots\" content=\"noindex,nofollow\"><meta name=\"referrer\" content=\"origin\"><link rel=\"shortcut icon\" href=\"/favicon.ico\" type=\"image/x-icon\" sizes=\"16x16 24x24 32x32 64x64\"/><link rel=\"apple-touch-icon\" href=\"/assets/icons/meta/DDG-iOS-icon_60x60.png\"/><link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"/assets/icons/meta/DDG-iOS-icon_76x76.png\"/><link rel=\"apple-touch-icon\" sizes=\"120x120\" href=\"/assets/icons/meta/DDG-iOS-icon_120x120.png\"/><link rel=\"apple-touch-icon\" sizes=\"152x152\" href=\"/assets/icons/meta/DDG-iOS-icon_152x152.png\"/><link rel=\"image_src\" href=\"/assets/icons/meta/DDG-icon_256x256.png\"/><script type=\"text/javascript\">var ct,fd,fq,it,iqa,iqm,iqs,iqp,iqq,qw,dl,ra,rv,rad,r1hc,r1c,r2c,r3c,rfq,rq,rds,rs,rt,rl,y,y1,ti,tig,iqd,locale,settings_js_version='s2469',is_twitter='',rpl=0;fq=0;fd=1;it=0;iqa=0;iqbi=0;iqm=0;iqs=0;iqp=0;iqq=0;qw=2;dl='en';ct='JP';iqd=0;r1hc=0;r1c=0;r3c=0;rq='prettify%20json';rqd=\"prettify json\";rfq=0;rt='E';ra='ffab';rv='';rad='';rds=30;rs=0;spice_version='1377';spice_paths='{}';locale='en_US';settings_url_params={};rl='us-en';rlo=0;df='';ds='';sfq='';iar='';vqd='228049522639321500361130374446819573324';</script><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" /><meta name=\"HandheldFriendly\" content=\"true\" /><meta name=\"apple-mobile-web-app-capable\" content=\"no\" /></head><body class=\"body--serp\"><input id=\"state_hidden\" name=\"state_hidden\" type=\"text\" size=\"1\"><span class=\"hide\">Ignore this box please.</span><div id=\"spacing_hidden_wrapper\"><div id=\"spacing_hidden\"></div></div><script type=\"text/javascript\" src=\"/locales/en_US/LC_MESSAGES/duckduckgo-duckduckgo+sprintf+gettext+locale-simple.20180916.233003.js\"></script><script type=\"text/javascript\" src=\"/lib/l110.js\"></script><script type=\"text/javascript\" src=\"/util/u249.js\"></script><script type=\"text/javascript\" src=\"/d2490.js\"></script><div class=\"site-wrapper  js-site-wrapper\"><div id=\"header_wrapper\" class=\"header-wrap js-header-wrap\"><div id=\"header\" class=\"header  cw\"><div class=\"header__search-wrap\"><a tabindex=\"-1\" href=\"/?t=ffab\" class=\"header__logo-wrap js-header-logo\"><span class=\"header__logo js-logo-ddg\">DuckDuckGo</span></a><div class=\"header__content  header__search\"><form id=\"search_form\" class=\"search--adv  search--header  js-search-form\" name=\"x\" action=\"/\"><input type=\"text\" name=\"q\" tabindex=\"1\" autocomplete=\"off\" id=\"search_form_input\" class=\"search__input--adv  js-search-input\" value=\"prettify json\"><input id=\"search_form_input_clear\" class=\"search__clear  js-search-clear\" type=\"button\" tabindex=\"3\" value=\"X\"/><input id=\"search_button\" class=\"search__button  js-search-button\" type=\"submit\" tabindex=\"2\" value=\"S\" /><a id=\"search_dropdown\" class=\"search__dropdown\" href=\"javascript:;\" tabindex=\"4\"></a><div id=\"search_elements_hidden\" class=\"search__hidden  js-search-hidden\"></div></form></div></div><div id=\"duckbar\" class=\"zcm-wrap  zcm-wrap--header  is-noscript-hidden\"></div></div><div class=\"header--aside js-header-aside\"></div></div><div id=\"zero_click_wrapper\" class=\"zci-wrap\"></div><div id=\"vertical_wrapper\" class=\"verticals\"></div><div id=\"web_content_wrapper\" class=\"content-wrap \"><div class=\"serp__top-right  js-serp-top-right\"></div><div class=\"serp__bottom-right  js-serp-bottom-right\"><div class=\"js-feedback-btn-wrap\"></div></div><div class=\"cw\"><div id=\"links_wrapper\" class=\"serp__results js-serp-results\"><div class=\"results--main\"><div class=\"search-filters-wrap\"><div class=\"js-search-filters search-filters\"></div></div><noscript><meta http-equiv=\"refresh\" content=\"0;URL=/html?q=prettify%20json\"><link href=\"/css/noscript.css\" rel=\"stylesheet\" type=\"text/css\"><div class=\"msg msg--noscript\"><p class=\"msg-title--noscript\">You are being redirected to the non-JavaScript site.</p>Click <a href=\"/html/?q=prettify%20json\">here</a> if it doesn't happen automatically.</div></noscript><div id=\"message\" class=\"results--message\"></div><div class=\"ia-modules js-ia-modules\"></div><div id=\"ads\" class=\"results--ads results--ads--main is-hidden js-results-ads\"></div><div id=\"links\" class=\"results is-hidden js-results\"></div></div><div class=\"results--sidebar js-results-sidebar\"><div class=\"sidebar-modules js-sidebar-modules\"></div><div class=\"is-hidden js-sidebar-ads\"></div></div></div></div></div><div id=\"bottom_spacing2\"> </div></div><script type=\"text/javascript\"></script><script type=\"text/JavaScript\">function nrci() {nrc('/share/goodie/jsonvalidator/1279/jsonvalidator.css');};DDG.ready(nrci, 1);</script><script type=\"text/JavaScript\">function nrji() {nrj('/t.js?q=prettify%20json&t=E&l=us-en&s=0&dl=en&ct=JP&ss_mkt=us&gjs=json_validator&p_ent=&ex=-2');nrj('/d.js?q=prettify%20json&t=E&l=us-en&s=0&a=ffab&dl=en&ct=JP&ss_mkt=us&vqd=228049522639321500361130374446819573324&atb=v132-2_j&gjs=json_validator&p_ent=&ex=-2');DDH.json_validator=DDH.json_validator||{};DDH.json_validator.meta={\"src_name\":null,\"dev_date\":null,\"attribution\":null,\"name\":\"JSON Validator\",\"src_id\":null,\"maintainer\":{\"github\":\"sahildua2305\"},\"producer\":\"Jag\",\"tab\":\"Answer\",\"example_query\":\"json validator\",\"developer\":[{\"type\":\"github\",\"name\":\"sahildua2305\",\"url\":\"https://github.com/sahildua2305\"},{\"url\":\"https://github.com/tgckpg\",\"name\":\"tgckpg\",\"type\":\"github\"},{\"url\":\"https://github.com/gautamkrishnar\",\"name\":\"gautamkrishnar\",\"type\":\"github\"}],\"production_state\":\"online\",\"perl_module\":\"DDG::Goodie::JSONValidator\",\"status\":null,\"is_stackexchange\":null,\"repo\":\"goodies\",\"designer\":null,\"blockgroup\":\"goodie\",\"js_callback_name\":\"jsonvalidator\",\"topic\":[\"tools\",\"JavaScript\",\"programming\"],\"src_url\":\" \",\"description\":\" \",\"dev_milestone\":\"live\",\"signal_from\":\"json_validator\",\"live_date\":\"2016-08-02\",\"id\":\"json_validator\",\"created_date\":\"2016-07-21\",\"unsafe\":null,\"src_domain\":null};;};DDG.ready(nrji, 1);</script><script src=\"/g1985.js\"></script><script type=\"text/javascript\">DDG.ready(function () {DDH.add({\"require\":\"/share/goodie/jsonvalidator/1279/jsonvalidator.goodie.js\",\"signal\":\"medium\",\"id\":\"json_validator\",\"name\":\"JSON Validator\",\"from\":\"json_validator\",\"data\":{\"subtitle\":\"Enter JSON below, then click the button to get beautified version of JSON and check for any syntax errors\",\"title\":\"JSON Beautifier & Validator\"},\"templates\":{\"options\":{\"content\":\"DDH.jsonvalidator.content\"},\"item\":0,\"group\":\"text\"}});});</script><script type=\"text/javascript\">DDG.page = new DDG.Pages.SERP({ showSafeSearch: 0, instantAnswerAds: false });</script><div id=\"z2\"> </div><div id=\"z\"></div></body></html>"))
        }
        "nhentai 404 is found" in {
          assertEquals(dummyGallery, htp.processHtmlToGallery("\n\n\n\n<!doctype html>\n<html lang=\"en\" class=\" " +
                                                                 "theme-black\">\n\t<head>\n\t\t<meta charset=\"utf-8\" />\n\n\t\t\n\t\t\t<meta name=\"theme-color\" content=\"#1f1f1f\" />\n\t\t\n\t\t\n\t\t\n\n\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=yes, viewport-fit=cover\" />\n\n\t\t\n\n\t\t<title>404 - Not Found &raquo; nhentai: hentai doujinshi and manga</title>\n\n\t\t<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\" />\n\t\t<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Noto+Sans:400,400i,700\" />\n\n\t\t\n\t\t\t<link rel=\"stylesheet\" href=\"https://static.nhentai.net/css/main_style.17c3be9fb4b6.css\" />\n\t\t\n\n\t\t<script src=\"https://static.nhentai.net/js/combined.8ca2cefd62d9.js\"></script>\n\n\n\t\t\n\t</head>\n\n\t<body>\n\t\t<nav role=\"navigation\"><a class=\"logo\" href=\"/\"><img src=\"https://static.nhentai.net/img/logo.650c98bbb08e.svg\" alt=\"logo\" width=\"46\" height=\"30\"></a><form role=\"search\" action=\"/search/\" class=\"search\"><input type=\"search\" name=\"q\" value=\"\" autocapitalize=\"none\" required /><button type=\"submit\" class=\"btn btn-primary btn-square\"><i class=\"fa fa-search fa-lg\"></i></button></form><button type=\"button\" class=\"btn btn-secondary btn-square\" id=\"hamburger\"><span class=\"line\"></span><span class=\"line\"></span><span class=\"line\"></span></button><div class=\"collapse\"><ul class=\"menu left\"><li class=\"desktop \"><a href=\"/random/\">Random</a></li><li class=\"desktop \"><a href=\"/tags/\">Tags</a></li><li class=\"desktop \"><a href=\"/artists/\">Artists</a></li><li class=\"desktop \"><a href=\"/characters/\">Characters</a></li><li class=\"desktop \"><a href=\"/parodies/\">Parodies</a></li><li class=\"desktop \"><a href=\"/groups/\">Groups</a></li><li class=\"desktop \"><a href=\"/info/\">Info</a></li><li class=\"dropdown\"><button class=\"btn btn-secondary btn-square\" type=\"button\" id=\"dropdown\"><i class=\"fa fa-chevron-down\"></i></button><ul class=\"dropdown-menu\"><li><a href=\"/random/\">Random</a></li><li><a href=\"/tags/\">Tags</a></li><li><a href=\"/artists/\">Artists</a></li><li><a href=\"/characters/\">Characters</a></li><li><a href=\"/parodies/\">Parodies</a></li><li><a href=\"/groups/\">Groups</a></li><li><a href=\"/info/\">Info</a></li></ul></li></ul><ul class=\"menu right\"><li><a href=\"/login/\"><i class=\"fa fa-sign-in\"></i> Sign in</a></li><li><a href=\"/register/\"><i class=\"fa fa-pencil-square\"></i> Register</a></li></ul></div></nav>\n\n\t\t\n\n\t\t<div id=\"messages\">\n\t\t\t\n\t\t</div>\n\n\t\t<div id=\"content\">\n\t\t\t\n\t<div class=\"container error\">\n\t\t<h1>404 &ndash; Not Found</h1>\n\n\t\t\n\t\t\t<p>Looks like what you're looking for isn't here.</p>\n\t\t\n\n\t\t\n\t</div>\n\n\t\t</div>\n\n\t\t\n\n\t\t<script>\n\t\t\t\n\t\t\t\tN.init({\n\t\t\t\t\t\n\t\t\t\t\tlogged_in: false,\n\t\t\t\t\tads: {\n\t\t\t\t\t\tshow_popunders: true\n\t\t\t\t\t}\n\t\t\t\t});\n\t\t\t\n\t\t</script>\n\n\t\t\n\t\t\n\t</body>\n</html>"))
        }
      }
      "return data in Gallery" when {
        "html is good" when {
          "iso date not requested" in {
            val nhentaiHtmlFile = new File(getClass.getClassLoader.getResource("nhentaiid1.html").toURI)
            assertEquals(testGallery, htp.processHtmlToGallery(FileUtils.readFileToString(nhentaiHtmlFile, "utf-8")))
          }
          "iso date requested" in {
            val nhentaiHtmlFile = new File(getClass.getClassLoader.getResource("nhentaiid1.html").toURI)
            assertEquals(testGallery, htp.processHtmlToGallery(FileUtils.readFileToString(nhentaiHtmlFile, "utf-8")))
          }
        }
      }
    }
  }
}
