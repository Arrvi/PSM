package pl.edu.pja.s11531.psm.projectile.gui

import groovy.swing.SwingBuilder
import pl.edu.pja.s11531.psm.AirFriction
import pl.edu.pja.s11531.psm.ConstantGravityPull
import pl.edu.pja.s11531.psm.projectile.EulerProjectileImpl
import pl.edu.pja.s11531.psm.projectile.MidpointProjectileImpl
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
    ProjectileParameters model = new ProjectileParameters()

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
                                label 'Friction constant'
                            }
                            td(colfill: true) {
                                textField(id: 'airFrictField', text: model.airFriction)
                            }
                        }
                        tr {
                            td {
                                label 'Time step (ms)'
                            }
                            td(colfill: true) {
                                textField(id: 'timeField', text: model.timeStep)
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
                    startY: bind { parseBigDecimal(startYField.text) },
                    airFriction: bind { parseBigDecimal(airFrictField.text) },
                    timeStep: bind { parseBigDecimal(timeField.text) })
        }

    }

    def startSimulation() {
        simulationPanel.simulations.clear()
        def gravity = new ConstantGravityPull()
        def air = new AirFriction(model.airFriction)
        def projectile1 = new EulerProjectileImpl(mass: 1.0, position: model.start, velocity: model.velocity)
        projectile1.externalForces << gravity
        projectile1.externalForces << air
        ThrowSimulation simulation1 = new ThrowSimulation(projectile1, model.timeStep / 1000)
        simulationPanel.simulations << simulation1
        def projectile2 = new MidpointProjectileImpl(mass: 1.0, position: model.start, velocity: model.velocity)
        projectile2.externalForces << gravity
        projectile2.externalForces << air
        ThrowSimulation simulation2 = new ThrowSimulation(projectile2, model.timeStep / 1000)
        simulationPanel.simulations << simulation2
        simulationThread = Thread.start {
            while (simulationPanel.simulations*.projectile.position.y.any { it > -10 }) {
                simulationPanel.simulations*.step(false)
                swingBuilder.edt { mainFrame.repaint() }
                sleep(model.timeStep.intValue())
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
