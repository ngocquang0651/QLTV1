package model;

public class Book {
    public static int id= 100000;
    private int bookID;
    private String bookName;
    private String author;
    private String specialization;
    private int publishYear;
    private int quantity;
    //Constructor
    public Book() {
        this.bookID = bookID;
    }

    public Book(int bookID, String bookName, String author, String specialization, int publishYear, int quantity) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.specialization = specialization;
        this.publishYear = publishYear;
        this.quantity = quantity;
    }

    //getter, setter
    public int getBookID() {
        return bookID;
    }
    public void setBookID(){
        this.bookID=id++;
    }
    public void setBookID(int id) {
        this.bookID = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", specialization='" + specialization + '\'' +
                ", publishYear=" + publishYear +
                ", quantity=" + quantity +
                '}';
    }
}
