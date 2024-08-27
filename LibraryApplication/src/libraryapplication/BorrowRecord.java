
package libraryapplication;


public class BorrowRecord {
    private Book book;
    private Member member;

    public BorrowRecord(Book book, Member member) {
        this.book = book;
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }
}
