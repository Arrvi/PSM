package pl.edu.pja.s11531.psm.planets

import pl.edu.pja.s11531.psm.ExternalForce
import pl.edu.pja.s11531.psm.MaterialPointImpl
import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.projectile.Projectile

/**
 * Created by kris on 12/1/16.
 */
class Gravity implements ExternalForce {
    static final BigDecimal GRAVITATIONAL_CONSTANT = BigDecimal.valueOf(6674, -14) // 6674*10^-14
    MaterialPointImpl center

    Gravity(MaterialPointImpl center) {
        this.center = center
    }

    @Override
    Vector calculateForce(Projectile projectile) {
        def direction = center.position - projectile.position
        return direction * (GRAVITATIONAL_CONSTANT * center.mass * projectile.mass / direction.length**2)
    }
}
