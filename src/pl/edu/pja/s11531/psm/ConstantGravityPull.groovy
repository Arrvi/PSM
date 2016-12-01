package pl.edu.pja.s11531.psm

import pl.edu.pja.s11531.psm.projectile.Projectile

/**
 * Created by s11531 on 2016-11-15.
 */
class ConstantGravityPull implements ExternalForce {
    BigDecimal gravity

    ConstantGravityPull(BigDecimal gravity=-10.0) {
        this.gravity = gravity
    }

    @Override
    Vector calculateForce(Projectile projectile) {
        return new Vector(0.0, gravity)
    }
}
