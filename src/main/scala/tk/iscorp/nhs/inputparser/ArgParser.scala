package tk.iscorp.nhs.inputparser

import org.apache.commons.cli.{CommandLineParser, DefaultParser, HelpFormatter, Option, Options}
import org.jetbrains.annotations.NotNull
import tk.iscorp.nhs.Utils

import java.text.ParseException

class ArgParser {
  private val parser       : CommandLineParser = new DefaultParser
  private val options      : Options           = {
    val opt: Options = new Options

    val help = new Option("h", "help", false, "Display help")
    val id = new Option("i", "hentai-id", true, "ID of the hentai to view")

    opt.addOption(help)
    opt.addOption(id)

    opt
  }
  private val helpFormatter: HelpFormatter     = new HelpFormatter()

  @NotNull
  def parse(args: Array[String]): ParseData = {
    try {
      val cl = parser.parse(options, args)

      val help = cl.hasOption("h")

      val id = if (cl.hasOption("i")) cl.getOptionValue("i") else ""

      new ParseData(help, id)
    } catch {
      case e: ParseException ⇒
        Utils.logger.error(s"Argument Parsing error: ${e.getMessage}")
        ParseData.dummy()
    }
  }

  def printHelp(): Unit = {
    helpFormatter.printHelp("nhs", "WIP NHentai desktop experience", options, "\n")
  }
}
