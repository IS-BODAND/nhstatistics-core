# NHentaiStatistics Core

[![Waffle.io - Columns and their card count](https://badge.waffle.io/isbodand/nhstatistics-core.svg?columns=all&style=flat-square)](https://waffle.io/isbodand/nhstatistics-core)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat-square)](http://www.apache.org/licenses/LICENSE-2.0)
[![Release](https://jitpack.io/v/isbodand/nhstatistics-core.svg?style=flat-square)](https://jitpack.io/#isbodand/nhstatistics-core)
[![Build Status](https://travis-ci.org/isbodand/nhstatistics-core.svg?branch=devel-1.3&style=flat-square)](https://travis-ci.org/isbodand/nhstatistics-core)
[![codecov.io](https://codecov.io/gh/isbodand/nhstatistics-core/branch/master/graphs/badge.svg?style=flat-square)](https://codecov.io/gh/isbodand/nhstatistics-core)
[![CodeFactor](https://www.codefactor.io/repository/github/is-bodand/nhstatistics-core/badge?style=flat-square)](https://www.codefactor.io/repository/github/is-bodand/nhstatistics-core)

The unofficial nhentai JVM api. Since nhentai's api is unavailable, (or I'm too stupid to access it,) I made this api 
which parses the webpage to get the data. It **will** be the part of the NHentaiStatistics program which **will** be 
like a desktop nhentai with additional statistics compatibilities. 

This library currently supports getting the data from the doujin's ID, including:
 - Title
 - Secondary/Japanese title
 - Page count
 - Authors
 - Tags, Groups, Parodies, etc. you see on the doujin's page
 - Upload time currently in string, will change

Doujin data can easily be got with the included HentaiInStream implementation and outputted to the user with the two 
HentaiOutStream implementations.
Additionally it allows the processing of offline doujin pages with the HtmlResponseProcessor class.

More documentation/small tutorial available at [https://isbodand.github.io/nhstatistics-core](the project's website).
