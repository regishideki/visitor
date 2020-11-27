package abstractfactory

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

class PartnerAbstractFactory {
    fun factoryFor(partner: Partner) = when(partner) {
        is Bmg -> BmgFactory(partner)
        is Pan -> PanFactory(partner)
        else -> throw RuntimeException()
    }
}

interface PartnerFactory {
    fun formValidation(): FormValidation
}

class BmgFactory(
    private val partner: Partner
): PartnerFactory {
    override fun formValidation() = BmgFormValidation(partner)
}

class PanFactory(
    private val partner: Partner
): PartnerFactory {
    override fun formValidation() = PanFormValidation(partner)
}

// ======== UseCase =========

class UpdatePartnerUseCase(
    private val partnerAbstractFactory: PartnerAbstractFactory
) {
    fun update(partner: Partner) {
        validate(partner)
        // do something
    }

    private fun validate(partner: Partner) {
        partnerAbstractFactory
            .factoryFor(partner)
            .formValidation()
            .validate()
    }
}
