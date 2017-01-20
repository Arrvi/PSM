package pl.edu.pja.s11531.psm.projectile

import pl.edu.pja.s11531.psm.ExternalForce
import pl.edu.pja.s11531.psm.MaterialPointImpl
import pl.edu.pja.s11531.psm.Vector

/**
 * Created by kris on 12/1/16.
 */
abstract class ProjectileImpl extends MaterialPointImpl implements Projectile {
    Vector velocity = new Vector(0.0, 0.0, 0.0)
    List<ExternalForce> externalForces = new ArrayList<>()

    @Override
    Vector getAcceleration() {
        force * (1 / mass)
    }

    @Override
    Vector getForce() {
        getForce(this) ?: new Vector(0.0)
    }

    Vector getForce(Projectile projectile) {
        externalForces*.calculateForce(projectile).sum() as Vector
    }
}
