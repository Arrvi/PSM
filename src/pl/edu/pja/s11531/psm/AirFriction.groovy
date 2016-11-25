package pl.edu.pja.s11531.psm

/**
 * Created by s11531 on 2016-11-25.
 */
class AirFriction implements ExternalForce {
    BigDecimal ratio

    AirFriction(BigDecimal ratio) {
        this.ratio = ratio
    }

    @Override
    Vector calculateForce(Vector position, Vector velocity) {
        return velocity * (-ratio)
    }
}
