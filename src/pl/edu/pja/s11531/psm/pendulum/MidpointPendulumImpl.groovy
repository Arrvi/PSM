package pl.edu.pja.s11531.psm.pendulum

/**
 * Created by s11531 on 2016-11-25.
 */
class MidpointPendulumImpl extends PendulumImpl {
    BigDecimal direction = 1
    int swings = 0

    @Override
    void move(BigDecimal timeChange) {
        def angVelHalfStep = angularVelocity + angularAcceleration * timeChange / 2.0
        def angleHalfStep = angle + angularVelocity * timeChange / 2.0
        def angAccHalfStep = force.y / radius * sin(angleHalfStep)
        angle = angle + angVelHalfStep * timeChange
        angularVelocity = angularVelocity + angAccHalfStep * timeChange

        if (direction < 0 && angVelHalfStep > 0) swings++
        direction = angVelHalfStep
    }


}
