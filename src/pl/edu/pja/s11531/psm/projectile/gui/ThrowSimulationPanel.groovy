package pl.edu.pja.s11531.psm.projectile.gui

import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.gui.SimulationPanel
import pl.edu.pja.s11531.psm.projectile.ThrowSimulation

/**
 * Created by s11531 on 2016-11-18.
 */
class ThrowSimulationPanel extends SimulationPanel {
    List<ThrowSimulation> simulations = new ArrayList<>();

    @Override
    void paintSimulation(SimulationGraphics g) {
        simulations.each {
            List<Vector> positionsBuffer = new ArrayList<>(it.positions)
            positionsBuffer.inject(null, { Vector prev, Vector curr ->
                if (prev != null) {
                    g.drawLine(prev, curr)
                }
                return curr
            })
        }
    }
}
