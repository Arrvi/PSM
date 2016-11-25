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
    void paintSimulation(Graphics g) {
        simulations.each {
            def anchor = transformPoint(it.pendulum.anchor)
            def position = transformPoint(it.pendulum.position)
            def velHead = transformPoint(it.pendulum.position + it.pendulum.velocity)

            g.color = Color.BLACK
            g.drawLine(anchor.x.intValue(), anchor.y.intValue(), position.x.intValue(), position.y.intValue())
            g.drawArc(position.x.intValue() - 5, position.y.intValue() - 5, 10, 10, 0, 360)
            g.color = Color.BLUE
            g.drawLine(position.x.intValue(), position.y.intValue(), velHead.x.intValue(), velHead.y.intValue())
        }
    }
}
