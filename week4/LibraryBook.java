public class LibraryBook {
    private String bookTitle = "Unknown";
    private String author = "Unknown";
    private Integer pages = 1;

    public LibraryBook(String bookTitle, String author, Integer pages) {
        setBookTitle(bookTitle);
        setAuthor(author);
        setPages(pages);
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPages(Integer pages) {
        if(pages > 0) {
            this.pages = pages;
        }
    }

    public String getBookTitle() {return bookTitle;}
    public String getAuthor() {return author;}
    public Integer getPages() {return pages;}

    public boolean isThick(){
        return pages > 500;
    }

}
