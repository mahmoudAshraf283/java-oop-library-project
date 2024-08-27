
package libraryapplication;

public class Library {
    private Book[] books;
    private User[] users;
    private BorrowRecord[] borrowRecords;
    private int bookCount = 0;
    private int userCount = 0;
    private int recordCount = 0;

    public Library(int maxBooks, int maxUsers, int maxRecords) {
        books = new Book[maxBooks];
        users = new User[maxUsers];
        borrowRecords = new BorrowRecord[maxRecords];
    }

    public void addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount++] = book;
            System.out.println("Book added: " + book.getTitle());
        } else {
            System.out.println("Library is full, cannot add more books.");
        }
    }

    public void removeBook(Book book) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i] == book) {
                books[i] = books[--bookCount];
                books[bookCount] = null;
                System.out.println("Book removed: " + book.getTitle());
                break;
            }
        }
    }

    public void addBookToCollection(Book book) {
        if (bookCount < books.length) {
            books[bookCount++] = book;
            System.out.println("Book added back: " + book.getTitle());
        } else {
            System.out.println("Library is full, cannot add more books.");
        }
    }

    public Book[] getBooks() {
        return books;
    }

    public void addUser(User user) {
        if (userCount < users.length) {
            users[userCount++] = user;
            System.out.println(user.getName() + " registered.");
        } else {
            System.out.println("User list is full, cannot add more users.");
        }
    }

    public User getUserByID(String userID) {
        for (User user : users) {
            if (user != null && user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    public void showAvailableBooks() {
        System.out.println("Available books:");
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                System.out.println("- " + books[i].getTitle());
            }
        }
    }

    public void showBorrowedBooks() {
        System.out.println("Borrowed books:");
        for (int i = 0; i < recordCount; i++) {
            Book book = borrowRecords[i].getBook();
            Member member = borrowRecords[i].getMember();
            System.out.println("- " + book.getTitle() + " borrowed by " + member.getName());
        }
    }

    public void lendBook(Member member, Book book) {
        if (book.isAvailable()) {
            removeBook(book);
            if (recordCount < borrowRecords.length) {
                borrowRecords[recordCount++] = new BorrowRecord(book, member);
                book.borrowBook();
                System.out.println("Book lent: " + book.getTitle() + " to " + member.getName());
            } else {
                System.out.println("Cannot lend more books, record list is full.");
            }
        } else {
            System.out.println("Book not available for lending.");
        }
    }

    public void acceptReturnedBook(Member member, Book book) {
        for (int i = 0; i < recordCount; i++) {
            if (borrowRecords[i].getBook() == book && borrowRecords[i].getMember() == member) {
                borrowRecords[i] = borrowRecords[--recordCount];
                borrowRecords[recordCount] = null;
                book.returnBook();
                addBookToCollection(book);
                System.out.println("Book returned: " + book.getTitle() + " by " + member.getName());
                return;
            }
        }
        System.out.println("This book was not borrowed by the specified member.");
    }
}