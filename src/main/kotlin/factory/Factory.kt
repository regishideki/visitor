package factory

import `if`.Document
import `if`.DocumentSender
import `if`.FormValidation
import `if`.LoanApplication
import `if`.PanDocumentSender
import `if`.PanFormValidation
import `if`.PanApplication
import `if`.Application
import `if`.ScdDocumentSender
import `if`.ScdFormValidation
import `if`.ScdApplication

interface Partner
class ScdPartner: Application
class PanPartner: Application

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
class LoanApplication(val application: Application, val document: Document)

class PartnerResouceFactory {

}

class CreateLoanApplicationUseCase {
    fun create(loanApplication: LoanApplication) {
        validate(loanApplication)
    }

    private fun validate(loanApplication: LoanApplication) {
        when(loanApplication.application) {
            is ScdApplication -> ScdFormValidation().validate(loanApplication)
            is PanApplication -> PanFormValidation().validate(loanApplication)
        }
    }
}

class SendLoanApplicationDocumentUseCase {
    fun send(loanApplication: LoanApplication) {
        validate(loanApplication)
    }

    private fun validate(loanApplication: LoanApplication) {
        when(loanApplication.application) {
            is ScdApplication -> ScdDocumentSender().send(loanApplication.document)
            is PanApplication -> PanDocumentSender().send(loanApplication.document)
        }
    }
}
