package pl.edu.pja.s11531.psm.inclined

import pl.edu.pja.s11531.psm.projectile.EulerProjectileImpl
import pl.edu.pja.s11531.psm.projectile.MidpointProjectileImpl

/**
 * Created by kris on 1/20/17.
 */
class EulerRoundRigidBodyImpl extends EulerProjectileImpl implements RoundRigidBody, RoundRigidBodyImpl {
    static
    final Closure<BigDecimal> BALL_INERTIA = { BigDecimal mass, BigDecimal radius -> (2 / 5) * mass * radius * radius }
    static
    final Closure<BigDecimal> CYLINDER_INERTIA = { BigDecimal mass, BigDecimal radius -> (1 / 2) * mass * radius * radius }
    final Closure<BigDecimal> calculateInertia

    BigDecimal angularVelocity = 0.0

    EulerRoundRigidBodyImpl(Closure<BigDecimal> inertiaCalculator) {
        this.calculateInertia = inertiaCalculator
    }

    @Override
    BigDecimal getMomentOfInertia() {
        return calculateInertia(mass, radius)
    }

    @Override
    void move(BigDecimal timeChange) {
        super.move(timeChange)
        angularVelocity = angularVelocity + angularAcceleration * timeChange
        angle = angle + angularVelocity * timeChange
    }
}
