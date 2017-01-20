package pl.edu.pja.s11531.psm.inclined.gui

import groovy.swing.SwingBuilder
import pl.edu.pja.s11531.psm.ConstantGravityPull
import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.inclined.InclinedPlane
import pl.edu.pja.s11531.psm.inclined.InclinedPlaneSimulation
import pl.edu.pja.s11531.psm.inclined.EulerRoundRigidBodyImpl

import javax.swing.JFrame
import javax.swing.UIManager
import javax.swing.WindowConstants
import java.awt.BorderLayout

/**
 * Created by s11531 on 2017-01-20.
 */
class InclinedPlaneSimulationGUI {
    InclinedPlaneSimulationPanel simulationPanel = new InclinedPlaneSimulationPanel(offsetX: 1, offsetY: 1)
    JFrame mainFrame
    Thread simulationThread
    SwingBuilder swingBuilder = new SwingBuilder()

    void buildGUI() {
        swingBuilder.edt {
            lookAndFeel UIManager.getSystemLookAndFeelClassName()
            mainFrame = frame(
                    title: 'Inclined Plane Simulation',
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
        def plane = new InclinedPlane(position: new Vector(3, 10), angle: -Math.PI / 10)
        def ball = new EulerRoundRigidBodyImpl(EulerRoundRigidBodyImpl.BALL_INERTIA)
        ball.externalForces << new ConstantGravityPull()
        ball.inclinedPlane = plane
        ball.radius = 2
        ball.position = plane.position + new Vector(-plane.angleVector[1], plane.angleVector[0]) * ball.radius

        def cylinder = new EulerRoundRigidBodyImpl(EulerRoundRigidBodyImpl.CYLINDER_INERTIA)
        cylinder.externalForces << new ConstantGravityPull()
        cylinder.inclinedPlane = plane
        cylinder.radius = 2
        cylinder.position = plane.position + new Vector(-plane.angleVector[1], plane.angleVector[0]) * cylinder.radius
        def ballSimulation = new InclinedPlaneSimulation(body: ball)
        def cylinderSimulation = new InclinedPlaneSimulation(body: cylinder)

        simulationPanel.simulations << ballSimulation
        simulationPanel.simulations << cylinderSimulation


        simulationThread = Thread.start {
            while (1) {
                simulationPanel.simulations*.step(0.01)
                sleep(50)
                swingBuilder.edt { mainFrame.repaint() }
            }
        }
    }

    def stopSimulation() {
        simulationThread.interrupt()
    }
}
