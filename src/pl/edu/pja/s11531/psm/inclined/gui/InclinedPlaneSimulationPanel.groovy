package pl.edu.pja.s11531.psm.inclined.gui

import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.gui.SimulationPanel
import pl.edu.pja.s11531.psm.inclined.InclinedPlaneSimulation
import pl.edu.pja.s11531.psm.inclined.RoundRigidBody
import pl.edu.pja.s11531.psm.planets.CelestialBody

import java.awt.Color
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.image.BufferedImage

/**
 * Created by kris on 1/20/17.
 */
class InclinedPlaneSimulationPanel extends SimulationPanel {
    List<InclinedPlaneSimulation> simulations = []
    BufferedImage orbits
    Graphics2D graphics

    Map<RoundRigidBody, Vector> lastPositions

    @Override
    void paintSimulation(SimulationGraphics sg) {
        if (!orbits || orbits.width != width || orbits.height != height) {
            orbits = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
            graphics = orbits.createGraphics()
            graphics.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.color = Color.white
            graphics.drawRect(0, 0, width, height)
        }
        if (lastPositions && graphics) {
            simulations.body.each {
                graphics.color = Color.red
                def lastPos = lastPositions.get(it)
                if (lastPos) {
                    int x1, y1, x2, y2;
                    (x1, y1) = sg.transformPoint(it.position + it.radiusVector)
                    (x2, y2) = sg.transformPoint(lastPos)
                    graphics.drawLine x1, y1, x2, y2
                }
            }
        }
        lastPositions = simulations*.body.collectEntries { body ->
            [body, body.position + body.radiusVector]
        }

        simulations.each { sim ->
            sg.drawLine sim.inclinedPlane.position, sim.inclinedPlane.position + sim.inclinedPlane.angleVector * maxX * 0.5
            sg.drawCircleAround sim.body.position, sg.transformLength(sim.body.radius) * 2
            sg.drawLine sim.body.position, sim.body.position + sim.body.radiusVector
        }

        sg.drawImage orbits, 0, 0, null
    }
}
