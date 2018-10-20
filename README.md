# NHentaiStatistics Core

[![Waffle.io - Columns and their card count](https://badge.waffle.io/isbodand/nhstatistics-core.svg?columns=all&style=flat-square)](https://waffle.io/isbodand/nhstatistics-core)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat-square)](http://www.apache.org/licenses/LICENSE-2.0)
[![Release](https://jitpack.io/v/isbodand/nhstatistics-core.svg?style=flat-square)](https://jitpack.io/#isbodand/nhstatistics-core)
[![Build Status](https://travis-ci.org/isbodand/nhstatistics-core.svg?branch=devel-1.3&style=flat-square)](https://travis-ci.org/isbodand/nhstatistics-core)
[![codecov.io](https://codecov.io/gh/isbodand/nhstatistics-core/branch/master/graphs/badge.svg?style=flat-square)](https://codecov.io/gh/isbodand/nhstatistics-core)
[![CodeFactor](https://www.codefactor.io/repository/github/isbodand/nhstatistics-core/badge?style=flat-square)](https://www.codefactor.io/repository/github/isbodand/nhstatistics-core)
[![Dependabot Status](https://api.dependabot.com/badges/status?host=github&repo=isbodand/nhstatistics-core)](https://dependabot.com)
[![Known Vulnerabilities](https://snyk.io/test/github/isbodand/nhstatistics-core/badge.svg?targetFile=build.gradle)](https://snyk.io/test/github/isbodand/nhstatistics-core?targetFile=build.gradle)
[![Join the chat at https://gitter.im/nhstatistics-core/Lobby](https://badges.gitter.im/nhstatistics-core/Lobby.svg)](https://gitter.im/nhstatistics-core/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

The unofficial nhentai JVM api. Since nhentai's api does not exist* (I'm going to guess it stopped existing
when they moved to `https://` instead of `http://` as a post on their json api only used `http://`), I made this api
which parses the webpage to get the data.
It is named NHentaiStatistics *Core* as it **will** be the part of the NHentaiStatistics program
which **will** be like a desktop nhentai with additional statistics compatibilities.

This library currently supports getting the data from the doujin's ID, including:

- Title
- Secondary/Japanese title
- Page count
- Authors
- Tags, Groups, Parodies, etc. you see on the doujin's page
- Upload time in Java 8's OffsetDateTime

Doujin data can easily be got with the included HentaiInStream implementation and outputted to the user with the two
HentaiOutStream implementations.
Additionally it allows the processing of offline doujin pages with the HtmlResponseProcessor class.

More documentation/small tutorial available at [the projects' website](https://isbodand.github.io/nhstatistics-core).

\* I sent an email about whether their API is non-functional or I'm being stupid, but they are yet to reply
which I doubt will happen
