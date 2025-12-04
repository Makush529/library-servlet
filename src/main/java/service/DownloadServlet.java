package service;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.RepositoryBook;

import java.io.IOException;
import java.io.Writer;

public class DownloadServlet extends HttpServlet {
    private RepositoryBook repository = new RepositoryBook();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = repository.getBook(title);
        if (text != null) {
            resp.setContentType("text/plain");
            Writer writer = resp.getWriter();
            writer.write("Название: " + title + "\nТекст: " + text);
            writer.close();
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}