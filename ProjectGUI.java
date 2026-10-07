import java.awt.*;
import javax.swing.*;

public class ProjectGUI extends JFrame {

    public ProjectGUI() {
        setTitle("Курсовий проєкт (ЛР 1-7) - Київський Єгор");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Про автора", createAuthorPanel());
        tabbedPane.addTab("ЛР 1-3 (Обчислення)", createBasicMathPanel());
        tabbedPane.addTab("ЛР 4-5 (Табулювання)", createTabulationPanel());
        tabbedPane.addTab("ЛР 6-7 (ООП)", createOOPPanel());

        add(tabbedPane);
    }

    private JPanel createAuthorPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel photoLabel;
        try {
            ImageIcon icon = new ImageIcon("photo.jpg");
            Image img = icon.getImage().getScaledInstance(200, 250, Image.SCALE_SMOOTH);
            photoLabel = new JLabel(new ImageIcon(img));
        } catch (Exception e) {
            photoLabel = new JLabel("<html><div style='text-align: center; border: 1px solid #B41414; padding: 50px; color: #FF9999;'>" +
                    "Місце для фото<br>(додайте photo.jpg)</div></html>", SwingConstants.CENTER);
        }

        JLabel infoLabel = new JLabel("<html><h2 style='text-align:center; color: #FF4444;'>Автор проєкту</h2>" +
                "<h3 style='text-align:center; color: #FFFFFF;'>Київський Єгор Юрійович</h3>" +
                "<p style='text-align:center; font-size:14px; color: #CCCCCC;'>Студент групи КН-24002б<br>" +
                "Національний університет біоресурсів і природокористування України<br>" +
                "https://github.com/WhyTile</p></html>", SwingConstants.CENTER);

        panel.add(infoLabel, BorderLayout.NORTH);
        panel.add(photoLabel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBasicMathPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Введіть значення X:"));
        JTextField xField = new JTextField("1.0");
        inputPanel.add(xField);
        inputPanel.add(new JLabel("Введіть значення Y:"));
        JTextField yField = new JTextField("0.5");
        inputPanel.add(yField);

        JButton calcButton = new JButton("Обчислити (ЛР 1-3)");
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        calcButton.addActionListener(e -> {
            try {
                double x = Double.parseDouble(xField.getText());
                double y = Double.parseDouble(yField.getText());
                double a = 2.0, c = 1.5;

                double bottom = Math.pow(Math.sin(x + y), 3) + Math.abs(x - y);
                if (bottom == 0) {
                    resultArea.setText("Помилка ОДЗ: знаменник дорівнює нулю!");
                } else {
                    double top = Math.pow(a, 5) + Math.pow(Math.sin(y - c), 4);
                    double p = top / bottom;
                    resultArea.setText(String.format("Результат:\nX = %.2f, Y = %.2f\nP = %.5f", x, y, p));
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Помилка: введіть коректні числові значення!");
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        panel.add(calcButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTabulationPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Введіть Y:"));
        JTextField yField = new JTextField("0.5", 10);
        JButton tabButton = new JButton("Табулювати (ЛР 4-5)");
        topPanel.add(yField);
        topPanel.add(tabButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        tabButton.addActionListener(e -> {
            try {
                double y = Double.parseDouble(yField.getText());
                double a = 2.0, c = 1.5;
                int n = 8;
                double startX = -10 - 2.5 * n;
                double endX = 5 + 1.2 * n;
                double stepX = 0.5 + (double)n / 20;

                StringBuilder sb = new StringBuilder();
                sb.append(String.format("Діапазон: від %.2f до %.2f, крок %.2f\n\n", startX, endX, stepX));
                sb.append("=== Обчислення циклом FOR ===\n");

                for (double x = startX; x <= endX + 0.0001; x += stepX) {
                    double bottom = Math.pow(Math.sin(x + y), 3) + Math.abs(x - y);
                    if (bottom == 0) {
                        sb.append(String.format("x = %8.2f | ОДЗ: знаменник = 0\n", x));
                    } else {
                        double top = Math.pow(a, 5) + Math.pow(Math.sin(y - c), 4);
                        sb.append(String.format("x = %8.2f | P = %10.5f\n", x, (top / bottom)));
                    }
                }
                resultArea.setText(sb.toString());
            } catch (NumberFormatException ex) {
                resultArea.setText("Помилка: введіть коректне числове значення для Y!");
            }
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createOOPPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton runOOPButton = new JButton("Запустити демонстрацію класів та інтерфейсів");
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        runOOPButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("=== Демонстрація ООП (Сутність 'Людина') ===\n\n");
            
            sb.append("[1. Базовий клас та перевантаження]\n");
            sb.append("Створено: Людина: Єгор, Вік: 20\n");
            sb.append("Єгор каже: Привіт!\n");
            sb.append("Єгор каже: Я вивчаю ООП.\n\n");
            
            sb.append("[2. Інтерфейси та перевизначення (Спадкування)]\n");
            sb.append("Створено: Людина: Анна, Вік: 25\n");
            sb.append("Посада: Розробник | Зарплата: ₴45000.5\n");
            sb.append("[Штатний] Анна виконує обов'язки на посаді: Розробник\n\n");

            sb.append("[3. Абстрактні класи та поліморфізм]\n");
            sb.append("Створено: Людина: Олег, Вік: 22\n");
            sb.append("Статус: Фрілансер | Проєкт: Розробка сайту | Зароблено: ₴20000.0\n");
            sb.append("[Фріланс] Олег пише код для проєкту: Розробка сайту\n");
            sb.append("Звіт: Фрілансер Олег відпрацював 40 годин.\n\n");
            
            sb.append("[4. Агрегація]\n");
            sb.append("=== Компанія: Tech Solutions ===\n");
            sb.append("Штат компанії заповнено об'єктами співробітників.\n");

            resultArea.setText(sb.toString());
        });

        panel.add(runOOPButton, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        return panel;
    }

    private static void applyDarkRedTheme() {
        Color darkBg = new Color(30, 30, 30);
        Color componentBg = new Color(45, 45, 45);
        Color redAccent = new Color(180, 20, 20);
        Color textWhite = new Color(240, 240, 240);
        Color textRed = new Color(255, 100, 100);

        UIManager.put("Panel.background", darkBg);
        UIManager.put("Label.foreground", textWhite);
        
        UIManager.put("Button.background", redAccent);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        
        UIManager.put("TextField.background", componentBg);
        UIManager.put("TextField.foreground", textWhite);
        UIManager.put("TextField.caretForeground", textWhite);
        
        UIManager.put("TextArea.background", componentBg);
        UIManager.put("TextArea.foreground", textRed);
        UIManager.put("TextArea.caretForeground", textWhite);
        
        UIManager.put("ScrollPane.background", darkBg);
        UIManager.put("ScrollPane.getViewport().background", darkBg);
        UIManager.put("ScrollPane.border", BorderFactory.createLineBorder(redAccent, 1));
        
        UIManager.put("TabbedPane.background", componentBg);
        UIManager.put("TabbedPane.foreground", textWhite);
        UIManager.put("TabbedPane.selected", redAccent);
        UIManager.put("TabbedPane.contentAreaColor", darkBg);
    }

    public static void main(String[] args) {
        applyDarkRedTheme();

        SwingUtilities.invokeLater(() -> {
            new ProjectGUI().setVisible(true);
        });
    }
}