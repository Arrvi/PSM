package pl.edu.pja.s11531.psm.planets

import pl.edu.pja.s11531.psm.ExternalForce
import pl.edu.pja.s11531.psm.Vector

import java.math.RoundingMode

/**
 * Created by s11531 on 2016-12-20.
 */
class StarSystemSimulation {
    final Map<CelestialBody, ExternalForce> bodiesToForces = [:]

    Set<CelestialBody> getBodies() {
        bodiesToForces.keySet()
    }

    void addBody(CelestialBody body) {
        body.externalForces.addAll bodiesToForces.values()
        def newGravity = new Gravity(body)
        bodiesToForces.keySet().externalForces*.add newGravity
        bodiesToForces[body] = newGravity;
    }

    Vector getCenterOfMass() {
        BigDecimal systemMassInverted = 1 / (bodies*.mass.sum() as BigDecimal)
        return bodies.collect {it.position * it.mass * systemMassInverted}.sum() as Vector
    }

    void step(BigDecimal deltaTime) {
        bodies*.calculateForce() // preserve state
        bodies*.move(deltaTime)
    }
}
