package visitor

interface Partner {
    fun resouceFor(registry: PartnerResouceRegistry): PartnerResouce
}
class ScdPartner: Partner {
    override fun resouceFor(registry: PartnerResouceRegistry) = registry.resourceForScd()
}
class PanPartner: Partner {
    override fun resouceFor(registry: PartnerResouceRegistry) = registry.resourceForPan()
}

interface PartnerResouce {
    fun execute(loanApplication: LoanApplication)
}

class ScdDocumentSender: PartnerResouce {
    override fun execute(loanApplication: LoanApplication) {}
}
class PanDocumentSender: PartnerResouce {
    override fun execute(loanApplication: LoanApplication) {}
}

class ScdFormValidation: PartnerResouce {
    override fun execute(loanApplication: LoanApplication) {}
}
class PanFormValidation: PartnerResouce {
    override fun execute(loanApplication: LoanApplication) {}
}

interface PartnerResouceRegistry {
    fun resourceForScd(): PartnerResouce
    fun resourceForPan(): PartnerResouce
}
class PartnerFormValidationResouceRegistry: PartnerResouceRegistry {
    override fun resourceForScd() = ScdFormValidation()
    override fun resourceForPan() = PanFormValidation()
}
class PartnerDocumentResouceRegistry: PartnerResouceRegistry {
    override fun resourceForScd() = ScdDocumentSender()
    override fun resourceForPan() = PanDocumentSender()
}

class Document
class LoanApplication(val partner: Partner, val document: Document) {
    fun resourceFor(partnerResourceResouceRegistry: PartnerResouceRegistry) =
        partner.resouceFor(partnerResourceResouceRegistry)
}

class CreateLoanApplicationUseCase(
    private val partnerFormValidationResouceRegistry: PartnerFormValidationResouceRegistry
) {
    fun create(loanApplication: LoanApplication) {
        validate(loanApplication)
    }

    private fun validate(loanApplication: LoanApplication) {
        loanApplication.resourceFor(partnerFormValidationResouceRegistry).execute(loanApplication)
    }
}
