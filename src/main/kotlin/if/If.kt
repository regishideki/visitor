package `if`

interface Partner
class ScdPartner: Partner
class PanPartner: Partner

interface DocumentSender {
    fun send(document: Document) {}
}
class ScdDocumentSender: DocumentSender {
    override fun send(document: Document) {}
}
class PanDocumentSender: DocumentSender {
    override fun send(document: Document) {}
}

interface FormValidation {
    fun validate(loanApplication: LoanApplication)
}
class ScdFormValidation: FormValidation {
    override fun validate(loanApplication: LoanApplication) {}
}
class PanFormValidation: FormValidation {
    override fun validate(loanApplication: LoanApplication) {}
}

class Document
class LoanApplication(val partner: Partner, val document: Document)

class CreateLoanApplicationUseCase(
    private val scdFormValidation: ScdFormValidation,
    private val panFormValidation: PanFormValidation
) {
    fun create(loanApplication: LoanApplication) {
        validate(loanApplication)
    }

    private fun validate(loanApplication: LoanApplication) {
        when(loanApplication.partner) {
            is ScdPartner -> scdFormValidation.validate(loanApplication)
            is PanPartner -> panFormValidation.validate(loanApplication)
        }
    }
}

class SendLoanApplicationDocumentUseCase(
    private val scdDocumentSender: ScdDocumentSender,
    private val panDocumentSender: PanDocumentSender
) {
    fun send(loanApplication: LoanApplication) {
        validate(loanApplication)
    }

    private fun validate(loanApplication: LoanApplication) {
        when(loanApplication.partner) {
            is ScdPartner -> scdDocumentSender.send(loanApplication.document)
            is PanPartner -> panDocumentSender.send(loanApplication.document)
        }
    }
}
