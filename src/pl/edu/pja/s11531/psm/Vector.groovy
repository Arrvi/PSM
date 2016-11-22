package pl.edu.pja.s11531.psm

/**
 * Created by s11531 on 2016-11-15.
 */
class Vector {
    final BigDecimal[] scalars;

    Vector(int length) {
        scalars = new BigDecimal[length];
    }

    Vector(Vector vector) {
        scalars = vector.scalars.clone()
    }

    Vector(BigDecimal[] scalars) {
        this.scalars = scalars.clone()
    }

    Vector(List<BigDecimal> scalars) {
        this(scalars.toArray() as BigDecimal[])
    }

    int getSize() {
        return scalars.length;
    }

    Vector plus(Vector vector) {
        if ( size < vector.size ) return vector + this;
        Vector sum = new Vector(size)
        for (def i in 0..(size-1)) {
            sum.scalars[i] = i < vector.size ? vector.scalars[i] + this.scalars[i] : this.scalars[i]
        }
        return sum;
    }

    Vector multiply(BigDecimal multiplier) {
        return new Vector(scalars.collect { it * multiplier }.toArray() as BigDecimal[])
    }

    BigDecimal getAt(int index) {
        scalars[index] ?: 0.0
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

    @Override
    String toString() {
        return "[${scalars.join(', ')}]"
    }
}
