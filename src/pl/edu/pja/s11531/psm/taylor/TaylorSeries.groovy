package pl.edu.pja.s11531.psm.taylor

interface TaylorSeries {
    BigDecimal calculate(BigDecimal value, int degree);
}