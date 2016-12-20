package pl.edu.pja.s11531.psm.planets

import pl.edu.pja.s11531.psm.ExternalForce

import java.math.RoundingMode

/**
 * Created by s11531 on 2016-12-20.
 */
class StarSystemSimulation {
    final Map<CelestialBody, ExternalForce> bodiesToForces = [:]

    void addBody(CelestialBody body) {
        body.externalForces.addAll bodiesToForces.values()
        def newGravity = new Gravity(body)
        bodiesToForces.keySet().externalForces*.add newGravity
        bodiesToForces[body] = newGravity;
    }

    void step(BigDecimal deltaTime) {
        def bodies = bodiesToForces.keySet()
        bodies*.calculateForce() // preserve state
        bodies*.move(deltaTime)
    }
}
