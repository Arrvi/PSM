package pl.edu.pja.s11531.psm.projectile.gui

import groovy.swing.SwingBuilder

import pl.edu.pja.s11531.psm.ConstantGravityPull
import pl.edu.pja.s11531.psm.projectile.EulerProjectileImpl
import pl.edu.pja.s11531.psm.projectile.Projectile
import pl.edu.pja.s11531.psm.projectile.ThrowSimulation

import javax.swing.*
import java.awt.*

/**
 * Created by s11531 on 2016-11-15.
 */
class ProjectileSimulationGUI {
    ThrowSimulationPanel simulationPanel = new ThrowSimulationPanel()
    JFrame mainFrame
    Thread simulationThread
    SwingBuilder swingBuilder = new SwingBuilder()
    SimulationParameters model = new SimulationParameters()

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

                panel(constraints: BorderLayout.EAST) {
                    tableLayout {
                        tr {
                            td {
                                label 'Angle'
                            }
                            td(colfill: true) {
                                textField(id: 'angleField', text: model.angle)
                            }
                        }
                        tr {
                            td {
                                label 'Initial velocity'
                            }
                            td(colfill: true) {
                                def input = textField(id: 'initVelField', text: model.initialVelocity)
                            }
                        }
                        tr {
                            td(colspan: 2) {
                                label 'Start point'
                            }
                        }
                        tr {
                            td(colfill: true) {
                                textField(id: 'startXField', text: model.startX)
                            }
                            td(colfill: true) {
                                textField(id: 'startYField', text: model.startY)
                            }
                        }
                        tr {
                            td {
                                button(text: 'Stop', actionPerformed: { stopSimulation() })
                            }
                            td {
                                button(text: 'Simulate', actionPerformed: { startSimulation() })
                            }
                        }
                    }
                }
            }
            bean(model,
                    angle: bind { parseBigDecimal(angleField.text) * Math.PI / 180 },
                    initialVelocity: bind { parseBigDecimal(initVelField.text) },
                    startX: bind { parseBigDecimal(startXField.text) },
                    startY: bind { parseBigDecimal(startYField.text) })
        }

    }

    def startSimulation() {
        simulationPanel.simulations.clear()
        def projectile = new EulerProjectileImpl(mass: 1.0, position: model.start, velocity: model.velocity)
        projectile.externalForces << new ConstantGravityPull()
        ThrowSimulation simulation = new ThrowSimulation(projectile, 0.03)
        simulationPanel.simulations << simulation
        simulationThread = Thread.start {
            simulation.simulateWhile({ Projectile proj, BigDecimal time -> proj.position[1] > -10.0 }, true) {
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
