package factory

// ======== Partner =========

interface Partner

class Bmg: Partner

class Pan: Partner

// ======== Validation =========

interface FormValidation {
    fun validate()
}

class BmgFormValidation(
    private val partner: Partner
): FormValidation {
    override fun validate() {
        println("executing BMG Form Validation in ${partner::class}")
    }
}

class PanFormValidation(
    private val partner: Partner
): FormValidation {
    override fun validate() {
        println("executing Pan Form Validation in ${partner::class}")
    }
}

// ======== Factory =========

class PartnerFormValidationFactory {
    fun validationFor(partner: Partner): FormValidation {
        return when(partner) {
            is Bmg -> BmgFormValidation(partner)
            is Pan -> PanFormValidation(partner)
            else -> throw RuntimeException()
        }
    }
}

// ======== UseCase =========

class UpdatePartnerUseCase(
    private val partnerFormValidationFactory: PartnerFormValidationFactory
) {
    fun update(partner: Partner) {
        validate(partner)
        // do something
    }

    private fun validate(partner: Partner) {
        partnerFormValidationFactory.validationFor(partner).validate()
    }
}
