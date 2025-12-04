package service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@WebServlet("/book")
public class MainServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Приложение запущено!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = now.format(formatter);

        System.out.println("Запрос получен в " + formattedTime);

        String path = req.getRequestURI();
        if (path.equals("/book")) {
            downloadBook(resp);
        } else if (path.equals("/load-book")) {
            uploadBook(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void downloadBook(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=book.txt");

        Writer writer = resp.getWriter();
        writer.write("Это пример книги.");
        writer.close();
    }

    private void uploadBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Реализация загрузки книги на сервер
        // Здесь можно использовать библиотеки для работы с файлами, например, Apache Commons FileUpload
        resp.setContentType("text/html");
        Writer writer = resp.getWriter();
        writer.write("<html><body><h1>Файл загружен</h1></body></html>");
        writer.close();
    }
}
