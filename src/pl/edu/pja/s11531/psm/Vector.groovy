package pl.edu.pja.s11531.psm

import java.math.RoundingMode

/**
 * Created by s11531 on 2016-11-15.
 */
class Vector {
    final BigDecimal[] scalars;
    static int maxScale = 20;

    Vector(int length) {
        scalars = new BigDecimal[length];
    }

    Vector(Vector vector) {
        scalars = vector.scalars.clone()
        ensureScale()
    }

    Vector(BigDecimal[] scalars) {
        this.scalars = scalars.clone()
        ensureScale()
    }

    Vector(List<BigDecimal> scalars) {
        this(scalars.toArray() as BigDecimal[])
    }

    int getSize() {
        return scalars.length;
    }

    Vector plus(Vector vector) {
        if (size < vector.size) return vector + this;
        Vector sum = new Vector(size)
        for (def i in 0..(size - 1)) {
            sum[i] = i < vector.size ? vector[i] + this[i] : this[i]
        }
        return sum;
    }

    Vector minus(Vector vector) {
        return this + (vector * -1)
    }

    Vector multiply(BigDecimal multiplier) {
        return new Vector(scalars.collect { it * multiplier }.toArray() as BigDecimal[])
    }

    Vector divide(BigDecimal divider) {
        return this * (1 / divider)
    }

    BigDecimal getAt(int index) {
        scalars[index] ?: 0.0
    }

    void putAt(int index, BigDecimal value) {
        scalars[index] = value
        ensureScale()
    }

    BigDecimal getX() {
        this[0]
    }

    BigDecimal getY() {
        this[1]
    }

    BigDecimal getZ() {
        this[2]
    }

    BigDecimal getLength() {
        Math.sqrt(scalars*.pow(2).sum())
    }

    BigDecimal getAngle() {

    }

    private void ensureScale() {
        for ( int i = 0; i < size; i++ ) {
            if (scalars[i] == null) scalars[i] = 0.0
            if (scalars[i].scale > maxScale) {
                scalars[i] = scalars[i].setScale(maxScale, RoundingMode.HALF_UP);
            }
        }
    }

    @Override
    String toString() {
        return "[${scalars.join(', ')}]"
    }
}
