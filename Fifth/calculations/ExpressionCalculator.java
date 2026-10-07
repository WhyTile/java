package calculations;

public class ExpressionCalculator {
    private double a;
    private double c;
    private double x;
    private double y;

    public ExpressionCalculator() {
        this.a = 2.0;
        this.c = 1.5;
        this.x = 0.0;
        this.y = 0.5;
    }

    public ExpressionCalculator(double a, double c, double x, double y) {
        this.a = a;
        this.c = c;
        this.x = x;
        this.y = y;
    }

    public double getA() { return a; }
    public void setA(double a) { this.a = a; }

    public double getC() { return c; }
    public void setC(double c) { this.c = c; }

    public double getX() { return x; }
    public void setX(double x) { this.x = x; }

    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public double calculate() {
        double bottom = Math.pow(Math.sin(this.x + this.y), 3) + Math.abs(this.x - this.y);
        
        if (bottom == 0) {
            return Double.NaN;
        }
        
        double top = Math.pow(this.a, 5) + Math.pow(Math.sin(this.y - this.c), 4);
        return top / bottom;
    }

    public static double calculateStatic(double a, double c, double x, double y) {
        double bottom = Math.pow(Math.sin(x + y), 3) + Math.abs(x - y);
        
        if (bottom == 0) {
            return Double.NaN;
        }
        
        double top = Math.pow(a, 5) + Math.pow(Math.sin(y - c), 4);
        return top / bottom;
    }
}