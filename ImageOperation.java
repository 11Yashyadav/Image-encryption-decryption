import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class ImageOperation {

    static boolean isDarkTheme = false;

    public static void operate(File file, int key) {
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

            JOptionPane.showMessageDialog(null, "Operation completed successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error processing the file!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void applyTheme(Component[] components, boolean dark) {
        Color bg = dark ? new Color(40, 44, 52) : Color.WHITE;
        Color fg = dark ? Color.WHITE : Color.BLACK;

        for (Component comp : components) {
            comp.setBackground(bg);
            comp.setForeground(fg);
            if (comp instanceof JButton || comp instanceof JTextField || comp instanceof JLabel) {
                comp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            }
            if (comp instanceof JPanel) {
                applyTheme(((JPanel) comp).getComponents(), dark);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Encryptor/Decryptor");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Font font = new Font("Segoe UI", Font.PLAIN, 18);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel keyLabel = new JLabel("Enter Key (Integer):");
        JTextField keyField = new JTextField(10);

        JButton selectButton = new JButton("Select Image");
        JButton encryptButton = new JButton("Encrypt / Decrypt");
        JButton resetButton = new JButton("Reset");
        JButton themeToggle = new JButton("Toggle Theme");

        JLabel selectedFileLabel = new JLabel("No file selected.");

        // Store the selected file
        final File[] selectedFile = new File[1];

        // File chooser
        selectButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile[0] = fileChooser.getSelectedFile();
                selectedFileLabel.setText("Selected: " + selectedFile[0].getName());
                encryptButton.setEnabled(true);
            }
        });

        // Encrypt button
        encryptButton.setEnabled(false);
        encryptButton.addActionListener(e -> {
            try {
                int key = Integer.parseInt(keyField.getText());
                if (selectedFile[0] != null) {
                    operate(selectedFile[0], key);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a file first.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid integer key.");
            }
        });

        // Reset button
        resetButton.addActionListener(e -> {
            keyField.setText("");
            selectedFile[0] = null;
            selectedFileLabel.setText("No file selected.");
            encryptButton.setEnabled(false);
        });

        // Theme toggle button
        themeToggle.addActionListener(e -> {
            isDarkTheme = !isDarkTheme;
            applyTheme(panel.getComponents(), isDarkTheme);
            panel.setBackground(isDarkTheme ? new Color(30, 30, 30) : Color.WHITE);
        });

        // Drag & drop file support
        new DropTarget(panel, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) dtde.getTransferable()
                            .getTransferData(DataFlavor.javaFileListFlavor);
                    if (!droppedFiles.isEmpty()) {
                        selectedFile[0] = droppedFiles.get(0);
                        selectedFileLabel.setText("Dropped: " + selectedFile[0].getName());
                        encryptButton.setEnabled(true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Layout placement
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(keyLabel, gbc);
        gbc.gridx = 1;
        panel.add(keyField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(selectButton, gbc);
        gbc.gridx = 1;
        panel.add(encryptButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(resetButton, gbc);
        gbc.gridx = 1;
        panel.add(themeToggle, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(selectedFileLabel, gbc);

        frame.add(panel);
        applyTheme(panel.getComponents(), isDarkTheme);
        frame.setVisible(true);
    }
}
