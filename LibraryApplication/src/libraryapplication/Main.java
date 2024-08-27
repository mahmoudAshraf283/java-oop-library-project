
package libraryapplication;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Library library = new Library(10, 10, 10);
        Librarian librarian1 = new Librarian("Mahmoud", "L001");
        Librarian librarian2 = new Librarian("Mohammed", "L002");
        library.addUser(librarian1);
        library.addUser(librarian2);
        Member member1 = new Member("Ahmed", "M001");
        Member member2 = new Member("Islam", "M002");
        Member member3 = new Member("Atef", "M003");
        Member member4 = new Member("Momen", "M004");
        library.addUser(member1);
        library.addUser(member2);
        library.addUser(member3);
        library.addUser(member4);

        
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1);
        Book book2 = new Book("1984", "George Orwell", 1);
        Book book3 = new Book("Treasure Island", "Robert Louis Stevenson", 1);
        Book book4 = new Book("Pride and Prejudice", "Jane Austen", 1);
        Book book5 = new Book("Wuthering Heights", "Emily Brontë", 1);
        Book book6 = new Book("Macbeth", "William Shakespeare", 1);
        Book book7 = new Book("The Merchant of Venice", "William Shakespeare", 1);
        Book book8 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);
        library.addBook(book7);
        library.addBook(book8);


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter user type (Librarian/Member/Register/Exit): ");
            String userType = scanner.nextLine();

            if (userType.equalsIgnoreCase("Librarian")) {
                System.out.print("Enter Librarian ID: ");
                String librarianID = scanner.nextLine();
                Librarian loggedInLibrarian = (Librarian) library.getUserByID(librarianID);

                if (loggedInLibrarian != null) {
                    System.out.println("Logged in as Librarian");

                    while (true) {
                        System.out.println("Options: 1. Show available books, 2. Lend book, 3. Return book, 4. Exit");
                        int option = scanner.nextInt();
                        scanner.nextLine();

                        if (option == 1) {
                            loggedInLibrarian.showAllAvailableBooks(library);
                        } else if (option == 2) {
                            System.out.print("Enter Member ID: ");
                            String memberID = scanner.nextLine();
                            Member member = (Member) library.getUserByID(memberID);
                            if (member != null) {
                                System.out.print("Enter Book Title: ");
                                String bookTitle = scanner.nextLine();
                                Book bookToLend = null;
                                for (Book book : library.getBooks()) {
                                    if (book != null && book.getTitle().equalsIgnoreCase(bookTitle)) {
                                        bookToLend = book;
                                        break;
                                    }
                                }
                                if (bookToLend != null) {
                                    member.borrowBook(bookToLend, library);
                                } else {
                                    System.out.println("Book not found.");
                                }
                            } else {
                                System.out.println("Member not found.");
                            }
                        } else if (option == 3) {
                            System.out.print("Enter Member ID: ");
                            String memberID = scanner.nextLine();
                            Member member = (Member) library.getUserByID(memberID);
                            if (member != null) {
                                member.showBorrowedBooks();
                                System.out.print("Enter Book Title to return: ");
                                String bookTitle = scanner.nextLine();
                                Book bookToReturn = null;
                                for (Book book : member.getBorrowedBooks()) {
                                    if (book != null && book.getTitle().equalsIgnoreCase(bookTitle)) {
                                        bookToReturn = book;
                                        break;
                                    }
                                }
                                if (bookToReturn != null) {
                                    member.returnBook(bookToReturn, library);
                                } else {
                                    System.out.println("Book not found.");
                                }
                            } else {
                                System.out.println("Member not found.");
                            }
                        } else if (option == 4) {
                            break;
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid Librarian ID.");
                }
            } else if (userType.equalsIgnoreCase("Member")) {
                System.out.print("Enter Member ID: ");
                String memberID = scanner.nextLine();
                Member loggedInMember = (Member) library.getUserByID(memberID);

                if (loggedInMember != null) {
                    System.out.println("Logged in as Member");

                    while (true) {
                        System.out.println("Options: 1. Show available books, 2. Show borrowed books, 3. Exit");
                        int option = scanner.nextInt();
                        scanner.nextLine();

                        if (option == 1) {
                            library.showAvailableBooks();
                        } else if (option == 2) {
                            loggedInMember.showBorrowedBooks();
                            if (loggedInMember.hasBooksToReturn()) {
                                System.out.println("You have books to return.");
                            } else {
                                System.out.println("No books to return.");
                            }
                        } else if (option == 3) {
                            break;
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid Member ID.");
                }
            } else if (userType.equalsIgnoreCase("Register")) {
                System.out.print("Enter Librarian ID to authorize registration: ");
                String librarianID = scanner.nextLine();

                User user = library.getUserByID(librarianID);
                if (user instanceof Librarian) { 
                    System.out.print("Enter Member Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Member ID: ");
                    String userID = scanner.nextLine();

                    if (library.getUserByID(userID) != null) {
                        System.out.println("This ID is already taken. Please choose a different ID.");
                    } else {
                        Member newMember = new Member(name, userID);
                        library.addUser(newMember);
                        System.out.println("Member registered successfully.");
                    }
                } else {
                    System.out.println("Invalid Librarian ID. Registration failed.");
                }
            
            } else if (userType.equalsIgnoreCase("Exit")) {
                break;
            } else {
                System.out.println("Invalid user type.");
            }
        }

        scanner.close();
    }
}