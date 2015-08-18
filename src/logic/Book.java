package logic;

import java.io.Serializable;

/**
 * Created by DJeFRY_RASH on 06.08.2015.
 */
public class Book implements Comparable<Book>, Serializable {
    private String author;
    private int pageqnt;
    private double price;
    private String bookname;

    public Book(String author, int pageqnt, double price, String bookname) {
        this.author = author;
        this.pageqnt = pageqnt;
        this.price = price;
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageqnt() {
        return pageqnt;
    }

    public void setPageqnt(int pageqnt) {
        this.pageqnt = pageqnt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", pageqnt=" + pageqnt +
                ", price=" + price +
                ", bookname='" + bookname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!author.equals(book.author)) return false;
        return bookname.equals(book.bookname);

    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + bookname.hashCode();
        return result;
    }

    @Override
    public int compareTo(Book other) {
        int res = author.compareTo(other.author);
        if (res != 0) {
            return res;
        }
        return bookname.compareTo(other.bookname);
    }
}