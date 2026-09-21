import java.util.InputMismatchException;
import java.util.Scanner;

public class Third {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        double a = 2.0;
        double c = 1.5;
        double y = 0.5;
        
        try {
            System.out.print("Введіть значення x (число): ");
            double x = scanner.nextDouble();
            
            double top = Math.pow(a, 5) + Math.pow(Math.sin(y - c), 4);
            double bottom = Math.pow(Math.sin(x + y), 3) + Math.abs(x - y);
            
            if (bottom == 0) {
                System.out.println("Помилка ОДЗ: знаменник дорівнює нулю, ділення на нуль неможливе");
            } else {
                double p = top / bottom;
                System.out.println("Результат виразу P = " + p);
            }
        } catch (InputMismatchException e) {
            System.out.println("Виняткова ситуація (InputMismatchException): Некоректний ввід! Очікувалося числове значення.");
        } finally {
            scanner.close();
            System.out.println("Ресурси сканера закрито (блок finally виконано).");
        }
    }
}