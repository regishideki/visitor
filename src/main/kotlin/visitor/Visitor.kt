package visitor

interface Partner {
    fun resourceFor(registryPartner: PartnerResourceRegistry): PartnerResource
}
class Bmg: Partner {
    override fun resourceFor(registryPartner: PartnerResourceRegistry) =
        registryPartner.resourceForBmg(this)
}
class Pan: Partner {
    override fun resourceFor(registryPartner: PartnerResourceRegistry) =
        registryPartner.resourceForPan(this)
}

interface PartnerResource {
    fun execute()
}

class BmgFormValidation(
    private val partner: Partner
): PartnerResource {
    override fun execute() {
        println("executing BMG Form Validation")
    }
}
class PanFormValidation(
    private val partner: Partner
): PartnerResource {
    override fun execute() {
        println("executing Pan Form Validation")
    }
}

interface PartnerResourceRegistry {
    fun resourceFor(partner: Partner) = partner.resourceFor(this)
    fun resourceForBmg(partner: Partner): PartnerResource
    fun resourceForPan(partner: Partner): PartnerResource
}
class FormValidationPartnerResourceRegistry: PartnerResourceRegistry {
    override fun resourceForBmg(partner: Partner) = BmgFormValidation(partner)
    override fun resourceForPan(partner: Partner) = PanFormValidation(partner)
}

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
