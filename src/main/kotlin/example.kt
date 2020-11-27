// ======== Partner =========

interface Partner

class Bmg: Partner

class Pan: Partner

// ======== UseCase =========

class UpdatePartnerUseCase {
    fun update(partner: Partner) {
        validate(partner)
        // do something
    }

    private fun validate(partner: Partner) {
        // TODO
    }
}
