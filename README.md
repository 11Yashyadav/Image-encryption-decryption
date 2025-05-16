# 🖼️ Image Encryptor 🔐

A simple Java Swing-based GUI application to **encrypt** and **decrypt** images using XOR logic. It provides a clean user interface to perform secure image transformations using a numeric key.

---

## 🚀 Features

- 🔐 Encrypt any image using XOR logic and a numeric key
- 🔓 Decrypt the image back using the same key
- 🖱️ GUI-based interface using Java Swing
- ✅ Input validation for key field (only numbers)
- 🔁 Reset functionality to clear inputs
- 💡 Tooltips for better user experience

---

## 🧰 Technologies Used

- Java (JDK 8+)
- Java Swing (GUI Framework)
- AWT (for layout and font)
- File I/O (FileInputStream & FileOutputStream)

---

## 🖼️ How it Works

1. Launch the app.
2. Enter a numeric key (e.g., `123`).
3. Click `Encrypt Image` to scramble the image using XOR.
4. Use the **same key** and click `Decrypt Image` to revert it back.

> ⚠️ Ensure you remember the key used for encryption, or the image cannot be recovered.

---

## 📸 Screenshots

![image](https://github.com/user-attachments/assets/d493526a-f91c-4f03-98c9-03ceae21a71a)

### Encryption 

![image](https://github.com/user-attachments/assets/38fcd47f-3b5d-40ed-914b-513b7c8470c5)

---

## 🧪 Usage

1. Compile the Java file:

```bash
javac ImageOperation.java
````

2. Run the application:

```bash
java ImageOperation
```

---

## 🗃️ Directory Structure

```
ImageEncryptor/
│
├── ImageOperation.java     # Main source code
├── README.md               # Project documentation
└── .gitignore              # Git ignore rules (optional)
```

---

## 🧠 Concepts Behind

* **XOR Encryption**: A simple bitwise operation that can be reversed using the same key.
* **Symmetric Key Logic**: The same key is used for both encryption and decryption.
* **Byte-Level Transformation**: Works directly with image byte data.

---

## 📈 Future Improvements

* 📂 Drag & Drop image support
* 🖼️ Image preview before and after encryption
* 🔑 Save key metadata securely
* 🌐 Build using JavaFX for a modern look

---

## 🙋‍♂️ Author

**Yash Yadav**
📫 [GitHub](https://github.com/11Yashyadav) • ✉️ [yashyadav9325@gmail.com](mailto:yashyadav9325@gmail.com)

---
