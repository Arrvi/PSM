package pl.edu.pja.s11531.psm.planets.gui

import groovy.swing.SwingBuilder
import pl.edu.pja.s11531.psm.ConstantGravityPull
import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.pendulum.EulerPendulumImpl
import pl.edu.pja.s11531.psm.pendulum.MidpointPendulumImpl
import pl.edu.pja.s11531.psm.pendulum.PendulumSimulation
import pl.edu.pja.s11531.psm.planets.CelestialBody
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
                    size: [600, 400],
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
        simulationPanel.simulation.addBody new MidpointCelestialBodyImpl(
                position: new Vector(10, 10), velocity: new Vector(0, 0),
                size: 30, mass: 10000000000)
        simulationPanel.simulation.addBody new MidpointCelestialBodyImpl(
                position: new Vector(10, 5), velocity: new Vector(-1, 0),
                size: 10, mass: 1000)
        simulationPanel.simulation.addBody new MidpointCelestialBodyImpl(
                position: new Vector(10, 4.5), velocity: new Vector(-0.9, 0),
                size: 5, mass: 1)
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