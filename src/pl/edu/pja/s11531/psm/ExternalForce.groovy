package pl.edu.pja.s11531.psm

/**
 * Created by s11531 on 2016-11-15.
 */
interface ExternalForce {
    Vector calculateForce(Vector position, Vector velocity)
}