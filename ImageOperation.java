package image;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ImageOperation {
    JButton encryptButton = new JButton("Encrypt Image");
JButton decryptButton = new JButton("Decrypt Image");
encryptButton.addActionListener(e -> {
    String text = textField.getText();
    if (text.isEmpty()) {
        JOptionPane.showMessageDialog(f, "Enter a key.");
        return;
    }
    int key = Integer.parseInt(text);
    operate(key); // XOR encryption
});
}
