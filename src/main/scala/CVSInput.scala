import java.time.LocalDate
import java.time.format.DateTimeFormatter

import tabulate._
import tabulate.ops._

class CVSInput {
  def read(fileName: String): Iterator[Expense] = {
    val rawData = getClass.getResource(fileName)

    implicit val codec = scala.io.Codec.ISO8859

    // adapt to Belgian format (comma instead of point for decimal separator)
    implicit val amountDecoder = CellDecoder(s => DecodeResult(BigDecimal(s.replaceAll(",", "."))))
    implicit val dateDecoder = CellDecoder(s => DecodeResult(LocalDate.parse(s, DateTimeFormatter.ofPattern("dd/MM/yyyy"))))

    implicit val expenseDecoder = RowDecoder.decoder8(Expense.apply)(0, 1, 2, 3, 4, 5, 6, 7)

    rawData.asUnsafeCsvRows[Expense](';', true)
  }
}
