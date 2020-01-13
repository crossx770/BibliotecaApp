package app.model;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private int bookId;
    private int userId;
    private String username;
    private String bookTitle;
    private String borrowDate;

    public Order(int id, int bookId, int userId, String borrowDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = borrowDate;
    }

    public Order (int id, String username,String bookTitle,String borrowDate) {
        this.id = id;
        this.username = username;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
    }

    public Order () {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }
}
