package pl.edu.pja.s11531.psm.projectile

import pl.edu.pja.s11531.psm.ExternalForce
import pl.edu.pja.s11531.psm.MaterialPoint
import pl.edu.pja.s11531.psm.Vector

/**
 * Created by s11531 on 2016-11-15.
 */
abstract class Projectile extends MaterialPoint {
    Vector velocity
    Vector acceleration
    List<ExternalForce> externalForces

    abstract void move(BigDecimal timeChange);

    Vector getAcceleration() {
        force * (1 / mass)
    }

    Vector getForce() {
        externalForces*.calculateForce(position, velocity).sum() as Vector
    }
}
