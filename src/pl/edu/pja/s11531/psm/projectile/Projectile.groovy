package pl.edu.pja.s11531.psm.projectile

import pl.edu.pja.s11531.psm.ExternalForce
import pl.edu.pja.s11531.psm.MaterialPoint
import pl.edu.pja.s11531.psm.MaterialPointImpl
import pl.edu.pja.s11531.psm.Vector

/**
 * Created by s11531 on 2016-11-15.
 */
interface Projectile extends MaterialPoint {
    void move(BigDecimal timeChange);

    List<ExternalForce> getExternalForces()

    Vector getVelocity()

    Vector getAcceleration()

    Vector getForce()
}
