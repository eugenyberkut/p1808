package gui;

import logic.Book;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Yevhen on 18.08.2015.
 */
public class BookTableModel extends AbstractTableModel {
    private static final String[] columns = {"Автор","Название","Стр","Цена"};
    private List<Book> books;

    public BookTableModel(List<Book> books) {
        this.books = books;
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        switch (columnIndex) {
            case 0: return book.getAuthor();
            case 1: return book.getBookname();
            case 2: return book.getPageqnt();
            case 3: return book.getPrice();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Book book = books.get(rowIndex);
        if (columnIndex==0) {
            book.setAuthor(aValue.toString());
        } else if (columnIndex == 1){
            book.setBookname(aValue.toString());
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex < 2;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
