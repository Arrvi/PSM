package pl.edu.pja.s11531.psm.planets.gui

import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.gui.SimulationPanel
import pl.edu.pja.s11531.psm.planets.CelestialBody
import pl.edu.pja.s11531.psm.planets.StarSystemSimulation

import java.awt.Color
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.image.BufferedImage

/**
 * Created by s11531 on 2016-12-20.
 */
class StarSystemSimulationPanel extends SimulationPanel {
    StarSystemSimulation simulation
    BufferedImage orbits
    Graphics2D graphics

    static final Color[] availColors = [
            Color.orange.darker(),
            Color.green.darker(),
            Color.red.darker(),
            Color.blue]

    Map<CelestialBody, Vector> lastPositions
    Map<CelestialBody, Color> colors = new HashMap<>()

    @Override
    void paintSimulation(SimulationGraphics sg) {
        if ( !orbits || orbits.width != width || orbits.height != height ) {
            orbits = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
            graphics = orbits.createGraphics()
            graphics.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.color = Color.white
            graphics.drawRect(0, 0, width, height)
        }

        simulation.bodies
                .findAll {!colors.keySet().contains(it)}
                .each {colors[it] = availColors[colors.size()] ?: Color.BLACK}

        if (lastPositions && graphics) {
            simulation.bodies.each {
                graphics.color = colors[it]
                def lastPos = lastPositions.get(it)
                if (lastPos) {
                    int x1,y1,x2,y2;
                    (x1, y1) = sg.transformPoint(it.position)
                    (x2, y2) = sg.transformPoint(lastPos)
                    graphics.drawLine x1, y1, x2, y2
                }
            }
        }
        lastPositions = simulation.bodies.collectEntries {
            [it, it.position]
        }

        simulation.bodies.each {
            sg.color = colors[it]
            sg.drawCircleAround it.position, it.size.intValue()
        }
//        sg.color = Color.magenta
//        sg.drawCircleAround simulation.centerOfMass, 5
        sg.drawImage orbits, 0, 0, null
    }
}
