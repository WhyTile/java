import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Базовий клас, що представляє людину (з ЛР9 та ЛР10).
 * Реалізує інтерфейс {@link Serializable} для можливості запису у бінарний файл.
 *
 * @author Студент
 * @version 1.0
 */
class Human implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ПІБ або ім'я людини */
    protected String name;

    /** Вік людини у роках */
    protected int age;

    /**
     * Конструктор за замовчуванням.
     */
    public Human() {
        this.name = "Невідомо";
        this.age = 18;
    }

    /**
     * Конструктор з параметрами для створення об'єкта людини.
     *
     * @param name ім'я або ПІБ людини
     * @param age  вік людини
     */
    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Повертає ім'я (ПІБ) людини.
     *
     * @return ім'я людини
     */
    public String getName() {
        return name;
    }

    /**
     * Повертає вік людини.
     *
     * @return вік людини
     */
    public int getAge() {
        return age;
    }

    /**
     * Виводить базову інформацію про людину в консоль.
     */
    public void showInfo() {
        System.out.println("Людина: " + name + ", Вік: " + age);
    }
}

/**
 * Клас співробітника, що успадковує клас {@link Human} (об'єднання ЛР9, ЛР10, ЛР11).
 * Додає поля ідентифікатора БД (id), посади (position) та заробітної плати (salary).
 *
 * @author Студент
 * @version 1.0
 */
class Employee extends Human implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Унікальний ідентифікатор співробітника для бази даних SQLite */
    private int id;

    /** Посада співробітника в компанії */
    private String position;

    /** Заробітна плата співробітника */
    private double salary;

    /**
     * Конструктор за замовчуванням.
     */
    public Employee() {
        super();
        this.id = 0;
        this.position = "Стажер";
        this.salary = 0.0;
    }

    /**
     * Повний конструктор співробітника (для роботи з БД SQLite у ЛР11).
     *
     * @param id       унікальний ідентифікатор запису в БД
     * @param name     ПІБ співробітника
     * @param age      вік співробітника
     * @param position посада співробітника
     * @param salary   заробітна плата
     */
    public Employee(int id, String name, int age, String position, double salary) {
        super(name, age);
        this.id = id;
        this.position = position;
        this.salary = salary;
    }

    /**
     * Конструктор співробітника без ID (для створення нових записів, ЛР9 та ЛР10).
     *
     * @param name     ПІБ співробітника
     * @param age      вік співробітника
     * @param position посада співробітника
     * @param salary   заробітна плата
     */
    public Employee(String name, int age, String position, double salary) {
        this(0, name, age, position, salary);
    }

    /**
     * Повертає ідентифікатор співробітника.
     *
     * @return ID співробітника
     */
    public int getId() {
        return id;
    }

    /**
     * Повертає посаду співробітника.
     *
     * @return назва посади
     */
    public String getPosition() {
        return position;
    }

    /**
     * Повертає заробітну плату співробітника.
     *
     * @return сума зарплати
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Встановлює нову посаду співробітника.
     *
     * @param position нова назва посади
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Встановлює нову заробітну плату співробітника.
     *
     * @param salary нова сума зарплати
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Виводить детальну інформацію про співробітника у консоль.
     */
    @Override
    public void showInfo() {
        System.out.println("ID: " + id + " | Співробітник: " + name + " | Вік: " + age +
                " | Посада: " + position + " | Зарплата: ₴" + salary);
    }
}

/**
 * Клас для управління масивом/списком співробітників компанії (методи з ЛР9).
 * Забезпечує сортування, фільтрацію та аналіз заробітної плати.
 *
 * @author Студент
 * @version 1.0
 */
class Company {
    /** Назва компанії */
    private String companyName;

    /** Масив співробітників компанії */
    private Employee[] employees;

    /**
     * Конструктор створення компанії.
     *
     * @param companyName назва компанії
     * @param employees   масив співробітників
     */
    public Company(String companyName, Employee[] employees) {
        this.companyName = companyName;
        this.employees = employees;
    }

    /**
     * Повертає поточний масив співробітників компанії.
     *
     * @return масив об'єктів {@link Employee}
     */
    public Employee[] getEmployees() {
        return employees;
    }

    /**
     * Сортує масив співробітників за зростанням віку (метод бульбашки з ЛР9).
     */
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
    }

    /**
     * Сортує масив співробітників за спаданням віку (метод бульбашки з ЛР9).
     */
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
    }

    /**
     * Сортує масив співробітників за алфавітним порядком ПІБ (метод з ЛР9 та ЛР10).
     */
    public void sortByNameAsc() {
        for (int i = 0; i < employees.length - 1; i++) {
            for (int j = 0; j < employees.length - 1 - i; j++) {
                if (employees[j].getName().compareToIgnoreCase(employees[j + 1].getName()) > 0) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Сортує масив співробітників у зворотному алфавітному порядку ПІБ (метод з ЛР9).
     */
    public void sortByNameDesc() {
        for (int i = 0; i < employees.length - 1; i++) {
            for (int j = 0; j < employees.length - 1 - i; j++) {
                if (employees[j].getName().compareToIgnoreCase(employees[j + 1].getName()) < 0) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Фільтрує співробітників, які старші за вказаний вік (метод з ЛР9).
     *
     * @param minAge мінімальний поріг віку
     * @return список відфільтрованих співробітників
     */
    public List<Employee> filterByAge(int minAge) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getAge() > minAge) {
                result.add(emp);
            }
        }
        return result;
    }

    /**
     * Фільтрує співробітників, чиє ПІБ закінчується на заданий суфікс (метод з ЛР9 та ЛР11).
     *
     * @param suffix рядок закінчення ПІБ
     * @return список співробітників, що відповідають критерію
     */
    public List<Employee> filterByNamePattern(String suffix) {
        List<Employee> result = new ArrayList<>();
        String cleanSuffix = suffix.trim().toLowerCase();
        for (Employee emp : employees) {
            if (emp.getName().toLowerCase().endsWith(cleanSuffix)) {
                result.add(emp);
            }
        }
        return result;
    }

    /**
     * Формує текстовий звіт про співробітників з найменшою та найбільшою зарплатою (метод з ЛР9).
     *
     * @return рядок з результатами аналізу зарплат
     */
    public String getMinMaxSalaryAnalysis() {
        if (employees.length == 0) {
            return "Список співробітників порожній.";
        }
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

        return String.format(
                "Компанія: %s\n\n" +
                "Найменша зарплата: %.2f ₴\n -> %s (%s, %d р.)\n\n" +
                "Найбільша зарплата: %.2f ₴\n -> %s (%s, %d р.)",
                companyName,
                minEmp.getSalary(), minEmp.getName(), minEmp.getPosition(), minEmp.getAge(),
                maxEmp.getSalary(), maxEmp.getName(), maxEmp.getPosition(), maxEmp.getAge()
        );
    }
}

/**
 * Утилітний клас для роботи з текстовими та бінарними файлами (методи з ЛР10).
 *
 * @author Студент
 * @version 1.0
 */
class FileStorageManager {

    /**
     * Зчитує масив співробітників із текстового CSV-файлу (формат: ПІБ,Вік,Посада,Зарплата).
     *
     * @param filename шлях до текстового файлу
     * @return масив зчитаних об'єктів {@link Employee}
     * @throws IOException якщо виникла помилка читання файлу
     */
    public static Employee[] readFromTextFile(String filename) throws IOException {
        ArrayList<Employee> empList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int fakeId = 1;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String name = data[0].trim();
                    int age = Integer.parseInt(data[1].trim());
                    String position = data[2].trim();
                    double salary = Double.parseDouble(data[3].trim());
                    empList.add(new Employee(fakeId++, name, age, position, salary));
                }
            }
        }
        return empList.toArray(new Employee[0]);
    }

    /**
     * Записує поточний список співробітників у текстовий файл.
     *
     * @param employees список співробітників для збереження
     * @param filename  ім'я цільового текстового файлу
     * @throws IOException якщо виникла помилка запису
     */
    public static void writeToTextFile(List<Employee> employees, String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Employee emp : employees) {
                pw.println(emp.getName() + "," + emp.getAge() + "," + emp.getPosition() + "," + emp.getSalary());
            }
        }
    }

    /**
     * Серіалізує масив співробітників у нетекстовий (бінарний) файл (метод з ЛР10).
     *
     * @param employees масив співробітників для запису
     * @param filename  ім'я бінарного файлу (.dat)
     * @throws IOException якщо виникла помилка вводу/виводу
     */
    public static void writeToBinaryFile(Employee[] employees, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(employees);
        }
    }

    /**
     * Десеріалізує масив співробітників із нетекстового (бінарного) файлу (метод з ЛР10).
     *
     * @param filename ім'я бінарного файлу (.dat)
     * @return відновлений масив {@link Employee}
     * @throws IOException            якщо файл не знайдено або пошкоджено
     * @throws ClassNotFoundException якщо клас об'єкта не знайдено
     */
    public static Employee[] readFromBinaryFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Employee[]) ois.readObject();
        }
    }
}

/**
 * Клас для управління локальною реляційною базою даних SQLite (методи з ЛР11).
 * Виконує ініціалізацію схеми та CRUD-операції через JDBC.
 *
 * @author Студент
 * @version 1.0
 */
class LocalDatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:company_local.db";

    /**
     * Ініціалізує таблицю Employees у БД SQLite та заповнює її початковими даними, якщо вона порожня.
     */
    public void initSchemaAndSeed() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS Employees (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL CHECK(length(trim(name)) > 0), " +
                "age INTEGER NOT NULL CHECK(age >= 18 AND age <= 100), " +
                "position TEXT NOT NULL CHECK(length(trim(position)) > 0), " +
                "salary REAL NOT NULL CHECK(salary >= 0)" +
                ");";

        boolean isEmpty = false;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSql);

            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Employees")) {
                if (rs.next() && rs.getInt(1) == 0) {
                    isEmpty = true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Помилка ініціалізації БД: " + e.getMessage(),
                    "Помилка БД", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isEmpty) {
            try {
                addEmployee(new Employee("Єгор Київський", 20, "Junior Developer", 25000));
                addEmployee(new Employee("Анна Коваленко", 25, "Middle Developer", 45000));
                addEmployee(new Employee("Олег Петренко", 35, "Project Manager", 65000));
                addEmployee(new Employee("Марія Іваненко", 22, "QA Engineer", 28000));
                addEmployee(new Employee("Іван Сидоренко", 40, "Senior Developer", 85000));
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Помилка заповнення початкових даних: " + e.getMessage(),
                        "Помилка БД", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Отримує список усіх співробітників із бази даних SQLite.
     *
     * @return список об'єктів {@link Employee}
     */
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT id, name, age, position, salary FROM Employees ORDER BY id";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("position"),
                        rs.getDouble("salary")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Помилка читання даних: " + e.getMessage(),
                    "Помилка", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    /**
     * Додає нового співробітника до бази даних SQLite.
     *
     * @param emp об'єкт співробітника без ID
     * @throws SQLException якщо порушено обмеження цілісності таблиці (CHECK constraint)
     */
    public void addEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO Employees (name, age, position, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, emp.getName().trim());
            pstmt.setInt(2, emp.getAge());
            pstmt.setString(3, emp.getPosition().trim());
            pstmt.setDouble(4, emp.getSalary());
            pstmt.executeUpdate();
        }
    }

    /**
     * Оновлює дані існуючого співробітника в базі даних SQLite за його ID.
     *
     * @param emp об'єкт співробітника з оновленими даними та існуючим ID
     * @throws SQLException якщо виникла помилка SQL або порушено обмеження CHECK
     */
    public void updateEmployee(Employee emp) throws SQLException {
        String sql = "UPDATE Employees SET name = ?, age = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, emp.getName().trim());
            pstmt.setInt(2, emp.getAge());
            pstmt.setString(3, emp.getPosition().trim());
            pstmt.setDouble(4, emp.getSalary());
            pstmt.setInt(5, emp.getId());
            pstmt.executeUpdate();
        }
    }

    /**
     * Видаляє запис співробітника з бази даних SQLite за його ідентифікатором.
     *
     * @param id унікальний ідентифікатор співробітника
     * @throws SQLException якщо виникла помилка видалення
     */
    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM Employees WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}

/**
 * Головний клас графічного застосунку (СР2. Проєкт 2), що об'єднує функціонал ЛР9, ЛР10 та ЛР11.
 * Надає інтерфейс користувача Swing для керування базою даних, файлами та аналітики.
 *
 * @author Студент
 * @version 1.0
 */
public class Project2 extends JFrame {
    // --- КОЛІРНА ПАЛІТРА ---
    private static final Color BG_DARK = Color.decode("#02182B");
    private static final Color BG_SURFACE = Color.decode("#072540");
    private static final Color BG_INPUT = Color.decode("#041E36");
    private static final Color ACCENT_RED = Color.decode("#D7263D");
    private static final Color BTN_SECONDARY = Color.decode("#103B60");
    private static final Color BORDER_COLOR = Color.decode("#164268");
    private static final Color TEXT_MAIN = new Color(240, 245, 250);
    private static final Color TEXT_MUTED = new Color(150, 175, 200);

    private static final String TEXT_FILE = "employees.txt";
    private static final String BINARY_FILE = "employees.dat";

    private final LocalDatabaseManager dbManager = new LocalDatabaseManager();
    private final DefaultTableModel tableModel;
    private final JTable table;

    /** Поточний список співробітників, що відображається на екрані */
    private List<Employee> currentEmployees = new ArrayList<>();

    private final JTextField txtId = createTextField(5, false);
    private final JTextField txtName = createTextField(15, true);
    private final JTextField txtAge = createTextField(5, true);
    private final JTextField txtPosition = createTextField(15, true);
    private final JTextField txtSalary = createTextField(10, true);

    private final JTextField txtSearchSuffix = createTextField(8, true);
    private final JTextField txtMinAge = createTextField(4, true);

    /**
     * Конструктор головного вікна програми. Ініціалізує компоненти GUI та обробники подій.
     */
    public Project2() {
        setTitle("СР2. Проєкт 2 — Об'єднання ЛР9, ЛР10, ЛР11 (Управління персоналом)");
        setSize(1020, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_DARK);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        dbManager.initSchemaAndSeed();

        // --- ВЕРХНЯ ПАНЕЛЬ: Інструменти ЛР9 та ЛР10 ---
        JPanel topContainer = new JPanel(new GridLayout(2, 1, 5, 5));
        topContainer.setBackground(BG_DARK);

        // Рядок 1: Пошук, Сортування та Мін/Макс зарплата (ЛР9)
        JPanel panelLR9 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        panelLR9.setBackground(BG_SURFACE);
        panelLR9.setBorder(createSectionBorder("ЛР9: Фільтрація, Сортування масиву та Аналіз зарплат"));

        panelLR9.add(createLabel("ПІБ закінчується на:"));
        panelLR9.add(txtSearchSuffix);
        JButton btnFilterSuffix = createButton("Знайти", true);
        panelLR9.add(btnFilterSuffix);

        panelLR9.add(createLabel("Вік >"));
        panelLR9.add(txtMinAge);
        JButton btnFilterAge = createButton("Фільтр віку", false);
        panelLR9.add(btnFilterAge);

        String[] sortOptions = {"Сортування...", "За ім'ям (А-Я)", "За ім'ям (Я-А)", "За віком (зрост.)", "За віком (спад.)"};
        JComboBox<String> cmbSort = new JComboBox<>(sortOptions);
        cmbSort.setBackground(BG_INPUT);
        cmbSort.setForeground(TEXT_MAIN);
        panelLR9.add(cmbSort);

        JButton btnMinMax = createButton("Мін/Макс ЗП", true);
        JButton btnReset = createButton("Скинути (З БД)", false);
        panelLR9.add(btnMinMax);
        panelLR9.add(btnReset);

        // Рядок 2: Робота з файлами (ЛР10) + Генерація документації
        JPanel panelLR10 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        panelLR10.setBackground(BG_SURFACE);
        panelLR10.setBorder(createSectionBorder("ЛР10: Файлові потоки, Серіалізація та API-документація"));

        JButton btnReadTxt = createButton("Зчитати з TXT", false);
        JButton btnWriteTxt = createButton("Записати у TXT", false);
        JButton btnWriteBin = createButton("Зберегти у BIN (.dat)", true);
        JButton btnReadBin = createButton("Відновити з BIN (.dat)", false);
        JButton btnSyncToDb = createButton("Зберегти таблицю в БД", true);
        
        // Кнопка програмної генерації Javadoc-документації
        JButton btnGenerateDocs = createButton("📄 Згенерувати API-документацію", true);
        btnGenerateDocs.addActionListener(e -> generateJavadocProgrammatically());

        panelLR10.add(btnReadTxt);
        panelLR10.add(btnWriteTxt);
        panelLR10.add(btnWriteBin);
        panelLR10.add(btnReadBin);
        panelLR10.add(btnSyncToDb);
        panelLR10.add(btnGenerateDocs);

        topContainer.add(panelLR9);
        topContainer.add(panelLR10);
        add(topContainer, BorderLayout.NORTH);

        // --- ЦЕНТРАЛЬНА ПАНЕЛЬ: Таблиця співробітників ---
        String[] columns = {"ID (PK)", "ПІБ Співробітника", "Вік", "Посада", "Зарплата (₴)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setBackground(BG_SURFACE);
        table.setForeground(TEXT_MAIN);
        table.setGridColor(BORDER_COLOR);
        table.setSelectionBackground(ACCENT_RED);
        table.setSelectionForeground(Color.WHITE);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(24);

        table.getTableHeader().setBackground(BTN_SECONDARY);
        table.getTableHeader().setForeground(TEXT_MAIN);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(BG_SURFACE);
        scrollPane.setBorder(new LineBorder(BORDER_COLOR));
        add(scrollPane, BorderLayout.CENTER);

        // --- НИЖНЯ ПАНЕЛЬ: Форма та CRUD-операції з БД SQLite (ЛР11) ---
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBackground(BG_SURFACE);
        bottomPanel.setBorder(createSectionBorder("ЛР11: Керування записами бази даних SQLite (company_local.db)"));

        JPanel formPanel = new JPanel(new GridLayout(2, 5, 8, 5));
        formPanel.setBackground(BG_SURFACE);
        formPanel.setBorder(new EmptyBorder(5, 8, 5, 8));

        formPanel.add(createLabel("ID (Авто):"));
        formPanel.add(createLabel("ПІБ співробітника:"));
        formPanel.add(createLabel("Вік (18–100):"));
        formPanel.add(createLabel("Посада:"));
        formPanel.add(createLabel("Зарплата (>= 0):"));

        formPanel.add(txtId);
        formPanel.add(txtName);
        formPanel.add(txtAge);
        formPanel.add(txtPosition);
        formPanel.add(txtSalary);

        bottomPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        buttonPanel.setBackground(BG_SURFACE);

        JButton btnAdd = createButton("Додати в БД", true);
        JButton btnUpdate = createButton("Оновити в БД", false);
        JButton btnDelete = createButton("Видалити з БД", true);
        JButton btnClear = createButton("Очистити поля", false);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- ОБРОБНИКИ ПОДІЙ ---

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                txtId.setText(tableModel.getValueAt(row, 0).toString());
                txtName.setText(tableModel.getValueAt(row, 1).toString());
                txtAge.setText(tableModel.getValueAt(row, 2).toString());
                txtPosition.setText(tableModel.getValueAt(row, 3).toString());
                txtSalary.setText(tableModel.getValueAt(row, 4).toString());
            }
        });

        btnAdd.addActionListener(e -> {
            try {
                Employee emp = parseFormWithoutId();
                dbManager.addEmployee(emp);
                loadFromDatabase();
                clearForm();
                JOptionPane.showMessageDialog(this, "Запис успішно додано до БД SQLite!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Вік та зарплата мають бути числами!",
                        "Помилка вводу", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Порушено обмеження схеми БД (CHECK constraint):\n" + ex.getMessage(),
                        "Помилка БД", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUpdate.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Спочатку виберіть запис у таблиці!",
                        "Увага", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                int id = Integer.parseInt(txtId.getText());
                Employee emp = parseFormWithoutId();
                Employee updated = new Employee(id, emp.getName(), emp.getAge(), emp.getPosition(), emp.getSalary());
                dbManager.updateEmployee(updated);
                loadFromDatabase();
                clearForm();
                JOptionPane.showMessageDialog(this, "Запис ID " + id + " успішно оновлено!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Перевірте правильність числових полів!",
                        "Помилка вводу", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Порушено обмеження БД: " + ex.getMessage(),
                        "Помилка БД", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnDelete.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Виберіть запис у таблиці для видалення!",
                        "Увага", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = Integer.parseInt(txtId.getText());
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Ви дійсно хочете видалити співробітника з ID " + id + "?",
                    "Підтвердження видалення", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    dbManager.deleteEmployee(id);
                    loadFromDatabase();
                    clearForm();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Помилка видалення: " + ex.getMessage(),
                            "Помилка БД", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnFilterSuffix.addActionListener(e -> {
            String suffix = txtSearchSuffix.getText();
            Company comp = new Company("Tech Solutions", currentEmployees.toArray(new Employee[0]));
            refreshTable(comp.filterByNamePattern(suffix));
        });

        btnFilterAge.addActionListener(e -> {
            try {
                int minAge = Integer.parseInt(txtMinAge.getText().trim());
                Company comp = new Company("Tech Solutions", currentEmployees.toArray(new Employee[0]));
                refreshTable(comp.filterByAge(minAge));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Введіть ціле число для віку!",
                        "Помилка", JOptionPane.WARNING_MESSAGE);
            }
        });

        cmbSort.addActionListener(e -> {
            int idx = cmbSort.getSelectedIndex();
            if (idx <= 0) return;
            Company comp = new Company("Tech Solutions", currentEmployees.toArray(new Employee[0]));
            switch (idx) {
                case 1 -> comp.sortByNameAsc();
                case 2 -> comp.sortByNameDesc();
                case 3 -> comp.sortByAgeAsc();
                case 4 -> comp.sortByAgeDesc();
            }
            refreshTable(List.of(comp.getEmployees()));
        });

        btnMinMax.addActionListener(e -> {
            Company comp = new Company("Tech Solutions", currentEmployees.toArray(new Employee[0]));
            JOptionPane.showMessageDialog(this, comp.getMinMaxSalaryAnalysis(),
                    "Аналіз заробітних плат (ЛР9)", JOptionPane.INFORMATION_MESSAGE);
        });

        btnReset.addActionListener(e -> {
            txtSearchSuffix.setText("");
            txtMinAge.setText("");
            cmbSort.setSelectedIndex(0);
            loadFromDatabase();
        });

        btnReadTxt.addActionListener(e -> {
            try {
                Employee[] loaded = FileStorageManager.readFromTextFile(TEXT_FILE);
                refreshTable(List.of(loaded));
                JOptionPane.showMessageDialog(this,
                        "Успішно зчитано " + loaded.length + " записів із файлу " + TEXT_FILE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Помилка читання " + TEXT_FILE + ":\n" + ex.getMessage(),
                        "Помилка файлу", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnWriteTxt.addActionListener(e -> {
            try {
                FileStorageManager.writeToTextFile(currentEmployees, TEXT_FILE);
                JOptionPane.showMessageDialog(this,
                        "Поточний список успішно збережено у текстовий файл " + TEXT_FILE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Помилка запису у файл: " + ex.getMessage(),
                        "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnWriteBin.addActionListener(e -> {
            try {
                Company comp = new Company("Tech Solutions", currentEmployees.toArray(new Employee[0]));
                comp.sortByNameAsc();
                FileStorageManager.writeToBinaryFile(comp.getEmployees(), BINARY_FILE);
                refreshTable(List.of(comp.getEmployees()));
                JOptionPane.showMessageDialog(this,
                        "Масив відсортовано за ім'ям та серіалізовано у бінарний файл " + BINARY_FILE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Помилка серіалізації: " + ex.getMessage(),
                        "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnReadBin.addActionListener(e -> {
            try {
                Employee[] restored = FileStorageManager.readFromBinaryFile(BINARY_FILE);
                refreshTable(List.of(restored));
                JOptionPane.showMessageDialog(this,
                        "Дані успішно відновлено з нетекстового файлу " + BINARY_FILE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Помилка читання " + BINARY_FILE + ":\n" + ex.getMessage(),
                        "Помилка десеріалізації", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnSyncToDb.addActionListener(e -> {
            int added = 0;
            for (Employee emp : currentEmployees) {
                if (emp.getId() == 0 || txtId.getText().isEmpty()) {
                    try {
                        dbManager.addEmployee(emp);
                        added++;
                    } catch (SQLException ignored) {}
                }
            }
            loadFromDatabase();
            JOptionPane.showMessageDialog(this, "Синхронізовано з базою даних SQLite (додано нових: " + added + ").");
        });

        btnClear.addActionListener(e -> clearForm());

        loadFromDatabase();
    }

    /**
     * Програмно запускає генерацію Javadoc-документації для поточного файлу при натисканні кнопки.
     */
    private void generateJavadocProgrammatically() {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "javadoc",
                    "-d", "docs",
                    "-encoding", "UTF-8",
                    "-charset", "UTF-8",
                    "-docencoding", "UTF-8",
                    "-private",
                    "Project2.java"
            );

            pb.redirectErrorStream(true);
            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), java.nio.charset.StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                JOptionPane.showMessageDialog(this, 
                        "API-документацію успішно згенеровано!\nПапка: /docs (файл index.html)", 
                        "Успіх", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                        "Помилка генерації Javadoc. Перевірте консоль або наявність JDK в системі.", 
                        "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(this, 
                    "Не вдалося запустити javadoc:\n" + ex.getMessage() + 
                    "\n\nПереконайтеся, що ви використовуєте повноцінний JDK.", 
                    "Системна помилка", JOptionPane.ERROR_MESSAGE);
            if (ex instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private TitledBorder createSectionBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(new LineBorder(BORDER_COLOR), title);
        border.setTitleColor(TEXT_MAIN);
        return border;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(TEXT_MUTED);
        return label;
    }

    private static JTextField createTextField(int columns, boolean editable) {
        JTextField field = new JTextField(columns);
        field.setEditable(editable);
        field.setBackground(editable ? BG_INPUT : BG_DARK);
        field.setForeground(editable ? TEXT_MAIN : TEXT_MUTED);
        field.setCaretColor(TEXT_MAIN);
        return field;
    }

    private JButton createButton(String text, boolean isAccent) {
        JButton btn = new JButton(text);
        btn.setBackground(isAccent ? ACCENT_RED : BTN_SECONDARY);
        btn.setForeground(TEXT_MAIN);
        return btn;
    }

    private Employee parseFormWithoutId() {
        String name = txtName.getText();
        int age = Integer.parseInt(txtAge.getText().trim());
        String pos = txtPosition.getText();
        double salary = Double.parseDouble(txtSalary.getText().trim().replace(",", "."));
        return new Employee(name, age, pos, salary);
    }

    private void loadFromDatabase() {
        refreshTable(dbManager.getAllEmployees());
    }

    private void refreshTable(List<Employee> employees) {
        this.currentEmployees = new ArrayList<>(employees);
        tableModel.setRowCount(0);
        for (Employee emp : employees) {
            tableModel.addRow(new Object[]{
                    emp.getId(),
                    emp.getName(),
                    emp.getAge(),
                    emp.getPosition(),
                    String.format("%.2f", emp.getSalary())
            });
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtPosition.setText("");
        txtSalary.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Project2().setVisible(true));
    }
}