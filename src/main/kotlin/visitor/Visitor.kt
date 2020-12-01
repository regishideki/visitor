package visitor

// ======== Partner =========

interface Partner {
    fun resourceFor(partnerResourceFactory: PartnerResourceFactory): PartnerResource
}

class Bmg: Partner {
    override fun resourceFor(partnerResourceFactory: PartnerResourceFactory) =
        partnerResourceFactory.resourceForBmg(this)
}

class Pan: Partner {
    override fun resourceFor(partnerResourceFactory: PartnerResourceFactory) =
        partnerResourceFactory.resourceForPan(this)
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

// ======== Partner Resource Factory Interface =========

interface PartnerResourceFactory {
    fun resourceFor(partner: Partner) = partner.resourceFor(this)
    fun resourceForBmg(partner: Partner): PartnerResource
    fun resourceForPan(partner: Partner): PartnerResource
}

// ======== Partner FormValidation Factory  =========

class FormValidationPartnerResouceFactory: PartnerResourceFactory {
    override fun resourceForBmg(partner: Partner) = BmgFormValidation(partner)
    override fun resourceForPan(partner: Partner) = PanFormValidation(partner)
}

// ======== UseCase =========

class UpdatePartnerUseCase(
    private val formValidationPartnerResourceFactory: FormValidationPartnerResouceFactory
) {
    fun update(partner: Partner) {
        validate(partner)
        // do something
    }

    private fun validate(partner: Partner) {
        formValidationPartnerResourceFactory
          .resourceFor(partner)
          .execute()
    }

