package pl.edu.pja.s11531.psm.planets.gui

import pl.edu.pja.s11531.psm.gui.SimulationPanel
import pl.edu.pja.s11531.psm.planets.StarSystemSimulation

import javax.swing.JPanel
import java.awt.Color
import java.awt.Graphics

/**
 * Created by s11531 on 2016-12-20.
 */
class StarSystemSimulationPanel extends SimulationPanel {
    StarSystemSimulation simulation

    @Override
    void paintSimulation(SimulationGraphics sg) {
        simulation.bodies.each {
            sg.color = Color.black
            sg.drawCircleAround it.position, it.size.intValue()
            sg.drawLine it.position, it.position + it.force
        }
        sg.color = Color.magenta
        sg.drawCircleAround simulation.centerOfMass, 5
    }
}
