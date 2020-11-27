package visitor

// ======== Partner =========

interface Partner {
    fun resourceFor(partnerResouceRegistry: PartnerResourceRegistry): PartnerResource
}

class Bmg: Partner {
    override fun resourceFor(partnerResouceRegistry: PartnerResourceRegistry) =
        partnerResouceRegistry.resourceForBmg(this)
}

class Pan: Partner {
    override fun resourceFor(partnerResouceRegistry: PartnerResourceRegistry) =
        partnerResouceRegistry.resourceForPan(this)
}

// ======== Resource =========

interface PartnerResource {
    fun execute()
}

class BmgFormValidation(
    private val partner: Partner
): PartnerResource {
    override fun execute() {
        println("executing BMG Form Validation in ${partner::class}")
    }
}

class PanFormValidation(
    private val partner: Partner
): PartnerResource {
    override fun execute() {
        println("executing Pan Form Validation in ${partner::class}")
    }
}

// ======== GenericRegistry =========

interface PartnerResourceRegistry {
    fun resourceFor(partner: Partner) = partner.resourceFor(this)
    fun resourceForBmg(partner: Partner): PartnerResource
    fun resourceForPan(partner: Partner): PartnerResource
}

// ======== SpecificRegistry =========

class FormValidationPartnerResourceRegistry: PartnerResourceRegistry {
    override fun resourceForBmg(partner: Partner) = BmgFormValidation(partner)
    override fun resourceForPan(partner: Partner) = PanFormValidation(partner)
}

// ======== UseCase =========

class UpdatePartnerUseCase(
    private val formValidationPartnerResourceRegistry: FormValidationPartnerResourceRegistry
) {
    fun update(partner: Partner) {
        validate(partner)
        // do something
    }

    private fun validate(partner: Partner) {
        formValidationPartnerResourceRegistry.resourceFor(partner).execute()
    }
}
