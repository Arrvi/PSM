package pl.edu.pja.s11531.psm

import pl.edu.pja.s11531.psm.projectile.Projectile

/**
 * Created by s11531 on 2016-11-15.
 */
interface ExternalForce {
    Vector calculateForce(Projectile projectile)
}