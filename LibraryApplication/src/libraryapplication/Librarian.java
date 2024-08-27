
package libraryapplication;

public class Librarian extends User {
    public Librarian(String name, String userID) {
        super(name, userID);
    }

    public void addBook(Library library, Book book) {
        library.addBook(book);
    }

    public void removeBook(Library library, Book book) {
        library.removeBook(book);
    }

    public void showAllAvailableBooks(Library library) {
        library.showAvailableBooks();
    }

    public void showAllBorrowedBooks(Library library) {
        library.showBorrowedBooks();
    }
}