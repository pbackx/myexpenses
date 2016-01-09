import java.time.LocalDate
import java.time.temporal.ChronoUnit

object Main extends App {
  val expenses = new CVSInput().read("/statement.csv").toList

  // TODO read already classified expenses
  // TODO remove already classified exp from new ones

  // rules to classify expenses
  val rules = List(
    new Rule
  )

  // classify expenses
  val categorized = expenses.groupBy(
    expense => {
      val matched = rules.find(rule => rule.classify(expense).isDefined)
      matched match {
        case Some(rule) => rule.classify(expense).get
        case None => "unknown"
      }
    }
  )

  // TODO write out classified expenses

  // print summary
  val startDate = (LocalDate.now() /: expenses) { (earliest, expense) =>
    if (expense.date isBefore earliest)
      expense.date
    else
      earliest
  }
  val endDate = (startDate /: expenses) { (latest, expense) =>
    if (expense.date isAfter latest)
      expense.date
    else
      latest
  }
  val days = ChronoUnit.DAYS.between(startDate, endDate)
  val months = days / 30

  categorized.foreach(
    category => {
      print(category._1 + " | ")

      val sum = category._2.map(expense => expense.amount).foldLeft(BigDecimal.valueOf(0.0)) { _ + _ }
      println(sum / months + "/month")
    }
  )

  println(categorized("unknown").mkString("\n"))
}