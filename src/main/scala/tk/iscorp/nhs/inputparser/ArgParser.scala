package tk.iscorp.nhs.inputparser

import org.apache.commons.cli.{CommandLineParser, DefaultParser, HelpFormatter, Option, Options}
import org.jetbrains.annotations.NotNull
import tk.iscorp.nhs.Utils

import java.io.File
import java.text.ParseException

class ArgParser {
  private val parser       : CommandLineParser = new DefaultParser
  private val options      : Options           = {
    val opt: Options = new Options

    val help = new Option("h", "help", false, "Display help")
    val id = new Option("i", "hentai-id", true, "ID of the hentai to view")
    val isoDate = new Option("y", "iso-date", false,
                             "Display upload time in ISO 8601 format, like it should be")
    val until = new Option("u", "until", true, "Gets all hentai until the ID specified. Inclusive.")
    val file = new Option("F", "file-output", true, "Outputs to specified file")

    opt.addOption(help)
    opt.addOption(id)
    opt.addOption(isoDate)
    opt.addOption(until)
    opt.addOption(file)

    opt
  }
  private val helpFormatter: HelpFormatter     = new HelpFormatter()

  @NotNull
  def parse(args: Array[String]): ParseData = {
    try {
      val cl = parser.parse(options, args)

      val help = cl.hasOption("h")

      val id = if (cl.hasOption("i")) cl.getOptionValue("i") else ""

      val isoDate = cl.hasOption("y")

      val until = {
        if (cl.hasOption("u")) {
          cl.getOptionValue("u").toInt
        } else {
          0
        }
      }

      val file = if (cl.hasOption("F")) new File(cl.getOptionValue("F")) else null

      new ParseData(help, id, isoDate, Range.inclusive(1, until), file)
    } catch {
      case e: ParseException ⇒
        Utils.logger.error(s"Argument Parsing error: ${e.getMessage}")
        ParseData.dummy()
    }
  }

  def printHelp(): Unit = {
    helpFormatter.printHelp("nhs", "WIP NHentai desktop experience\n\n", options, "\n")
  }
}
