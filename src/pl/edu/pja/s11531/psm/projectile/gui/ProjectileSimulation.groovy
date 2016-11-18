package pl.edu.pja.s11531.psm.projectile.gui

import groovy.swing.SwingBuilder
import pl.edu.pja.s11531.psm.ConstantGravityPull
import pl.edu.pja.s11531.psm.Vector
import pl.edu.pja.s11531.psm.projectile.EulerProjectileImpl
import pl.edu.pja.s11531.psm.projectile.Projectile
import pl.edu.pja.s11531.psm.projectile.ThrowSimulation

import javax.swing.*
import java.awt.*

/**
 * Created by s11531 on 2016-11-15.
 */
class ProjectileSimulation {
    ThrowSimulationPanel simulationPanel = new ThrowSimulationPanel();
    void buildGUI() {

        SwingBuilder swingBuilder = new SwingBuilder()
        swingBuilder.edt {
            lookAndFeel UIManager.getSystemLookAndFeelClassName()
            frame(
                    title: 'Projectile Simulation',
                    size: [600, 400],
                    show: true,
                    locationRelativeTo: null,
                    defaultCloseOperation: WindowConstants.EXIT_ON_CLOSE) {
                borderLayout()

                panel(simulationPanel, constraints: BorderLayout.CENTER) {
                    label 'CANVAS'
                }

                panel(constraints: BorderLayout.EAST) {
                    tableLayout {
                        tr {
                            td {
                                label 'Angle'
                            }
                            td(colfill: true) {
                                textField()
                            }
                        }
                        tr {
                            td {
                                label 'Initial velocity'
                            }
                            td(colfill: true) {
                                textField()
                            }
                        }
                        tr {
                            td {
                                button 'Stop'
                            }
                            td {
                                button 'Simulate'
                            }
                        }
                    }
                }
            }
        }

        def projectile = new EulerProjectileImpl(mass: 1.0, position: new Vector(0.0, 0.0), velocity: new Vector(1.0, 1.0))
        projectile.externalForces << new ConstantGravityPull()
        ThrowSimulation simulation = new ThrowSimulation(projectile)
        simulationPanel.simulations << simulation
        simulation.simulateWhile({Projectile proj, BigDecimal time -> proj.position[1] > -10.0})
    }
}
