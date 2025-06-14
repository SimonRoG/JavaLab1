package gui;

import car.*;
import collections.TaxiCollection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TaxiGUI extends JFrame {
    private TaxiCollection taxiCollection;
    private DefaultTableModel table;
    private JTable taxiTable;
    private JTextField brandField, modelField, hpField, weightField, fareField;
    private JCheckBox turboCheckBox;
    private JTextArea output;
    private Timer timer;
    private JLabel timerLabel;
    private int seconds;

    public TaxiGUI() {
        taxiCollection = new TaxiCollection();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        setupTimer();

        setTitle("Taxi depot management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 550);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        // Поля введення
        brandField = new JTextField(10);
        modelField = new JTextField(10);
        hpField = new JTextField(10);
        weightField = new JTextField(10);
        fareField = new JTextField(10);
        turboCheckBox = new JCheckBox();

        // Таблиця
        String[] columns = {"ID", "Brand", "Model", "HP", "Weight", "Turbo", "Fare"};
        table = new DefaultTableModel(columns, 0);
        taxiTable = new JTable(table);

        // Область результатів
        output = new JTextArea(10, 30);
        output.setEditable(false);

        timerLabel = new JLabel("work time: 0s");
        seconds = 0;
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Панель введення
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        inputPanel.add(new JLabel("Brand:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        inputPanel.add(brandField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        inputPanel.add(new JLabel("Model:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        inputPanel.add(modelField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        inputPanel.add(new JLabel("HP:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        inputPanel.add(hpField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        inputPanel.add(new JLabel("Weight:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        inputPanel.add(weightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        inputPanel.add(new JLabel("Fare:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        inputPanel.add(fareField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        inputPanel.add(new JLabel("Turbo:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        inputPanel.add(turboCheckBox, gbc);

        // Панель кнопок
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add taxi");
        JButton clearBtn = new JButton("Clear");
        JButton analyseBtn = new JButton("Analyse");
        JButton filterBtn = new JButton("Turbo filter");

        btnPanel.add(addBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(analyseBtn);
        btnPanel.add(filterBtn);

        // Основна панель
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.WEST);
        mainPanel.add(btnPanel, BorderLayout.CENTER);
        mainPanel.add(timerLabel, BorderLayout.EAST);

        // Компоновка
        add(mainPanel, BorderLayout.NORTH);
        add(new JScrollPane(taxiTable), BorderLayout.CENTER);
        add(new JScrollPane(output), BorderLayout.SOUTH);

        // Налаштування подій
        addBtn.addActionListener(this::addTaxi);
        clearBtn.addActionListener(e -> clearFields());
        analyseBtn.addActionListener(this::analyzeCollection);
        filterBtn.addActionListener(this::filterTurboTaxis);
    }

    private void setupEventHandlers() {
        // Використання лямбда-виразів
        brandField.addActionListener(e -> modelField.requestFocus());
        modelField.addActionListener(e -> hpField.requestFocus());
    }

    private void setupTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                timerLabel.setText("work time: " + seconds + "s");
            }
        });
        timer.start();
    }

    // Додавання таксі з перевіркою
    private void addTaxi(ActionEvent e) {
        try {
            String brand = validateText(brandField.getText(), "Brand");
            String model = validateText(modelField.getText(), "Model");
            int hp = validateNumber(hpField.getText(), "HP", 50, 1500);
            int weight = validateNumber(weightField.getText(), "Weight", 800, 5000);
            int fare = validateNumber(fareField.getText(), "Fare", 10, 500);
            boolean turbo = turboCheckBox.isSelected();

            Taxi taxi = new Taxi(brand, model, hp, weight, turbo, fare);
            taxiCollection.add(taxi);

            Object[] row = {taxi.getId(), brand, model, hp, weight,
                    turbo ? "yes" : "no", fare};
            table.addRow(row);

            clearFields();
            output.append("Taxi added: " + taxi.getName() + "\n");

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Input error: ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String validateText(String text, String fieldName) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " can't be empty!");
        }
        return text.trim();
    }

    private int validateNumber(String text, String fieldName, int min, int max) {
        try {
            int value = Integer.parseInt(text.trim());
            if (value < min || value > max) {
                throw new IllegalArgumentException(
                        fieldName + " must be between " + min + " and " + max);
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    fieldName + " must be integer!");
        }
    }

    private void clearFields() {
        brandField.setText("");
        modelField.setText("");
        hpField.setText("");
        weightField.setText("");
        fareField.setText("");
        turboCheckBox.setSelected(false);
        brandField.requestFocus();
    }

    // Аналіз з використанням лямбда-виразів
    private void analyzeCollection(ActionEvent e) {
        if (taxiCollection.getTaxis().isEmpty()) {
            output.append("empty\n");
            return;
        }

        output.append("\n=== ANALYSIS ===\n");
        output.append("Total amount: " + taxiCollection.getTaxis().size() + "\n");
        output.append("Average weight: " + String.format("%.2f", taxiCollection.averageWeight()) + " кг\n");

        long turboCount = taxiCollection.getTaxis().stream()
                .filter(Taxi::isTurboMod)
                .count();
        output.append("Turbo modified: " + turboCount + "\n");

        taxiCollection.getTaxis().stream()
                .max((t1, t2) -> Integer.compare(t1.getHp(), t2.getHp()))
                .ifPresent(taxi -> output.append("Most powerful: " + taxi.getName() +
                        " (" + taxi.getHp() + " HP)\n"));
    }

    // Фільтрація з використанням посилання на метод
    private void filterTurboTaxis(ActionEvent e) {
        List<Taxi> turboTaxis = taxiCollection.filterByTurbo();

        if (turboTaxis.isEmpty()) {
            output.append("No turbo modified!\n");
            return;
        }

        output.append("\n=== TURBO ===\n");
        turboTaxis.forEach(taxi ->
                output.append(taxi.getName() + " - " + taxi.getHp() + " HP\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TaxiGUI().setVisible(true);
        });
    }
}