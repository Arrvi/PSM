package pl.edu.pja.s11531.psm.projectile

import pl.edu.pja.s11531.psm.Vector

/**
 * Created by s11531 on 2016-11-15.
 */
class EulerProjectileImpl extends ProjectileImpl {
    transient Vector positionChange
    transient Vector velocityChange

    @Override
    void move(BigDecimal timeChange) {
        calculateVelocity(timeChange)
        calculatePosition(timeChange)
    }

    void resetDeltas() {
        positionChange = null
        velocityChange = null
    }

    void calculateVelocity(BigDecimal timeChange) {
        velocityChange = acceleration * timeChange
        velocity += velocityChange
    }

    void calculatePosition(BigDecimal timeChange) {
        assert velocityChange != null
        positionChange = velocity * timeChange
        position += positionChange
    }
}
