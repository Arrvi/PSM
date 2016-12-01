package pl.edu.pja.s11531.psm.pendulum

/**
 * Created by s11531 on 2016-11-25.
 */
class EulerPendulumImpl extends PendulumImpl {
    @Override
    void move(BigDecimal timeChange) {
        angularVelocity = angularVelocity + angularAcceleration * timeChange
        angle = angle + angularVelocity * timeChange
    }
}
