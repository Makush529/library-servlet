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

@WebServlet("/upload-book")
public class UpLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Writer writer = resp.getWriter();
        writer.write("<html><head>");
        writer.write("<link rel=\"stylesheet\" href=\"/NewStyle.css\">");
        writer.write("</head><body>");
        writer.write("<h1>Upload Book</h1>");
        writer.write("<form action='/upload-book' method='post'>");
        writer.write("<label for='title'>Название:</label>");
        writer.write("<input type='text' id='title' name='title'><br><br>");
        writer.write("<label for='text'>Текст:</label>");
        writer.write("<textarea id='text' name='text'></textarea><br><br>");
        writer.write("<button type='submit'>Upload</button>");
        writer.write("</form>");
        writer.write("<a href=\"/download-book\"><button>Перейти к скачиванию книг</button></a>");
        writer.write("</body></html>");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RepositoryBook repository = (RepositoryBook) context.getAttribute("repository");
        if (repository == null) {
            repository = new RepositoryBook();
            context.setAttribute("repository", repository);
        }

        String title = req.getParameter("title");
        String text = req.getParameter("text");
        repository.addBook(title, text);

        resp.setContentType("text/html");
        Writer writer = resp.getWriter();
        writer.write("<html><body><h1>Книга загружена</h1>");
        writer.write("<a href=\"/download-book\"><button>Перейти к скачиванию книг</button></a>");
        writer.write("</body></html>");
        writer.close();
    }
}