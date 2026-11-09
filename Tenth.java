import java.io.*;
import java.util.ArrayList;

class Human implements Serializable {
    protected String name;
    protected int age;

    public Human() {
        this.name = "Невідомо";
        this.age = 0;
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    public void showInfo() {
        System.out.println("Людина: " + name + ", Вік: " + age);
    }
}

class Employee extends Human implements Serializable {
    private String position;
    private double salary;

    public Employee() {
        super();
        this.position = "Стажер";
        this.salary = 0.0;
    }

    public Employee(String name, int age, String position, double salary) {
        super(name, age); 
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() { return position; }
    public double getSalary() { return salary; }
    public void setPosition(String position) { this.position = position; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public void showInfo() {
        System.out.println("Співробітник: " + name + " | Вік: " + age + " | Посада: " + position + " | Зарплата: ₴" + salary);
    }
}

class Company {
    private String companyName;
    private Employee[] employees; 

    public Company(String companyName, Employee[] employees) {
        this.companyName = companyName;
        this.employees = employees;
    }

    public void showAllEmployees() {
        System.out.println("\n=== Компанія: " + companyName + " ===");
        for (int i = 0; i < employees.length; i++) {
            System.out.print("[" + i + "] ");
            employees[i].showInfo(); 
        }
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void sortByNameAsc() {
        for (int i = 0; i < employees.length - 1; i++) {
            for (int j = 0; j < employees.length - 1 - i; j++) {
                if (employees[j].getName().compareTo(employees[j + 1].getName()) > 0) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                }
            }
        }
        System.out.println("\n[!] Масив відсортовано за ЗРОСТАННЯМ імені.");
    }
}

public class Tenth {
    
    public static Employee[] readFromTextFile(String filename) {
        ArrayList<Employee> empList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String name = data[0].trim();
                    int age = Integer.parseInt(data[1].trim());
                    String position = data[2].trim();
                    double salary = Double.parseDouble(data[3].trim());
                    empList.add(new Employee(name, age, position, salary));
                }
            }
            System.out.println("[+] Дані успішно зчитано з текстового файлу " + filename);
        } catch (IOException | NumberFormatException e) {
            System.out.println("[-] Помилка читання текстового файлу: " + e.getMessage());
        }
        return empList.toArray(new Employee[0]);
    }

    public static void writeToBinaryFile(Employee[] employees, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(employees);
            System.out.println("[+] Відсортований масив успішно записано у нетекстовий файл " + filename);
        } catch (IOException e) {
            System.out.println("[-] Помилка запису у нетекстовий файл: " + e.getMessage());
        }
    }

    public static Employee[] readFromBinaryFile(String filename) {
        Employee[] employees = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            employees = (Employee[]) ois.readObject();
            System.out.println("[+] Дані успішно зчитано з нетекстового файлу " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[-] Помилка читання нетекстового файлу: " + e.getMessage());
        }
        return employees;
    }

    public static void main(String[] args) {
        String textFile = "employees.txt";
        String binaryFile = "employees.dat";

        Employee[] loadedStaff = readFromTextFile(textFile);
        
        if (loadedStaff.length > 0) {
            Company techCompany = new Company("Tech Solutions", loadedStaff);
            techCompany.showAllEmployees();

            techCompany.sortByNameAsc();
            writeToBinaryFile(techCompany.getEmployees(), binaryFile);

            System.out.println("\n--- Відновлення даних з нетекстового файлу ---");
            Employee[] binaryStaff = readFromBinaryFile(binaryFile);
            
            if (binaryStaff != null) {
                Company restoredCompany = new Company("Restored Tech Solutions", binaryStaff);
                restoredCompany.showAllEmployees();
            }
        } else {
            System.out.println("Не вдалося завантажити працівників. Перевірте наявність файлу " + textFile);
        }
    }
}