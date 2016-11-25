package pl.edu.pja.s11531.psm.gui

import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.projectile.ThrowSimulation

import javax.swing.JPanel
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.math.RoundingMode

/**
 * Created by s11531 on 2016-11-25.
 */
abstract class SimulationPanel extends JPanel {
    BigDecimal maxY = 20
    BigDecimal offsetY = 10
    BigDecimal offsetX = 0

    abstract void paintSimulation(Graphics g)

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g)
        ((Graphics2D) g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (offsetY) {
            def g1 = transformPoint(new Vector(0.0, 0.0)), g2 = transformPoint(new Vector(maxX, 0.0))
            g.color = Color.RED
            g.drawLine(g1.x.intValue(), g1.y.intValue(), g2.x.intValue(), g2.y.intValue())
        }
        if (offsetX) {
            def g1 = transformPoint(new Vector(0.0, 0.0)), g2 = transformPoint(new Vector(0.0, maxY))
            g.color = Color.RED
            g.drawLine(g1.x.intValue(), g1.y.intValue(), g2.x.intValue(), g2.y.intValue())
        }

        g.color = Color.BLACK
        paintSimulation(g)
    }

    public Vector transformPoint(Vector point) {
        if (point == null) {
            return new Vector(0.0, 0.0)
        }
        def vector = new Vector(width * (point.x + offsetX) / maxX, height - height * (point.y + offsetY) / maxY)
        vector.scalars.collect { it.setScale(0, RoundingMode.HALF_UP) }
        return vector
    }

    public BigDecimal getMaxX() {
        BigDecimal ratio = (BigDecimal) width / height
        return maxY * ratio;
    }
}
