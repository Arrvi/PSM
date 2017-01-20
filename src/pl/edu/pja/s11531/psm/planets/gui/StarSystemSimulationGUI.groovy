package pl.edu.pja.s11531.psm.planets.gui

import groovy.swing.SwingBuilder
import pl.edu.pja.s11531.psm.ConstantGravityPull
import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.pendulum.EulerPendulumImpl
import pl.edu.pja.s11531.psm.pendulum.MidpointPendulumImpl
import pl.edu.pja.s11531.psm.pendulum.PendulumSimulation
import pl.edu.pja.s11531.psm.planets.CelestialBody
import pl.edu.pja.s11531.psm.planets.Gravity
import pl.edu.pja.s11531.psm.planets.MidpointCelestialBodyImpl
import pl.edu.pja.s11531.psm.planets.StarSystemSimulation
import pl.edu.pja.s11531.psm.planets.gui.StarSystemSimulationPanel

import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.WindowConstants
import java.awt.BorderLayout

/**
 * Created by kris on 1/19/17.
 */
class StarSystemSimulationGUI {
    StarSystemSimulationPanel simulationPanel = new StarSystemSimulationPanel(offsetX: 1, offsetY: 1)
    JFrame mainFrame
    Thread simulationThread
    SwingBuilder swingBuilder = new SwingBuilder()

    void buildGUI() {
        swingBuilder.edt {
            lookAndFeel UIManager.getSystemLookAndFeelClassName()
            mainFrame = frame(
                    title: 'Star System Simulation',
                    size: [1000, 600],
                    show: true,
                    locationRelativeTo: null,
                    defaultCloseOperation: WindowConstants.EXIT_ON_CLOSE) {
                borderLayout()

                panel(simulationPanel, constraints: BorderLayout.CENTER)
            }
        }

        startSimulation()

    }

    def startSimulation() {
        simulationPanel.simulation = new StarSystemSimulation()
        def star = new MidpointCelestialBodyImpl(
                position: new Vector(10, 10), velocity: new Vector(0, 0),
                size: 30, mass: 15000000000)
        def planet2 = new MidpointCelestialBodyImpl(
                position: new Vector(10, 13), velocity: new Vector(0, 0),
                size: 10, mass: 10000000)
        def planet = new MidpointCelestialBodyImpl(
                position: new Vector(10, 3), velocity: new Vector(0, 0),
                size: 10, mass: 100000000)
        def moon = new MidpointCelestialBodyImpl(
                position: new Vector(10, 2.5), velocity: new Vector(-0.95, 0),
                size: 5, mass: 1000)
        simulationPanel.simulation.addBody star
        simulationPanel.simulation.addBody planet
        simulationPanel.simulation.addBody planet2
        simulationPanel.simulation.addBody moon

        planet.velocity = (simulationPanel.simulation.bodiesToForces[star] as Gravity)
                .escapeVelocity(planet)
        planet2.velocity = (simulationPanel.simulation.bodiesToForces[star] as Gravity)
                .escapeVelocity(planet2)
        moon.velocity = (simulationPanel.simulation.bodiesToForces[planet] as Gravity)
                .escapeVelocity(moon) * -1 + planet.velocity

        simulationThread = Thread.start {
            while (1) {
                simulationPanel.simulation.step(0.1)
                sleep(50)
                swingBuilder.edt { mainFrame.repaint() }
            }
        }
    }

    def stopSimulation() {
        simulationThread.interrupt()
    }

    BigDecimal parseBigDecimal(String text) {
        try {
            return new BigDecimal(text)
        } catch (NumberFormatException ignored) {
            return BigDecimal.ZERO
        }
    }
}