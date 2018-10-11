package tk.iscorp.nhs.core.transform

private[core] trait XmlTransformable {
  def toXml: String
}
