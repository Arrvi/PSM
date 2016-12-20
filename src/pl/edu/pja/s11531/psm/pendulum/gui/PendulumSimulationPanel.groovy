package pl.edu.pja.s11531.psm.pendulum.gui

import pl.edu.pja.s11531.psm.gui.SimulationPanel
import pl.edu.pja.s11531.psm.pendulum.PendulumSimulation

import java.awt.Color
import java.awt.Graphics

/**
 * Created by s11531 on 2016-11-25.
 */
class PendulumSimulationPanel extends SimulationPanel {
    List<PendulumSimulation> simulations = new ArrayList<>();

    @Override
    void paintSimulation(SimulationGraphics g) {
        simulations.each {
            def anchor = it.pendulum.anchor
            def position = it.pendulum.position
            def velHead = it.pendulum.position + it.pendulum.velocity

            g.color = Color.BLACK
            g.drawLine(anchor, position)
            g.drawCircleAround(position, 10)
            g.color = Color.BLUE
            g.drawLine(position, velHead)
        }
    }
}
