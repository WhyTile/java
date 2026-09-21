import java.util.Scanner;

public class First {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        double a = 2.0;
        double c = 1.5;
        double y = 0.5;
        
        System.out.print("Введіть значення x: ");
        double x = scanner.nextDouble();
        double top = Math.pow(a, 5) + Math.pow(Math.sin(y - c), 4);
        double bottom = Math.pow(Math.sin(x + y), 3) + Math.abs(x - y);
        double p = top / bottom;
        
        System.out.println("Результат виразу P = " + p);
        
        scanner.close();
    }
}