class Human {
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

    public void showInfo() {
        System.out.println("Людина: " + name + ", Вік: " + age);
    }

    public void speak() {
        System.out.println(name + " каже: Привіт!");
    }

    public void speak(String phrase) {
        System.out.println(name + " каже: " + phrase);
    }
}

class Employee extends Human {
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

    @Override
    public void showInfo() {
        super.showInfo(); 
        System.out.println("Посада: " + position + " | Зарплата: ₴" + salary);
    }
}

class Company {
    private String companyName;
    private Employee[] employees; 

    public Company() {
        this.companyName = "Невідома компанія";
        this.employees = new Employee[0];
    }

    public Company(String companyName, Employee[] employees) {
        this.companyName = companyName;
        this.employees = employees;
    }

    public void showCompanyInfo() {
        System.out.println("\n=== Компанія: " + companyName + " ===");
        System.out.println("Штат співробітників:");
        for (Employee emp : employees) {
            emp.showInfo(); 
            System.out.println("-------------------------");
        }
    }
}

public class Sixth {
    public static void main(String[] args) {
        System.out.println("--- Демонстрація базового класу та перевантаження ---");
        Human human1 = new Human("Єгор", 20);
        human1.showInfo();
        human1.speak();                
        human1.speak("Я вивчаю ООП."); 

        System.out.println("\n--- Демонстрація спадкування та перевизначення ---");
        Employee emp1 = new Employee("Анна", 25, "Розробник", 45000.50);
        Employee emp2 = new Employee(); 
        
        emp1.showInfo(); 
        emp2.showInfo();

        System.out.println("\n--- Демонстрація агрегації ---");
        Employee[] itTeam = {emp1, new Employee("Олег", 30, "Менеджер", 50000.00)};
        
        Company techCompany = new Company("Tech Solutions", itTeam);
        techCompany.showCompanyInfo();
    }
}