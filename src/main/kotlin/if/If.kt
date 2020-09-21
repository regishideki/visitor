package `if`

interface Partner
class Bmg: Partner
class Pan: Partner

interface FormValidation {
    fun validate(partner: Partner)
}
class ScdFormValidation: FormValidation {
    override fun validate(partner: Partner) {
        // do something
    }
}
class PanFormValidation: FormValidation {
    override fun validate(partner: Partner) {
        // do something
    }
}

class CreatePartnerUseCase(
    private val scdFormValidation: ScdFormValidation,
    private val panFormValidation: PanFormValidation
) {
    fun update(partner: Partner) {
        validate(partner)
        // do something
    }

    private fun validate(partner: Partner) {
        when(partner) {
            is Bmg -> scdFormValidation.validate(partner)
            is Pan -> panFormValidation.validate(partner)
        }
    }
}

