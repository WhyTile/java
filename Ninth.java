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

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void showInfo() {
        System.out.println("Людина: " + name + ", Вік: " + age);
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

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

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
        System.out.println("\n=== Компанія: " + companyName + " (Усі співробітники) ===");
        for (int i = 0; i < employees.length; i++) {
            System.out.print("[" + i + "] ");
            employees[i].showInfo(); 
        }
    }

    public void modifyEmployee(int index, String newPosition, double newSalary) {
        if (index >= 0 && index < employees.length) {
            employees[index].setPosition(newPosition);
            employees[index].setSalary(newSalary);
            System.out.println("\n[!] Дані співробітника за індексом " + index + " успішно змінено.");
        } else {
            System.out.println("\n[!] Помилка: Невірний індекс співробітника.");
        }
    }

    public void filterByAge(int minAge) {
        System.out.println("\n=== Співробітники старші за " + minAge + " років ===");
        boolean found = false;
        for (Employee emp : employees) {
            if (emp.getAge() > minAge) {
                emp.showInfo();
                found = true;
            }
        }
        if (!found) System.out.println("Таких співробітників не знайдено.");
    }

    public void sortByAgeAsc() {
        for (int i = 0; i < employees.length - 1; i++) {
            for (int j = 0; j < employees.length - 1 - i; j++) {
                if (employees[j].getAge() > employees[j + 1].getAge()) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                }
            }
        }
        System.out.println("\n[!] Масив відсортовано за ЗРОСТАННЯМ віку.");
    }

    public void sortByAgeDesc() {
        for (int i = 0; i < employees.length - 1; i++) {
            for (int j = 0; j < employees.length - 1 - i; j++) {
                if (employees[j].getAge() < employees[j + 1].getAge()) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                }
            }
        }
        System.out.println("\n[!] Масив відсортовано за СПАДАННЯМ віку.");
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
        System.out.println("\n[!] Масив відсортовано за ЗРОСТАННЯМ імені (алфавітний порядок).");
    }

    public void sortByNameDesc() {
        for (int i = 0; i < employees.length - 1; i++) {
            for (int j = 0; j < employees.length - 1 - i; j++) {
                if (employees[j].getName().compareTo(employees[j + 1].getName()) < 0) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                }
            }
        }
        System.out.println("\n[!] Масив відсортовано за СПАДАННЯМ імені (зворотний алфавітний порядок).");
    }

    public void filterByNamePattern(String suffix) {
        System.out.println("\n=== Співробітники, чиє ім'я закінчується на \"" + suffix + "\" ===");
        boolean found = false;
        for (Employee emp : employees) {
            if (emp.getName().toLowerCase().endsWith(suffix.toLowerCase())) {
                emp.showInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Співробітників за заданим критерієм не знайдено.");
        }
    }

    public void showMinMaxSalary() {
        if (employees.length == 0) return;

        Employee minEmp = employees[0];
        Employee maxEmp = employees[0];

        for (int i = 1; i < employees.length; i++) {
            if (employees[i].getSalary() < minEmp.getSalary()) {
                minEmp = employees[i];
            }
            if (employees[i].getSalary() > maxEmp.getSalary()) {
                maxEmp = employees[i];
            }
        }

        System.out.println("\n=== Аналіз заробітних плат ===");
        System.out.println("Найменша зарплата ₴" + minEmp.getSalary() + " у співробітника " + minEmp.getName() + " (" + minEmp.getPosition() + ").");
        System.out.println("Найбільша зарплата ₴" + maxEmp.getSalary() + " у співробітника " + maxEmp.getName() + " (" + maxEmp.getPosition() + ").");
    }
}

public class Ninth {
    public static void main(String[] args) {
        Employee[] staff = {
            new Employee("Єгор", 20, "Junior Developer", 25000),
            new Employee("Анна", 25, "Middle Developer", 45000),
            new Employee("Олег", 35, "Project Manager", 65000),
            new Employee("Марія", 22, "QA Engineer", 28000),
            new Employee("Іван", 40, "Senior Developer", 85000),
            new Employee("Олена", 29, "UI/UX Designer", 40000),
            new Employee("Дмитро", 31, "DevOps Engineer", 70000),
            new Employee("Вікторія", 26, "HR Manager", 35000),
            new Employee("Максим", 21, "Intern", 15000),
            new Employee("Софія", 33, "Business Analyst", 55000)
        };
        
        Company techCompany = new Company("Tech Solutions", staff);

        techCompany.sortByNameAsc();
        techCompany.showAllEmployees();

        techCompany.sortByNameDesc();
        techCompany.showAllEmployees();

        techCompany.filterByNamePattern("а");
        techCompany.filterByNamePattern("я");

        techCompany.showMinMaxSalary();
    }
}