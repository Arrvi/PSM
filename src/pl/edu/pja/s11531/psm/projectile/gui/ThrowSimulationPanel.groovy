package pl.edu.pja.s11531.psm.projectile.gui

import pl.edu.pja.s11531.psm.projectile.ThrowSimulation
import pl.edu.pja.s11531.psm.Vector

import javax.swing.JPanel
import java.awt.Color
import java.awt.Graphics
import java.math.RoundingMode

/**
 * Created by s11531 on 2016-11-18.
 */
class ThrowSimulationPanel extends JPanel {
    List<ThrowSimulation> simulations = new ArrayList<>();

    BigDecimal maxY = 20;
    BigDecimal offsetY = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g)

        if (offsetY) {
            def g1 = transformPoint(new Vector(0.0, 0.0)), g2 = transformPoint(new Vector(maxX, 0.0))
            g.color = Color.RED
            g.drawLine(g1.x.intValue(), g1.y.intValue(), g2.x.intValue(), g2.y.intValue())
        }

        g.color = Color.BLACK
        simulations.each {
            it.positions.inject(null, { Vector prev, Vector curr ->
                if (prev != null) {
                    Vector a, b;
                    a = transformPoint(prev)
                    b = transformPoint(curr)

                    g.drawLine(a.x.intValue(), a.y.intValue(), b.x.intValue(), b.y.intValue())
                }
                return curr
            })
        }
    }

    public Vector transformPoint (Vector point) {
        if (point == null) {
            return new Vector(0.0, 0.0)
        }
        def vector = new Vector(width * point.x / maxX, height - height * (point.y + offsetY) / maxY)
        vector.scalars.collect {it.setScale(0, RoundingMode.HALF_UP)}
        return vector
    }

    public BigDecimal getMaxX() {
        BigDecimal ratio = (BigDecimal) width / height
        return maxY * ratio;
    }
}
