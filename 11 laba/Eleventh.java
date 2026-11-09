import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

class Human {
    protected String name;
    protected int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
}

class Employee extends Human {
    private int id;
    private String position;
    private double salary;

    public Employee(int id, String name, int age, String position, double salary) {
        super(name, age);
        this.id = id;
        this.position = position;
        this.salary = salary;
    }

    public Employee(String name, int age, String position, double salary) {
        this(0, name, age, position, salary);
    }

    public int getId() { return id; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
}

class LocalDatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:company_local.db";

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

    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM Employees WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<Employee> filterBySuffix(String suffix) {
        List<Employee> list = new ArrayList<>();
        String cleanSuffix = suffix.trim().toLowerCase();

        for (Employee emp : getAllEmployees()) {
            if (emp.getName().toLowerCase().endsWith(cleanSuffix)) {
                list.add(emp);
            }
        }
        return list;
    }
}

public class Eleventh extends JFrame {
    private static final Color BG_DARK = Color.decode("#02182B");
    private static final Color BG_SURFACE = Color.decode("#072540");
    private static final Color BG_INPUT = Color.decode("#041E36");
    private static final Color ACCENT_RED = Color.decode("#D7263D");
    private static final Color BTN_SECONDARY = Color.decode("#103B60");
    private static final Color BORDER_COLOR = Color.decode("#164268");  
    private static final Color TEXT_MAIN = new Color(240, 245, 250);    
    private static final Color TEXT_MUTED = new Color(150, 175, 200);  

    private final LocalDatabaseManager dbManager = new LocalDatabaseManager();
    private final DefaultTableModel tableModel;
    private final JTable table;

    private final JTextField txtId = createTextField(5, false);
    private final JTextField txtName = createTextField(15, true);
    private final JTextField txtAge = createTextField(5, true);
    private final JTextField txtPosition = createTextField(15, true);
    private final JTextField txtSalary = createTextField(10, true);
    private final JTextField txtSearchSuffix = createTextField(12, true);

    public Eleventh() {
        setTitle("ЛР11 — Клієнт бази даних співробітників (SQLite)");
        setSize(900, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_DARK);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        dbManager.initSchemaAndSeed();

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        topPanel.setBackground(BG_SURFACE);
        topPanel.setBorder(new LineBorder(BORDER_COLOR));

        JLabel lblSearch = new JLabel("ПІБ закінчується на:");
        lblSearch.setForeground(TEXT_MAIN);

        JButton btnFilter = createButton("Знайти", true);
        JButton btnReset = createButton("Показати всі", false);

        topPanel.add(lblSearch);
        topPanel.add(txtSearchSuffix);
        topPanel.add(btnFilter);
        topPanel.add(btnReset);

        add(topPanel, BorderLayout.NORTH);

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

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBackground(BG_SURFACE);
        
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                new LineBorder(BORDER_COLOR), "Дані співробітника");
        titledBorder.setTitleColor(TEXT_MAIN);
        bottomPanel.setBorder(titledBorder);

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

        JButton btnAdd = createButton("Додати", true);
        JButton btnUpdate = createButton("Оновити", false);
        JButton btnDelete = createButton("Видалити", true);
        JButton btnClear = createButton("Очистити поля", false);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

    
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
                refreshTable(dbManager.getAllEmployees());
                clearForm();
                JOptionPane.showMessageDialog(this, "Запис успішно додано до БД!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Вік та зарплата мають бути числами!",
                        "Помилка вводу", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Порушено обмеження схеми БД (CHECK constraint):\n" +
                        "- ПІБ та посада не можуть бути порожніми\n" +
                        "- Вік має бути від 18 до 100 років\n" +
                        "- Зарплата не може бути від'ємною\n\nДеталі SQL: " + ex.getMessage(),
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
                refreshTable(dbManager.getAllEmployees());
                clearForm();
                JOptionPane.showMessageDialog(this, "Запис ID " + id + " успішно оновлено!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Перевірте правильність числових полів (Вік, Зарплата)!",
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
                    refreshTable(dbManager.getAllEmployees());
                    clearForm();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Помилка видалення: " + ex.getMessage(),
                            "Помилка БД", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnFilter.addActionListener(e -> {
            String suffix = txtSearchSuffix.getText();
            if (suffix.trim().isEmpty()) {
                refreshTable(dbManager.getAllEmployees());
            } else {
                refreshTable(dbManager.filterBySuffix(suffix));
            }
        });

        btnReset.addActionListener(e -> {
            txtSearchSuffix.setText("");
            refreshTable(dbManager.getAllEmployees());
        });

        btnClear.addActionListener(e -> clearForm());

        refreshTable(dbManager.getAllEmployees());
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

    private void refreshTable(List<Employee> employees) {
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
        SwingUtilities.invokeLater(() -> new Eleventh().setVisible(true));
    }
}