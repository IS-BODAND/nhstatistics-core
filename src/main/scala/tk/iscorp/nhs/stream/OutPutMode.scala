package tk.iscorp.nhs.stream

sealed abstract class OutPutMode

case class FileOutPutMode() extends OutPutMode

case class STDOUTMode() extends OutPutMode
