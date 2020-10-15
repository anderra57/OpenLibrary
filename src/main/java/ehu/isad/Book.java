package ehu.isad;

public class Book {
    String isbn;
    String title;

    String info_url;
    String bib_key;
    String preview_url;
    String thumbnail_url;
    Details details;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

//    @Override
//    public String toString() {
//        return "Book{" +
//                "isbn='" + isbn + '\'' +
//                ", title='" + title + '\'' +
//                ", info_url='" + info_url + '\'' +
//                ", bib_key='" + bib_key + '\'' +
//                ", preview_url='" + preview_url + '\'' +
//                ", thumbnail_url='" + thumbnail_url + '\'' +
//                ", details=" + details +
//                '}';
//    }
}