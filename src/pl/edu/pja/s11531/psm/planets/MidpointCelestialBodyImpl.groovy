package pl.edu.pja.s11531.psm.planets

import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.projectile.MidpointProjectileImpl

/**
 * Created by s11531 on 2016-12-20.
 */
class MidpointCelestialBodyImpl extends MidpointProjectileImpl implements CelestialBody {
    BigDecimal size
    Vector force = new Vector(0.0)

    void calculateForce() {
        force = super.force
    }
}
