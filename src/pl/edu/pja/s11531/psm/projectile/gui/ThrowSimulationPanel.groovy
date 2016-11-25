package pl.edu.pja.s11531.psm.projectile.gui

import pl.edu.pja.s11531.psm.gui.SimulationPanel
import pl.edu.pja.s11531.psm.projectile.ThrowSimulation
import pl.edu.pja.s11531.psm.Vector

import javax.swing.JPanel
import java.awt.Color
import java.awt.Graphics
import java.math.RoundingMode

/**
 * Created by s11531 on 2016-11-18.
 */
class ThrowSimulationPanel extends SimulationPanel {
    List<ThrowSimulation> simulations = new ArrayList<>();

    @Override
    void paintSimulation(Graphics g) {
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
}
