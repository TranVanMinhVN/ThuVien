package model;

public class Book {

    private int id;
    private String nameBook;

    public Book(int id, String nameBook) {
        this.id = id;
        this.nameBook = nameBook;
    }

    public int getId() {
        return id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

}
