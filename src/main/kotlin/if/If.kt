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

class CreateLoanApplicationUseCase {
    fun create(loanApplication: LoanApplication) {
        validate(loanApplication)
    }

    private fun validate(loanApplication: LoanApplication) {
        when(loanApplication.partner) {
            is ScdPartner -> ScdFormValidation().validate(loanApplication)
            is PanPartner -> PanFormValidation().validate(loanApplication)
        }
    }
}

class SendLoanApplicationDocumentUseCase {
    fun send(loanApplication: LoanApplication) {
        validate(loanApplication)
    }

    private fun validate(loanApplication: LoanApplication) {
        when(loanApplication.partner) {
            is ScdPartner -> ScdDocumentSender().send(loanApplication.document)
            is PanPartner -> PanDocumentSender().send(loanApplication.document)
        }
    }
}
