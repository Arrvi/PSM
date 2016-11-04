

import pl.edu.pja.s11531.psm.taylor.*
def sin = new SinTaylorSeries().getClosure(25);
def cos = new CosTaylorSeries().getClosure(25);

def radius = 10.0
def gravity = -10.0
def timeStep = 0.1
def angle = -1.0
def angularVelocity = 0.0
def steps = 300;
def time = 0.0;


def round = { it.setScale(10, java.math.RoundingMode.HALF_UP)}

def ln = System.getProperty('line.separator')
File csv = new File("../pendulum.csv")
if (csv.exists()) csv.delete()
csv << "time;angle;angular velocity;angular acceleration$ln"

for (def i in (1..steps)) {
	def angularAcceleration = gravity / radius * sin(angle)
	def angVelHalfStep = angularVelocity + angularAcceleration * timeStep / 2
	def angleStep = angle + angVelHalfStep * timeStep
	def angleHalfStep = angle + angularVelocity * timeStep / 2
	def angAccHalfStep = gravity / radius * sin(angleHalfStep)
	def angVelStep = angularVelocity + angAccHalfStep * timeStep / 2

	angle = angleStep
	angularVelocity = angVelStep
	time += timeStep

	println "Step $i (time=${round(time)}) angle=${round(angle)} aVelocity=${round(angularVelocity)} aAcceleration=${round(angularAcceleration)}"
	csv << "${round(time)};${round(angle)};${round(angularVelocity)};${round(angularAcceleration)}$ln"
}