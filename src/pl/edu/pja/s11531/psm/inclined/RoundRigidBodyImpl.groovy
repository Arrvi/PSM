package pl.edu.pja.s11531.psm.inclined

import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.projectile.ProjectileImpl

/**
 * Created by kris on 1/20/17.
 */
trait RoundRigidBodyImpl {
    BigDecimal angle = 0.0
    BigDecimal radius = 1.0
    InclinedPlane inclinedPlane

    Vector getAcceleration() {
        def F = force
        def Th = F.angle - inclinedPlane.angle
        def cosTh = Math.cos Th
        def sinTh = Math.sin Th
        def Fpar = new Vector(F.length * cosTh * cosTh, F.length * cosTh * sinTh)

        return Fpar * (1.0 / (1.0+momentOfInertia/(mass*radius*radius)))
    }
}
