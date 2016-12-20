package pl.edu.pja.s11531.psm.projectile

import pl.edu.pja.s11531.psm.Vector

/**
 * Created by s11531 on 2016-11-25.
 */
class MidpointProjectileImpl extends ProjectileImpl {
    @Override
    void move(BigDecimal timeChange) {
        def posT2 = position + velocity * (timeChange / 2)
        def velT2 = velocity + acceleration * (timeChange / 2)
        def accT2 = getForce(new ProjectileState(posT2, velT2, mass))

        position += velT2 * timeChange
        velocity += accT2 * timeChange
    }
}
