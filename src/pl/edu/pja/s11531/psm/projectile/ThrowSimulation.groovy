package pl.edu.pja.s11531.psm.projectile

import pl.edu.pja.s11531.psm.Vector

import java.math.RoundingMode

/**
 * Created by s11531 on 2016-11-18.
 */
class ThrowSimulation {
    BigDecimal timeStep
    BigDecimal time = 0
    Projectile projectile
    List<Vector> positions

    ThrowSimulation(Projectile projectile, BigDecimal timeStep = 0.05) {
        this.timeStep = timeStep
        this.projectile = projectile
        positions = new ArrayList<>();
        positions << projectile.position
    }

    void simulateWhile(Closure<Boolean> condition, boolean sleepAfterMove=false) {
        while (condition(projectile, time)) {
            step()
            if ( sleepAfterMove ) {
                sleep((timeStep * 1000).setScale(0, RoundingMode.HALF_UP).intValue(), {condition={false}})
            }
            println projectile.position
        }
    }

    void step() {
        projectile.move(timeStep)
        positions << projectile.position
        time += timeStep
    }
}
