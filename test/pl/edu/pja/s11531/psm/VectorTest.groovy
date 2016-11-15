package pl.edu.pja.s11531.psm

/**
 * Created by s11531 on 2016-11-15.
 */

import spock.lang.*

@Unroll
class VectorTest extends Specification {
    def "Plus"() {
        expect:
        Vector v1 = new Vector(a.toArray() as BigDecimal[])
        Vector v2 = new Vector(b.toArray() as BigDecimal[])
        (v1 + v2).scalars == c.toArray() as BigDecimal[]

        where:
        a | b || c
        [0.0, 0.0, 0.0] | [0.0, 0.0, 0.0] || [0.0, 0.0, 0.0]
        [1.0, 1.0, 1.0] | [1.0, 1.0, 1.0] || [2.0, 2.0, 2.0]
        [1.0, 0.0, 1.0] | [0.0, 1.0, 0.0] || [1.0, 1.0, 1.0]
        [1.0] | [0.0, 1.0] || [1.0, 1.0]
        [1.0, 1.0] | [1.0] || [2.0, 1.0]
    }

    def "Multiply"() {
        expect:
        Vector v = new Vector(a.toArray() as BigDecimal[])
        (v * b).scalars == c.toArray() as BigDecimal[]

        where:
        a | b || c
        [1.0] | 0 || [0.0]
        [1.0] | 1 || [1.0]
        [1.0, 1.0] | 2 || [2.0, 2.0]
    }
}
