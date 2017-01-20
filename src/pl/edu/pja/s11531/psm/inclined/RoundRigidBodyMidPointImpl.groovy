package pl.edu.pja.s11531.psm.inclined

import pl.edu.pja.s11531.psm.projectile.MidpointProjectileImpl

/**
 * Created by kris on 1/20/17.
 */
class RoundRigidBodyMidPointImpl extends MidpointProjectileImpl implements RoundRigidBody, RoundRigidBodyImpl {
    static final InertiaCalculator BALL_INERTIA = {BigDecimal mass, BigDecimal radius -> (2/5)*mass*radius*radius }
    static final InertiaCalculator CYLINDER_INERTIA = {BigDecimal mass, BigDecimal radius -> (1/2)*mass*radius*radius }
    final InertiaCalculator calculateInertia

    RoundRigidBodyMidPointImpl(InertiaCalculator inertiaCalculator) {
        this.calculateInertia = inertiaCalculator
    }

    @Override
    BigDecimal getMomentOfInertia() {
        return calculateInertia(mass, radius)
    }

    @Override
    BigDecimal getAngularAcceleration() {
        return null
    }

    static class InertiaCalculator extends Closure<BigDecimal> {
        InertiaCalculator(Object owner, Object thisObject) {
            super(owner, thisObject)
        }

        InertiaCalculator(Object owner) {
            super(owner)
        }
    }
}
