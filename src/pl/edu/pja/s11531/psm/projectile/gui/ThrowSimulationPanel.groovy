package pl.edu.pja.s11531.psm.projectile.gui

import pl.edu.pja.s11531.psm.projectile.ThrowSimulation
import pl.edu.pja.s11531.psm.Vector

import javax.swing.JPanel
import java.awt.Graphics
import java.math.RoundingMode

/**
 * Created by s11531 on 2016-11-18.
 */
class ThrowSimulationPanel extends JPanel {
    List<ThrowSimulation> simulations = new ArrayList<>();

    BigDecimal maxY = 20;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g)

        simulations.each {
            it.positions.inject(transformPoint(new Vector(0.0, 0.0)), {Vector prev, Vector curr ->
                Vector a, b;
                a = transformPoint(prev)
                b = transformPoint(curr)
                g.drawLine(a[0].intValue(), a[1].intValue(), b[0].intValue(), b[1].intValue())
            })
        }
    }

    public Vector transformPoint (Vector point) {
        BigDecimal ratio = (BigDecimal) getWidth() / getHeight()
        BigDecimal maxX = maxY * ratio;
        def vector = new Vector(point.scalars[0] / maxX, getHeight() - point.scalars[1] / maxY)
        vector.scalars.collect {it.setScale(0, RoundingMode.HALF_UP)}
        return vector
    }
}
