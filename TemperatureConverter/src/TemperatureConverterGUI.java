import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverterGUI extends JFrame {

    private JTextField inputField;
    private JComboBox<String> unitSelector;
    private JTextArea resultArea;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        
        JLabel inputLabel = new JLabel("Enter Temperature:");
        inputField = new JTextField(10);

        JLabel unitLabel = new JLabel("Select Unit:");
        String[] units = { "Celsius", "Fahrenheit", "Kelvin" };
        unitSelector = new JComboBox<>(units);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertListener());

        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);

       
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(inputLabel, gbc);

        gbc.gridx = 1;
        panel.add(inputField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(unitLabel, gbc);

        gbc.gridx = 1;
        panel.add(unitSelector, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(convertButton, gbc);

        gbc.gridy = 3;
        panel.add(new JScrollPane(resultArea), gbc);

        add(panel);
    }

    private class ConvertListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                double inputTemp = Double.parseDouble(inputField.getText());
                String unit = (String) unitSelector.getSelectedItem();
                StringBuilder result = new StringBuilder();

                switch (unit) {
                    case "Celsius":
                        result.append("Fahrenheit: ").append(String.format("%.2f", (inputTemp * 9 / 5 + 32))).append(" °F\n");
                        result.append("Kelvin: ").append(String.format("%.2f", (inputTemp + 273.15))).append(" K");
                        break;
                    case "Fahrenheit":
                        result.append("Celsius: ").append(String.format("%.2f", ((inputTemp - 32) * 5 / 9))).append(" °C\n");
                        result.append("Kelvin: ").append(String.format("%.2f", ((inputTemp - 32) * 5 / 9 + 273.15))).append(" K");
                        break;
                    case "Kelvin":
                        result.append("Celsius: ").append(String.format("%.2f", (inputTemp - 273.15))).append(" °C\n");
                        result.append("Fahrenheit: ").append(String.format("%.2f", ((inputTemp - 273.15) * 9 / 5 + 32))).append(" °F");
                        break;
                }

                resultArea.setText(result.toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric temperature.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TemperatureConverterGUI().setVisible(true);
        });
    }
}
