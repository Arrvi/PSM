package pl.edu.pja.s11531.psm.inclined

import pl.edu.pja.s11531.psm.MaterialPoint
import pl.edu.pja.s11531.psm.projectile.Projectile

/**
 * Created by kris on 1/20/17.
 */
interface RoundRigidBody extends Projectile {
    BigDecimal getRadius()
    BigDecimal getMomentOfInertia()
    BigDecimal getAngle()
    BigDecimal getAngularAcceleration()
    InclinedPlane getInclinedPlane()
    void setInclinedPlane(InclinedPlane plane)
}