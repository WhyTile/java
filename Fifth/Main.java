import calculations.ExpressionCalculator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        double a = 2.0;
        double c = 1.5;
        int n = 8;
        double startX = -10 - 2.5 * n;       
        double endX = 5 + 1.2 * n;            
        double stepX = 0.5 + (double)n / 20; 
        
        try {
            System.out.print("Введіть значення y (число): ");
            double y = scanner.nextDouble();
            
            System.out.println("\nДіапазон обчислень:");
            System.out.println("Початкове X: " + startX + " | Кінцеве X: " + endX + " | Крок: " + stepX);
            
            System.out.println("\n=== Обчислення за допомогою циклу FOR (Нестатичний метод, Об'єкт 1) ===");
            ExpressionCalculator calc1 = new ExpressionCalculator(a, c, startX, y);
            
            for (double x = startX; x <= endX + 0.0001; x += stepX) {
                calc1.setX(x); 
                double p = calc1.calculate(); 
                
                if (Double.isNaN(p)) {
                    System.out.printf("x = %.2f \t-> Пропущено (помилка ОДЗ: знаменник = 0)\n", x);
                } else {
                    System.out.printf("x = %.2f \t P = %.5f\n", x, p);
                }
            }
            
            System.out.println("\n=== Демонстрація другого об'єкта (Конструктор за замовчуванням) ===");
            ExpressionCalculator calc2 = new ExpressionCalculator();
            System.out.printf("Початкові параметри Об'єкта 2: a=%.1f, c=%.1f, x=%.1f, y=%.1f\n", 
                              calc2.getA(), calc2.getC(), calc2.getX(), calc2.getY());
            
            System.out.println("\n=== Обчислення за допомогою циклу WHILE (Статичний метод класу) ===");
            double currentX = startX;
            while (currentX <= endX + 0.0001) {
                double p = ExpressionCalculator.calculateStatic(a, c, currentX, y);
                
                if (Double.isNaN(p)) {
                    System.out.printf("x = %.2f \t-> Пропущено (помилка ОДЗ)\n", currentX);
                } else {
                    System.out.printf("x = %.2f \t P = %.5f\n", currentX, p);
                }
                currentX += stepX; 
            }
            
        } catch (InputMismatchException e) {
            System.out.println("Виняткова ситуація (InputMismatchException): Некоректний ввід! Очікувалося числове значення.");
        } finally {
            scanner.close();
            System.out.println("\nРесурси сканера закрито.");
        }
    }
}