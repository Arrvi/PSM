package pl.edu.pja.s11531.psm.projectile.gui

import groovy.swing.SwingBuilder

import javax.swing.*
import java.awt.*

/**
 * Created by s11531 on 2016-11-15.
 */
class ProjectileSimulation {
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

                panel(constraints: BorderLayout.CENTER) {
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
    }
}
