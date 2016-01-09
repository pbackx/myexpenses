import java.time.LocalDate

case class Expense(val ref: String, val date: LocalDate, val valDate: LocalDate,
                   val amount: BigDecimal, val currency: String, val counterParty: String,
                   val comment: String, val accountNumber: String) {
  override def toString: String = {
    val sign = if(amount < 0) "to" else "from"
    s"${amount.abs} $sign $counterParty regarding $comment"
  }
}
