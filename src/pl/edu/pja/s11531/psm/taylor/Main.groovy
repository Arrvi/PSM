import pl.edu.pja.s11531.psm.taylor.ApproximationTester
import pl.edu.pja.s11531.psm.taylor.CosTaylorSeries

def taylorCosFactory = new CosTaylorSeries()

def tester = new ApproximationTester(10000)

(1..40).each { i ->
    print "Testing Taylor series cos at degree $i: "
    def start = System.currentTimeMillis();
    def averageError = tester.test(Math.&cos, taylorCosFactory.getClosure(i));
    def time = System.currentTimeMillis() - start;
    println "calculation time = $time ms; average error = $averageError"
}