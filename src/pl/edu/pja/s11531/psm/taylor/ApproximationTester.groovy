package pl.edu.pja.s11531.psm.taylor

import org.codehaus.groovy.runtime.typehandling.BigDecimalMath

import static groovyx.gpars.GParsPool.withPool

class ApproximationTester {
    static final def DEFAULT_TEST_AMOUNT = 1000
    static final def POOL_SIZE = 8
    int testsAmount
    def calculationProgress = 0.0

    ApproximationTester() {
        this(DEFAULT_TEST_AMOUNT)
    }

    ApproximationTester(int testsAmount) {
        this.testsAmount = testsAmount
    }

    public BigDecimal test(Closure<BigDecimal> controlOperation, Closure<BigDecimal> testOperation,
                           Closure<BigDecimal> dataGenerator = { Math.random() * 200 - 100 }) {
        calculationProgress = 0.0
        withPool(POOL_SIZE) {
            return (1..testsAmount).collectParallel({
                def argument = dataGenerator()
                def error = controlOperation(argument) - testOperation(argument);
                calculationProgress += 1 / testsAmount;
                return BigDecimalMath.abs(error) / testsAmount;
            }).sumParallel();
        } as BigDecimal
    }
}
