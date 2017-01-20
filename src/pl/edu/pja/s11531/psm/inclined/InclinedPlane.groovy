package pl.edu.pja.s11531.psm.inclined

import pl.edu.pja.s11531.psm.Vector

/**
 * Created by kris on 1/20/17.
 */
class InclinedPlane {
    Vector position
    BigDecimal angle

    Vector getAngleVector() {
        new Vector(Math.cos(angle), Math.sin(angle))
    }
}
