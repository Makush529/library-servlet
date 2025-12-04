package repository;

import java.util.HashMap;
import java.util.Map;

public class RepositoryBook {
    public Map<String, String> books = new HashMap<>();
    public void addBook(String title, String text) {
        books.put(title, text);
    }

    public String getBook(String title) {
        return books.get(title);
    }
}