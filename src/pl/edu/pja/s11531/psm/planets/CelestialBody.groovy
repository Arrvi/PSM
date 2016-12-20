package pl.edu.pja.s11531.psm.planets

import pl.edu.pja.s11531.psm.projectile.Projectile

/**
 * Created by kris on 12/1/16.
 */
interface CelestialBody extends Projectile {
    BigDecimal getSize()

    void calculateForce()
}
