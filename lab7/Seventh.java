package lab7;
interface Workable {
    void performWork();
}

class Human {
    protected String name;
    protected int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void showInfo() {
        System.out.println("Людина: " + name + ", Вік: " + age);
    }
}

class Employee extends Human implements Workable {
    private String position;
    private double salary;

    public Employee(String name, int age, String position, double salary) {
        super(name, age);
        this.position = position;
        this.salary = salary;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Посада: " + position + " | Зарплата: ₴" + salary);
    }

    // РЕАЛІЗАЦІЯ методу з інтерфейсу
    @Override
    public void performWork() {
        System.out.println("[Штатний] " + name + " виконує обов'язки на посаді: " + position);
    }
}

abstract class ExternalWorker extends Human implements Workable {
    protected String project;

    public ExternalWorker(String name, int age, String project) {
        super(name, age);
        this.project = project;
    }

    public abstract void reportProgress();
}

class Freelancer extends ExternalWorker {
    private double hourlyRate;
    private int hoursWorked;

    public Freelancer(String name, int age, String project, double hourlyRate, int hoursWorked) {
        super(name, age, project);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public void reportProgress() {
        System.out.println("Звіт: Фрілансер " + name + " відпрацював " + hoursWorked + " годин.");
    }

    @Override
    public void performWork() {
        System.out.println("[Фріланс] " + name + " пише код для проєкту: " + project);
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Статус: Фрілансер | Проєкт: " + project + " | Зароблено: ₴" + (hourlyRate * hoursWorked));
    }
}

public class Seventh {
    public static void main(String[] args) {
        System.out.println("=== 1. Демонстрація звичайного співробітника (Інтерфейс) ===");
        Employee emp = new Employee("Анна", 25, "Розробник", 45000.50);
        emp.showInfo();
        emp.performWork();

        System.out.println("\n=== 2. Демонстрація Фрілансера (Абстрактний клас + Інтерфейс) ===");
        Freelancer freelancer = new Freelancer("Олег", 22, "Розробка сайту ATS", 500.0, 40);
        freelancer.showInfo();
        freelancer.performWork(); 
        freelancer.reportProgress();
    }
}