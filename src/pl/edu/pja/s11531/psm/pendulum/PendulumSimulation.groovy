package pl.edu.pja.s11531.psm.pendulum

import java.math.RoundingMode

/**
 * Created by s11531 on 2016-11-25.
 */
class PendulumSimulation {
    BigDecimal time = 0
    BigDecimal timeStep
    Pendulum pendulum
    BigDecimal firstEnergy

    PendulumSimulation(Pendulum pendulum, BigDecimal timeStep = 0.05) {
        this.pendulum = pendulum
        this.timeStep = timeStep
    }

    void simulateWhile(Closure<Boolean> condition, boolean sleepAfterMove = false, Closure callback = {}) {
        while (condition(pendulum, time)) {
            step(sleepAfterMove, callback)
            if (sleepAfterMove) {
                sleep((timeStep * 1000).setScale(0, RoundingMode.HALF_UP).intValue(), { condition = { a, b -> false } })
            }
        }
    }

    void step(boolean sleepAfterMove, Closure callback = {}) {
        pendulum.move(timeStep)
        if (firstEnergy == null) {
            firstEnergy = pendulum.kineticEnergy.add(pendulum.potentialEnergy)
        }
        def KEVal = pendulum.kineticEnergy.setScale(2, RoundingMode.HALF_UP)
        def PEVal = pendulum.potentialEnergy.setScale(2, RoundingMode.HALF_UP)
        def sum = pendulum.kineticEnergy.add(pendulum.potentialEnergy).setScale(20, RoundingMode.HALF_UP)
        def errVal = pendulum.kineticEnergy.add(pendulum.potentialEnergy).subtract(firstEnergy).abs().setScale(20, RoundingMode.HALF_UP)
        println "KE: $KEVal\tPE: $PEVal\tSUM: $sum\terror: $errVal"
        time += timeStep
        if (sleepAfterMove)
            callback()
    }
}
