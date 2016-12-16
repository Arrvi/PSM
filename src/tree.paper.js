var START_POINT = new Point(300, 700);
var INITIAL_LENGTH = 150;
var LENGTH_MULT = 0.60;
var SPLIT = 4;
var SPREAD = 120;
var STEPS = 7;

var RANDOM_LENGTH = .2; // .3
var RANDOM_SPREAD = .1; // .2
var RANDOM_SPLIT = .2; // .2
var RANDOM_ANGLE = .3; // .5

function randomize(value, factor) {
    return value + 2 * value * factor * (Math.random() - .5);
}

function color(value) {
    var start = new Color(100, 100, 0);
    var end = new Color(30, 240, 0);
    return start * (1 - value) + end * value;
}

function treeStep(start, length, angle, remSteps) {
    if (remSteps <= 0) return;

    var end = start - new Point(0, length);
    end = end.rotate(angle, start);

    var path = new Path.Line(start, end);
    path.strokeColor = new Color(1 - remSteps / STEPS);

    var split = Math.round(randomize(SPLIT, RANDOM_SPLIT));
    var spread = randomize(SPREAD, RANDOM_SPREAD);

    for (var a = -spread / 2; a <= spread / 2; a += spread / (split - 1)) {
        var len = randomize(length * LENGTH_MULT, RANDOM_LENGTH);
        var ang = randomize(a, RANDOM_ANGLE);
        treeStep(end, len, angle + ang, remSteps - 1);
    }
}

treeStep(START_POINT, INITIAL_LENGTH, 0, STEPS);