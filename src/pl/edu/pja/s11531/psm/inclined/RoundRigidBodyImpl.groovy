package pl.edu.pja.s11531.psm.inclined

import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.projectile.ProjectileImpl

/**
 * Created by kris on 1/20/17.
 */
trait RoundRigidBodyImpl implements RoundRigidBody {
    BigDecimal angle = 0.0
    BigDecimal radius = 1.0
    InclinedPlane inclinedPlane

    Vector getAcceleration() {
        def F = force
        def Th = inclinedPlane.angle - F.angle
        def cosTh = Math.cos Th
        def sinTh = Math.sin Th
        def Fpar = new Vector(F.length * cosTh * cosTh, F.length * cosTh * sinTh)

        return Fpar * (1.0 / (1.0+momentOfInertia/(mass*radius*radius)))
    }

    @Override
    BigDecimal getAngularAcceleration() {
        return -acceleration.length / radius
    }

    @Override
    Vector getRadiusVector() {
        new Vector(Math.cos(angle) * radius, Math.sin(angle) * radius)
    }
}
