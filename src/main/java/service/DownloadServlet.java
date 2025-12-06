package service;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.RepositoryBook;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@WebServlet("/download-book")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RepositoryBook repository = (RepositoryBook) context.getAttribute("repository");
        if (repository == null) {
            repository = new RepositoryBook();
            context.setAttribute("repository", repository);
        }

        String title = req.getParameter("title");
        if (title == null) {
            // Отображение формы скачивания
            Map<String, String> books = repository.getBooks();
            resp.setContentType("text/html");
            Writer writer = resp.getWriter();
            writer.write("<html><head>");
            writer.write("<link rel=\"stylesheet\" href=\"/NewStyle.css\">");
            writer.write("</head><body>");
            writer.write("<h1>Download Book</h1>");
            writer.write("<form action='/download-book' method='get'>");
            writer.write("<label for='title'>Название:</label>");
            writer.write("<select id='title' name='title'>");
            for (String bookTitle : books.keySet()) {
                writer.write("<option value='" + bookTitle + "'>" + bookTitle + "</option>");
            }
            writer.write("</select><br><br>");
            writer.write("<button type='submit'>Download</button>");
            writer.write("</form>");
            writer.write("<a href=\"/upload-book\"><button>Перейти к загрузке книг</button></a>");
            writer.write("</body></html>");
            writer.close();
        } else {
            // Скачивание книги
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
}