package pl.edu.pja.s11531.psm.pendulum.gui

import groovy.swing.SwingBuilder
import pl.edu.pja.s11531.psm.ConstantGravityPull
import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.pendulum.EulerPendulumImpl
import pl.edu.pja.s11531.psm.pendulum.MidpointPendulumImpl
import pl.edu.pja.s11531.psm.pendulum.PendulumSimulation

import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.WindowConstants
import java.awt.BorderLayout

/**
 * Created by s11531 on 2016-11-25.
 */
class PendulumSimulationGUI {
    PendulumSimulationPanel simulationPanel = new PendulumSimulationPanel(offsetX: 10, offsetY: 15)
    JFrame mainFrame
    Thread simulationThread
    Thread simulationThread2
    SwingBuilder swingBuilder = new SwingBuilder()

    void buildGUI() {
        swingBuilder.edt {
            lookAndFeel UIManager.getSystemLookAndFeelClassName()
            mainFrame = frame(
                    title: 'Projectile Simulation',
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
        simulationPanel.simulations.clear()
        def pendulum1 = new EulerPendulumImpl(
                mass: 1.0,
                angle: -45 * Math.PI / 180,
                anchor: new Vector(0, 0),
                radius: 8)
        pendulum1.externalForces << new ConstantGravityPull()
        PendulumSimulation simulation1 = new PendulumSimulation(pendulum1, 0.1)
//        simulationPanel.simulations << simulation1
        def pendulum2 = new MidpointPendulumImpl(
                mass: 1.0,
                angle: -45 * Math.PI / 180,
                anchor: new Vector(1, 0),
                radius: 8)
        pendulum2.externalForces << new ConstantGravityPull()
        PendulumSimulation simulation2 = new PendulumSimulation(pendulum2, 0.1)
        simulationPanel.simulations << simulation2
        simulationThread = Thread.start {
            while (1) {
                simulationPanel.simulations*.step(false)
                sleep(100)
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
