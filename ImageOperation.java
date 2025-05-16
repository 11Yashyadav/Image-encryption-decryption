
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

        decryptButton.addActionListener(e -> {
            String text = textField.getText();
            if (!text.matches("\\d+")) {
                JOptionPane.showMessageDialog(f, "Enter a valid numeric key");
                return;
            }
            int key = Integer.parseInt(text);
            operate(key);
        });

        resetButton.addActionListener(e -> textField.setText(""));

    }
}