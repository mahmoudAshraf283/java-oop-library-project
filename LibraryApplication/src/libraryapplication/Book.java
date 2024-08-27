
package libraryapplication;

public class Book implements LibraryItem {
    private String title;
    private String author;
    private int availableCopies;

    public Book(String title, String author, int availableCopies) {
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public String getTitle() { 
        return title; 
    }
    public String getAuthor() { 
        return author; 
    }
    public int getAvailableCopies() { 
        return availableCopies; 
    }
    public boolean isAvailable() { 
        return availableCopies > 0;
    }

    public void borrowBook() { 
        if (availableCopies > 0) 
            availableCopies--; 
    }
    public void returnBook() { 
        availableCopies++; 
    }
}
