
// Step 1: Basic GUI with XOR Encryption
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ImageOperation {
    public static void operate(int key) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            fis.read(data);

            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (data[i] ^ key);
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();

            JOptionPane.showMessageDialog(null, "Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Image Operation");
        f.setSize(400, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Roboto", Font.BOLD, 20);

        JButton encryptButton = new JButton("Encrypt Image");
        JButton decryptButton = new JButton("Decrypt Image");
        JButton resetButton = new JButton("Reset");
        JTextField textField = new JTextField(10);

        encryptButton.setFont(font);
        decryptButton.setFont(font);
        resetButton.setFont(font);
        textField.setFont(font);
        textField.setToolTipText("Enter numeric key (e.g., 123)");

        encryptButton.setToolTipText("Encrypt the image with XOR key");
        decryptButton.setToolTipText("Decrypt the image with XOR key");
        resetButton.setToolTipText("Clear the input field");

        encryptButton.addActionListener(e -> {
            String text = textField.getText();
            if (!text.matches("\\d+")) {
                JOptionPane.showMessageDialog(f, "Enter a valid numeric key");
                return;
            }
            int key = Integer.parseInt(text);
            operate(key);
        });
        textField.setDragEnabled(true);

        decryptButton.addActionListener(e -> {
            String text = textField.getText();
            if (!text.matches("\\d+")) {
                JOptionPane.showMessageDialog(f, "Enter a valid numeric key");
                return;
            }
            int key = Integer.parseInt(text);
            operate(key);
        });
        JButton themeButton = new JButton("Toggle Theme");
        themeButton.setFont(font);

        themeButton.addActionListener(e -> {
            boolean isDark = f.getBackground().equals(java.awt.Color.DARK_GRAY);
            f.getContentPane().setBackground(isDark ? java.awt.Color.WHITE : java.awt.Color.DARK_GRAY);
        });

        f.add(themeButton);

        resetButton.addActionListener(e -> textField.setText(""));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "bmp"));

    }
    f.setLayout(new java.awt.GridBagLayout());
java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
gbc.gridx = 0;
gbc.gridy = 0;
f.add(button, gbc);

gbc.gridy++;
f.add(textField, gbc);

gbc.gridy++;
f.add(resetButton, gbc);

gbc.gridy++;
f.add(themeButton, gbc);
button.setToolTipText("Click to encrypt/decrypt image");
resetButton.setToolTipText("Clear the key field");
themeButton.setToolTipText("Toggle between dark and light mode");

}