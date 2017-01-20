package pl.edu.pja.s11531.psm.inclined

import pl.edu.pja.s11531.psm.MaterialPoint
import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.projectile.Projectile

/**
 * Created by kris on 1/20/17.
 */
interface RoundRigidBody extends Projectile {
    BigDecimal getRadius()
    BigDecimal getMomentOfInertia()
    BigDecimal getAngle()

    BigDecimal getAngularVelocity()
    BigDecimal getAngularAcceleration()
    InclinedPlane getInclinedPlane()
    void setInclinedPlane(InclinedPlane plane)

    Vector getRadiusVector()
}