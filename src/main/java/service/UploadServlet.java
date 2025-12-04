package service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.RepositoryBook;

import java.io.IOException;
import java.io.Writer;

public class UploadServlet extends HttpServlet {
    private RepositoryBook repository = new RepositoryBook();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        repository.addBook(title, text);

        resp.setContentType("text/html");
        Writer writer = resp.getWriter();
        writer.write("<html><body><h1>Книга загружена</h1></body></html>");
        writer.close();
    }
}