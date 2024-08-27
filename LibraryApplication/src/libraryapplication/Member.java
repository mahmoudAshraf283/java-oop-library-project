
package libraryapplication;

public class Member extends User {
    private Book[] borrowedBooks;
    private int borrowedCount;

    public Member(String name, String userID) {
        super(name, userID);
        borrowedBooks = new Book[5];
        borrowedCount = 0;
    }

    public void borrowBook(Book book, Library library) {
        if (borrowedCount < borrowedBooks.length && book.isAvailable()) {
            borrowedBooks[borrowedCount++] = book;
            book.borrowBook();
            library.removeBook(book);
            System.out.println(getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println("Cannot borrow more books or book not available.");
        }
    }

    public void returnBook(Book book, Library library) {
        for (int i = 0; i < borrowedCount; i++) {
            if (borrowedBooks[i] == book) {
                book.returnBook();
                library.addBook(book);
                borrowedBooks[i] = borrowedBooks[--borrowedCount];
                borrowedBooks[borrowedCount] = null;
                System.out.println(getName() + " returned " + book.getTitle());
                break;
            }
        }
    }

    public void showBorrowedBooks() {
        System.out.println("Borrowed books:");
        for (int i = 0; i < borrowedCount; i++) {
            System.out.println("- " + borrowedBooks[i].getTitle() + " by " + borrowedBooks[i].getAuthor());
        }
    }

    public boolean hasBooksToReturn() {
        return borrowedCount > 0;
    }

    public Book[] getBorrowedBooks() {
        return borrowedBooks;
    }
}