package pl.edu.pja.s11531.psm.projectile

import pl.edu.pja.s11531.psm.ExternalForce
import pl.edu.pja.s11531.psm.MaterialPoint
import pl.edu.pja.s11531.psm.Vector

/**
 * Created by s11531 on 2016-11-15.
 */
abstract class Projectile extends MaterialPoint {
    Vector velocity = new Vector(0.0, 0.0, 0.0)
    List<ExternalForce> externalForces = new ArrayList<>()

    abstract void move(BigDecimal timeChange);

    Vector getAcceleration() {
        force * (1 / mass)
    }

    Vector getForce() {
        getForce(position, velocity)
    }

    Vector getForce(Vector position, Vector velocity) {
        externalForces*.calculateForce(position, velocity).sum() as Vector
    }
}
