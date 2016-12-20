package pl.edu.pja.s11531.psm.planets

import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.projectile.EulerProjectileImpl

/**
 * Created by s11531 on 2016-12-20.
 */
class EulerCelestialBodyImpl extends EulerProjectileImpl implements CelestialBody {
    BigDecimal size
    Vector force

    void calculateForce() {
        force = super.force
    }
}
