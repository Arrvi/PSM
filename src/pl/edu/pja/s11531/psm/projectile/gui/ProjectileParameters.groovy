package pl.edu.pja.s11531.psm.projectile.gui

import groovy.beans.Bindable
import org.codehaus.groovy.runtime.typehandling.BigDecimalMath
import pl.edu.pja.s11531.psm.Vector

/**
 * Created by s11531 on 2016-11-22.
 */
class ProjectileParameters {
    @Bindable
    BigDecimal angle = 45
    @Bindable
    BigDecimal initialVelocity = 10
    @Bindable
    BigDecimal startX = 2
    @Bindable
    BigDecimal startY = 2
    @Bindable
    BigDecimal airFriction = 0.5
    @Bindable
    BigDecimal timeStep = 50

    Vector getStart() {
        new Vector(startX, startY)
    }

    Vector getVelocity() {
        new Vector(initialVelocity * Math.cos(angle), initialVelocity * Math.sin(angle))
    }
}
