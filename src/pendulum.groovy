

import pl.edu.pja.s11531.psm.taylor.*
// def sin = new SinTaylorSeries().getClosure(25);
// def cos = new CosTaylorSeries().getClosure(25);
def sin = Math.&sin


def radius = 10.0
def gravity = -10.0
def timeStep = 0.05
def angle = -1.0
def angularVelocity = 0.0
def steps = 1000;
def time = 0.0;


def round = { BigDecimal x -> x.setScale(10, java.math.RoundingMode.HALF_UP)}

def ln = System.getProperty('line.separator')
File csv = new File("../pendulum.csv")
if (csv.exists()) csv.delete()
csv << "time;angle;angular velocity;angular acceleration$ln"

def direction = 1
def swings = 0

while (swings < 5) {
	def angularAcceleration = gravity / radius * sin(angle)
	def angVelHalfStep = angularVelocity + angularAcceleration * timeStep / 2.0
	def angleHalfStep = angle + angularVelocity * timeStep / 2.0
	def angAccHalfStep = gravity / radius * sin(angleHalfStep)
	angle = angle + angVelHalfStep * timeStep
	angularVelocity = angularVelocity + angAccHalfStep * timeStep

	time += timeStep

	if ( direction < 0 && angVelHalfStep > 0 ) swings++
	direction = angVelHalfStep

	println "time=${round(time)} angle=${round(angle)} aVelocity=${round(angularVelocity)} aAcceleration=${round(angularAcceleration)} swings=$swings"
	csv << "${round(time)};${round(angle)};${round(angularVelocity)};${round(angularAcceleration)}$ln"
}