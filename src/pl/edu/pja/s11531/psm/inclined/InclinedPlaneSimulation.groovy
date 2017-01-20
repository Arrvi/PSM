package pl.edu.pja.s11531.psm.inclined

import pl.edu.pja.s11531.psm.inclined.InclinedPlane
import pl.edu.pja.s11531.psm.inclined.RoundRigidBody

/**
 * Created by kris on 1/20/17.
 */
class InclinedPlaneSimulation {
    RoundRigidBody body

    void setInclinedPlane(InclinedPlane plane) {
        body.inclinedPlane = plane
    }
    InclinedPlane getInclinedPlane() {
        body.inclinedPlane
    }

    void step(BigDecimal deltaTime) {
        body.move(deltaTime)
    }
}
