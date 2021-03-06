package pl.edu.pja.s11531.psm.planets

import org.codehaus.groovy.runtime.typehandling.BigDecimalMath
import pl.edu.pja.s11531.psm.ExternalForce
import pl.edu.pja.s11531.psm.MaterialPoint
import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.projectile.Projectile

/**
 * Created by kris on 12/1/16.
 */
class Gravity implements ExternalForce {
    static final BigDecimal GRAVITATIONAL_CONSTANT = BigDecimal.valueOf(6674, 14) // 6674*10^-14
    MaterialPoint center

    Gravity(MaterialPoint center) {
        this.center = center
    }

    @Override
    Vector calculateForce(Projectile projectile) {
        if (projectile == center) return new Vector(0)
        def direction = center.position - projectile.position
        return direction * (GRAVITATIONAL_CONSTANT * center.mass * projectile.mass / direction.length**3)
    }

    Vector escapeVelocity(Projectile projectile) {
        if (projectile == center) return new Vector(0)
        def direction = center.position - projectile.position
        direction = new Vector(-direction[1], direction[0])
        return direction * (Math.sqrt(GRAVITATIONAL_CONSTANT
                * center.mass / direction.length)) * (1/ direction.length)
    }
}
