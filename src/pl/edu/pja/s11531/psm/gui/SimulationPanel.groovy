package pl.edu.pja.s11531.psm.gui

import pl.edu.pja.s11531.psm.Vector
import sun.java2d.SunGraphics2D

import javax.swing.JPanel
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.math.RoundingMode

/**
 * Created by s11531 on 2016-11-25.
 */
abstract class SimulationPanel extends JPanel {
    BigDecimal maxY = 20
    BigDecimal offsetY = 10
    BigDecimal offsetX = 0
    BigDecimal markerHeight = 0.1

    abstract void paintSimulation(SimulationGraphics sg)

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g)
        SimulationGraphics sg = new SimulationGraphics(g as Graphics2D);

        sg.color = Color.RED
        if (offsetY) {
            sg.drawLine new Vector(0.0, 0.0), new Vector(maxX, 0.0)
            for ( int i = 1; i<maxY; i++ ) {
                sg.drawLine new Vector(-markerHeight, i), new Vector(markerHeight, i)
            }
        }
        if (offsetX) {
            sg.drawLine new Vector(0.0, 0.0), new Vector(0.0, maxY)
            for ( int i = 1; i<maxX; i++ ) {
                sg.drawLine new Vector(i, -markerHeight), new Vector(i, markerHeight)
            }
        }
        sg.color = Color.BLACK
        paintSimulation(sg)
    }


    public BigDecimal getMaxX() {
        BigDecimal ratio = (BigDecimal) width / height
        return maxY * ratio;
    }

    class SimulationGraphics {
        @Delegate
        private Graphics2D graphics

        SimulationGraphics(Graphics2D graphics) {
            graphics.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            this.graphics = graphics
        }

        void drawLine(Vector pointA, Vector pointB) {
            int x1, y1, x2, y2
            (x1, y1) = transformPoint(pointA)
            (x2, y2) = transformPoint(pointB)

            graphics.drawLine x1, y1, x2, y2
        }

        void drawArc(Vector bottomLeft, int width, int height, int startAngle, int arcAngle) {
            int x, y
            (x, y) = transformPoint(bottomLeft)

            graphics.drawArc x, y, width, height, startAngle, arcAngle
        }

        void drawArcAround(Vector center, int width, int height, int startAngle, int arcAngle) {
            int x, y
            (x, y) = transformPoint(center)
            x -= width / 2
            y -= height / 2

            graphics.drawArc x, y, width, height, startAngle, arcAngle
        }

        void drawEllipse(Vector bottomLeft, int width, int height) {
            drawArc bottomLeft, width, height, 0, 360
        }

        void drawCircle(Vector bottomLeft, int diameter) {
            drawEllipse bottomLeft, diameter, diameter
        }

        void drawEllipseAround(Vector center, int width, int height) {
            drawArcAround center, width, height, 0, 360
        }

        void drawCircleAround(Vector center, int diameter) {
            drawEllipseAround center, diameter, diameter
        }

        public List<Integer> transformPoint(Vector point) {
            if (point == null) {
                return [0, 0]
            }
            return [width * (point.x + offsetX) / maxX, height - height * (point.y + offsetY) / maxY]    \
                    .collect { it.setScale(0, RoundingMode.HALF_UP) }    \
                    *.intValue()
        }

        public int transformLength(BigDecimal len) {
            width * len / maxX
        }
    }
}
