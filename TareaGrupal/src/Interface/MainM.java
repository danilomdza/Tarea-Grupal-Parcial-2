package Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class MainM {
    private Connection databaseConnection;
    private JFrame mainFrame;
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> tableSelectorComboBox;

    public MainM() {
        // Establecer conexión a postgreSQL
        establishDBConnection();

        // Crear la interfaz gráfica
        createGUI();
    }

    private void establishDBConnection() {
        try {
            String dbUrl = "jdbc:postgresql://localhost:5432/danilomdza";
            String dbUser = "danilomdza";
            String dbPassword = "danilomdza123";
            databaseConnection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Conexión establecida con PostgreSQL.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createGUI() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(900, 700);

        // Centrar el título 
        centerTitle(mainFrame, "Constructores y Conductores de Fórmula 1");

        // Panel para el combo box y el texto
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel selectLabel = new JLabel("Seleccione una tabla:");
        tableSelectorComboBox = new JComboBox<>(new String[]{"", "Constructores", "Conductores", "Conductores y Constructores"}); 
        customizeComboBox(tableSelectorComboBox);
        tableSelectorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTable = (String) tableSelectorComboBox.getSelectedItem();
                if (!selectedTable.isEmpty()) { 
                    refreshTableData(selectedTable);
                } else {
                    clearTableData();
                }
            }
        });

        topPanel.add(selectLabel);
        topPanel.add(tableSelectorComboBox);

        // Tabla pa los datos
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(dataTable);
        customizeTable(dataTable);

        // Añadir el panel superior y la tabla al frame
        mainFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
        mainFrame.getContentPane().add(tableScrollPane, BorderLayout.CENTER);

        mainFrame.setVisible(true);
    }

    private void customizeComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        comboBox.setRenderer(new BasicComboBoxRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(new EmptyBorder(5, 10, 5, 10));
                if (isSelected) {
                    setBackground(new Color(59, 130, 246));
                    setForeground(Color.WHITE);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                }
                return this;
            }
        });
        comboBox.setPreferredSize(new Dimension(200, 30));
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(59, 130, 246), 2, true));
    }

    private void customizeTable(JTable table) {
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(10, 1));
        table.setShowGrid(false);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        table.setSelectionBackground(new Color(59, 130, 246));
        table.setSelectionForeground(Color.WHITE);
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);

        // Centrar el contenido de las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // estilizar la mea tabla pai
        table.setIntercellSpacing(new Dimension(1, 1));
        table.setShowGrid(true);
        table.setBorder(BorderFactory.createLineBorder(new Color(59, 130, 246), 2, true));
        table.setGridColor(new Color(59, 130, 246));
    }

    private void refreshTableData(String selectedTable) {
        Vector<String> columnNames = new Vector<>();
        Vector<Vector<Object>> data = new Vector<>();

        try {
            String query;
            if ("Constructores".equals(selectedTable)) {
                query = "SELECT constructor_id, constructor_ref, name, nationality, url FROM constructors";
                PreparedStatement pstmt = databaseConnection.prepareStatement(query);
                ResultSet resultSet = pstmt.executeQuery();

                columnNames.add("Constructor ID");
                columnNames.add("Constructor Ref");
                columnNames.add("Name");
                columnNames.add("Nationality");
                columnNames.add("URL");

                while (resultSet.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(resultSet.getInt("constructor_id"));
                    row.add(resultSet.getString("constructor_ref"));
                    row.add(resultSet.getString("name"));
                    row.add(resultSet.getString("nationality"));
                    row.add(resultSet.getString("url"));
                    data.add(row);
                }

                resultSet.close();
                pstmt.close();
            } else if ("Conductores".equals(selectedTable)) {
                query = "SELECT driver_id, driver_ref, number, code, forename, surname, dob, nationality, url FROM drivers";
                PreparedStatement pstmt = databaseConnection.prepareStatement(query);
                ResultSet resultSet = pstmt.executeQuery();

                columnNames.add("Driver ID");
                columnNames.add("Driver Ref");
                columnNames.add("Number");
                columnNames.add("Code");
                columnNames.add("Forename");
                columnNames.add("Surname");
                columnNames.add("Date of Birth");
                columnNames.add("Nationality");
                columnNames.add("URL");

                while (resultSet.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(resultSet.getInt("driver_id"));
                    row.add(resultSet.getString("driver_ref"));
                    row.add(resultSet.getInt("number"));
                    row.add(resultSet.getString("code"));
                    row.add(resultSet.getString("forename"));
                    row.add(resultSet.getString("surname"));
                    row.add(resultSet.getDate("dob"));
                    row.add(resultSet.getString("nationality"));
                    row.add(resultSet.getString("url"));
                    data.add(row);
                }

                resultSet.close();
                pstmt.close();
            } else if ("Conductores y Constructores".equals(selectedTable)) {
                query = "SELECT d.driver_id, d.driver_ref, d.number, d.code, d.forename, d.surname, d.dob, d.nationality AS driver_nationality, d.url AS driver_url, " +
                        "c.constructor_id, c.constructor_ref, c.name AS constructor_name, c.nationality AS constructor_nationality, c.url AS constructor_url " +
                        "FROM drivers d " +
                        "LEFT JOIN constructors c ON d.driver_id = c.constructor_id"; 
                PreparedStatement pstmt = databaseConnection.prepareStatement(query);
                ResultSet resultSet = pstmt.executeQuery();

                columnNames.add("Driver ID");
                columnNames.add("Driver Ref");
                columnNames.add("Number");
                columnNames.add("Code");
                columnNames.add("Forename");
                columnNames.add("Surname");
                columnNames.add("Date of Birth");
                columnNames.add("Driver Nationality");
                columnNames.add("Driver URL");
                columnNames.add("Constructor ID");
                columnNames.add("Constructor Ref");
                columnNames.add("Constructor Name");
                columnNames.add("Constructor Nationality");
                columnNames.add("Constructor URL");

                while (resultSet.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(resultSet.getInt("driver_id"));
                    row.add(resultSet.getString("driver_ref"));
                    row.add(resultSet.getInt("number"));
                    row.add(resultSet.getString("code"));
                    row.add(resultSet.getString("forename"));
                    row.add(resultSet.getString("surname"));
                    row.add(resultSet.getDate("dob"));
                    row.add(resultSet.getString("driver_nationality"));
                    row.add(resultSet.getString("driver_url"));
                    row.add(resultSet.getInt("constructor_id"));
                    row.add(resultSet.getString("constructor_ref"));
                    row.add(resultSet.getString("constructor_name"));
                    row.add(resultSet.getString("constructor_nationality"));
                    row.add(resultSet.getString("constructor_url"));
                    data.add(row);
                }

                resultSet.close();
                pstmt.close();
            }

            // Actualizar modelo de la tabla
            tableModel.setDataVector(data, columnNames);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearTableData() {
        tableModel.setDataVector(new Vector<>(), new Vector<>()); 
    }

    private void centerTitle(JFrame frame, String title) {
        Font font = new Font("Serif", Font.BOLD, 16);
        FontMetrics metrics = frame.getFontMetrics(font);
        int frameWidth = frame.getWidth();
        int titleWidth = metrics.stringWidth(title);
        int padding = (frameWidth - titleWidth) / 2;
        StringBuilder paddedTitle = new StringBuilder(title);
        for (int i = 0; i < padding / 8; i++) {
            paddedTitle.insert(0, " ");
        }
        frame.setTitle(paddedTitle.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainM::new);
    }
}
