package pl.edu.pja.s11531.psm.pendulum

import pl.edu.pja.s11531.psm.projectile.Projectile
import pl.edu.pja.s11531.psm.Vector

/**
 * Created by kris on 12/1/16.
 */
interface Pendulum extends Projectile {
    Vector getAnchor()
    BigDecimal getRadius()
    BigDecimal getAngle()
    BigDecimal getAngularVelocity()
}