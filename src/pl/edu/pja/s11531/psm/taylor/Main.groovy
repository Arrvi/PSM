import pl.edu.pja.s11531.psm.taylor.ApproximationTester
import pl.edu.pja.s11531.psm.taylor.CosTaylorSeries
import pl.edu.pja.s11531.psm.taylor.SinTaylorSeries

def taylorCosFactory = new CosTaylorSeries()
def taylorSinFactory = new SinTaylorSeries()

def testsAmount = 10000
def tester = new ApproximationTester(testsAmount)
def start, averageError, time

(1..25).each { i ->
    print "Testing Taylor series sin at degree $i: "
    start = System.currentTimeMillis();
    averageError = tester.test(Math.&sin, taylorSinFactory.getClosure(i));
    time = System.currentTimeMillis() - start;
    println "calculation time = ${time / testsAmount} ms/op; average error = ${averageError.toPlainString()}"

    print "Testing Taylor series cos at degree $i: "
    start = System.currentTimeMillis();
    averageError = tester.test(Math.&cos, taylorCosFactory.getClosure(i));
    time = System.currentTimeMillis() - start;
    println "calculation time = ${time / testsAmount} ms/op; average error = ${averageError.toPlainString()}\n"
}
