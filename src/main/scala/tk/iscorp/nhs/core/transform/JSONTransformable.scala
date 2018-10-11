package tk.iscorp.nhs.core.transform

private[core] trait JSONTransformable {
  def toJson: String
}
