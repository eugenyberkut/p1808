package gui;

import logic.Book;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 18.08.2015.
 */
public class MyFrame extends JFrame{
    List<Book> books;
    JTable table = new JTable();

    public MyFrame(String title) {
        super(title);
        books = new ArrayList<>();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        createCenterPanel();

        createRightPanel();

        pack();

    }

    private void createCenterPanel() {
//        JPanel mainPanel = new JPanel();
//        mainPanel.setPreferredSize(new Dimension(500, 400));
//        getContentPane().add(mainPanel);

        table.setModel(new BookTableModel(books));
        getContentPane().add(new JScrollPane(table));
    }

    private void createRightPanel() {
        JPanel newBookPanel = new JPanel(new GridLayout(7,1));
        JTextField editAuthor = new JTextField(10);
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new Label("Автор"));
        p.add(editAuthor);
        newBookPanel.add(p); // 1

        JTextField editNazvanie = new JTextField(10);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new Label("Название"));
        p.add(editNazvanie);
        newBookPanel.add(p); // 2

        JTextField editPages = new JTextField(5);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new Label("Страницы"));
        p.add(editPages);
        newBookPanel.add(p); // 3

        JTextField editPrice = new JTextField(5);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new Label("Цена"));
        p.add(editPrice);
        newBookPanel.add(p); // 4
        JButton addBookButton = new JButton("Добавить");
        p = new JPanel();
        p.add(addBookButton);
        newBookPanel.add(p); // 5

        JButton saveButton  = new JButton("Сохранить");
        p = new JPanel();
        p.add(saveButton);
        JButton openButton = new JButton("Открыть");
        p.add(openButton);
        newBookPanel.add(p); // 6


        p = new JPanel();
        JButton findButton = new JButton("Найти");
        p.add(findButton);
        newBookPanel.add(p); // 7

        getContentPane().add(newBookPanel, BorderLayout.EAST);

        addBookButton.addActionListener(e -> {
            String author = editAuthor.getText();
            String nazvanie = editNazvanie.getText();
            int pages = Integer.parseInt(editPages.getText());
            double price = Double.parseDouble(editPrice.getText());
            Book book = new Book(author, pages, price, nazvanie);
            books.add(book);
            clearFields(editAuthor, editNazvanie, editPages, editPrice);
        });

        saveButton.addActionListener(e -> {
            saveBookList();
        });

        openButton.addActionListener(e -> {
            openBookList();
        });

        findButton.addActionListener(e->{
            findMaxPrice();
        });
    }

    private void findMaxPrice() {
        double max = books.get(0).getPrice();
        Book bookMax = books.get(0);
        for (Book b : books) {
            if (b.getPrice() > max) {
                max = b.getPrice();
                bookMax = b;
            }
        }
//        System.out.println(bookMax);
        JOptionPane.showMessageDialog(this, "Самая дорогая книга: " + bookMax);
    }

    private void openBookList() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream("books.dat"))){
            books = (List<Book>) stream.readObject();
            table.setModel(new BookTableModel(books));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveBookList() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("books.dat"))) {
            stream.writeObject(books);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields(JTextField... edits) {
        for (int i = 0; i < edits.length; i++) {
            JTextField edit = edits[i];
            edit.setText("");
        }
        table.setModel(new BookTableModel(books));
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() ->
                        new MyFrame("Книги").setVisible(true)
        );
    }
}
