package pl.edu.pja.s11531.psm.taylor

class SinTaylorSeries extends TaylorSeries {
    @Override
    BigDecimal calculate(BigDecimal value, int degree) {
        value %= Math.PI * 2
        def sum = value
        def currentPower = value
        def currentFactorial = 1G
        for (int i in 1..<degree) {
            currentPower *= value * value
            currentFactorial *= 4 * i * i + 2 * i
            sum += (i % 2 ? -1 : 1) * currentPower / currentFactorial
        }
        return sum
    }
}
