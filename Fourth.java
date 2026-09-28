import java.util.InputMismatchException;
import java.util.Scanner;

public class Fourth {
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
            
            System.out.println("\n=== Обчислення за допомогою циклу FOR ===");
            for (double x = startX; x <= endX + 0.0001; x += stepX) {
                double bottom = Math.pow(Math.sin(x + y), 3) + Math.abs(x - y);
                
                if (bottom == 0) {
                    System.out.printf("x = %.2f \t-> Пропущено (помилка ОДЗ: знаменник = 0)\n", x);
                    continue; 
                }
                
                double top = Math.pow(a, 5) + Math.pow(Math.sin(y - c), 4);
                double p = top / bottom;
                System.out.printf("x = %.2f \t P = %.5f\n", x, p);
            }
            
            System.out.println("\n=== Обчислення за допомогою циклу WHILE ===");
            double currentX = startX;
            while (currentX <= endX + 0.0001) {
                double bottom = Math.pow(Math.sin(currentX + y), 3) + Math.abs(currentX - y);
                
                if (bottom == 0) {
                    System.out.printf("x = %.2f \t-> Пропущено (помилка ОДЗ)\n", currentX);
                    currentX += stepX; 
                    continue;
                }
                
                double top = Math.pow(a, 5) + Math.pow(Math.sin(y - c), 4);
                double p = top / bottom;
                System.out.printf("x = %.2f \t P = %.5f\n", currentX, p);
                
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