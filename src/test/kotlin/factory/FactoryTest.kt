package factory

import org.junit.jupiter.api.Test

class FactoryTest {
    @Test
    fun `it runs BMG form Validation for BMG Partner`() {
        val partner = Bmg()
        val useCase = buildUseCase()

        useCase.update(partner)
    }

    @Test
    fun `it runs Pan form Validation for Pan Partner`() {
        val partner = Pan()
        val useCase = buildUseCase()

        useCase.update(partner)
    }

    private fun buildUseCase() = UpdatePartnerUseCase(PartnerFormValidationFactory())
}
