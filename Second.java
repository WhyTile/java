import java.util.Scanner;

public class Second {
    public static void main(String[] args) {
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);
        
        double a = 2.0;
        double c = 1.5;
        double y = 0.5;
        
        System.out.print("Введіть значення x: ");
        double x = scanner.nextDouble();
        
        double top = Math.pow(a, 5) + Math.pow(Math.sin(y - c), 4);
        double bottom = Math.pow(Math.sin(x + y), 3) + Math.abs(x - y);
        
        if (bottom == 0) {
            System.out.println("Помилка ОДЗ: знаменник дорівнює нулю, ділення на нуль неможливе");
        } else {
            double p = top / bottom;
            System.out.println("Результат виразу P = " + p);
        }
        
        scanner.close();
    }
}