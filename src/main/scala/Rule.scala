import java.util.Properties

class Rule {
  val properties = new Properties
  properties.load(getClass.getResourceAsStream("/counterparties.properties"))

  import scala.collection.JavaConverters._
  val lookup = properties.asScala

  def classify(expense: Expense): Option[String] = {
    lookup.get(expense.counterParty.replaceAll(" ", ""))
  }
}
