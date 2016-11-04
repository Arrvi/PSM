package pl.edu.pja.s11531.psm.taylor

abstract class TaylorSeries {
    abstract BigDecimal calculate(BigDecimal value, int degree);

    Closure<BigDecimal> getClosure(int degree) {
        return { BigDecimal value -> calculate(value, degree) }
    }
}