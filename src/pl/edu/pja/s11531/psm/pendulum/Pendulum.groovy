package pl.edu.pja.s11531.psm.pendulum

import pl.edu.pja.s11531.psm.ExternalForce
import pl.edu.pja.s11531.psm.MaterialPoint
import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.taylor.CosTaylorSeries
import pl.edu.pja.s11531.psm.taylor.SinTaylorSeries

abstract class Pendulum extends MaterialPoint {
    Vector anchor
    BigDecimal radius
    BigDecimal angle
    BigDecimal angularVelocity = 0
    def sin = new SinTaylorSeries().getClosure(20)
    def cos = new CosTaylorSeries().getClosure(20)

    List<ExternalForce> externalForces = new ArrayList<>()

    abstract void move(BigDecimal timeChange);

    // TODO X acc
    BigDecimal getAngularAcceleration() {
        force.y / radius * sin(angle)
    }

    Vector getForce() {
        externalForces*.calculateForce(position, velocity).sum() as Vector
    }

    Vector getPosition() {
        anchor + new Vector(-radius * sin(angle), -radius * cos(angle))
    }

    Vector getVelocity() {
        def scalarVelocity = radius * angularVelocity
        def velocityAngle = angle - Math.PI / 2
        new Vector(scalarVelocity * sin(velocityAngle), scalarVelocity * cos(velocityAngle))
    }

    BigDecimal getKineticEnergy() {
        def e = mass * velocity.length.pow(2) / 2
        return e ?: 0.0
    }

    BigDecimal getPotentialEnergy() {
        def e = -mass * force.y * height
        return e ?: 0.0
    }

    BigDecimal getHeight() {
        radius - radius * cos(angle)
    }
}