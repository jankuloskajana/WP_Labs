package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name="BookListServlet", urlPatterns = "")
public class BookListServlet extends HttpServlet {
    private final BookService bookService;
    private final SpringTemplateEngine templateEngine;

    public BookListServlet(BookService bookService, SpringTemplateEngine templateEngine) {
        this.bookService = bookService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String search = req.getParameter("search");
        Double rating = null;

        if (search != null && !search.isEmpty()) {
            try {
                rating = Double.parseDouble(search);
            } catch (NumberFormatException e) {
                rating = null;
            }
        }

        List<Book> books;
        if(search != null && !search.isEmpty()) {
            books = bookService.searchBooks(search,rating);
        }
        else {
            books = bookService.listAll();
        }
        context.setVariable("books", books);
        templateEngine.process("listBooks.html", context, resp.getWriter());

    }
}