package pl.edu.pja.s11531.psm.projectile

import pl.edu.pja.s11531.psm.ExternalForce
import pl.edu.pja.s11531.psm.Vector

/**
 * Created by s11531 on 2016-12-20.
 */
class ProjectileState implements Projectile {
    final Vector position
    final Vector velocity
    final BigDecimal mass

    ProjectileState(Vector position, Vector velocity, BigDecimal mass) {
        this.position = position
        this.velocity = velocity
        this.mass = mass
    }

    @Override
    void move(BigDecimal timeChange) {
        throw new UnsupportedOperationException()
    }

    @Override
    List<ExternalForce> getExternalForces() {
        throw new UnsupportedOperationException()
    }

    @Override
    Vector getAcceleration() {
        throw new UnsupportedOperationException()
    }

    @Override
    Vector getForce() {
        throw new UnsupportedOperationException()
    }
}
