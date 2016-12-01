package pl.edu.pja.s11531.psm

import pl.edu.pja.s11531.psm.projectile.Projectile

/**
 * Created by s11531 on 2016-11-25.
 */
class AirFriction implements ExternalForce {
    BigDecimal ratio

    AirFriction(BigDecimal ratio) {
        this.ratio = ratio
    }

    @Override
    Vector calculateForce(Projectile projectile) {
        return projectile.velocity * (-ratio)
    }
}
