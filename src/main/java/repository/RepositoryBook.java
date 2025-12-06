package repository;

import java.util.HashMap;
import java.util.Map;

public class RepositoryBook {
    private Map<String, String> books;

    public RepositoryBook(){
        books = new HashMap<>();
        books.put("Трудно быть богом", "Текст книги ТББ");
        books.put("Пиноккио","Текст книги П");
        books.put("234","345353");
    }

    public void addBook(String title, String text) {
        books.put(title, text);
    }

    public String getBook(String title) {
        return books.get(title);
    }

    public Map<String, String> getBooks() {
        return books;
    }
}