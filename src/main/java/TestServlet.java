import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestServlet extends HttpServlet {
    //todo
    private ToDoList list = new ToDoList();

    private Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);

    {
        list.Add("Пример");
        try {
            cfg.setTemplateLoader(new FileTemplateLoader(new File(".")));
        } catch (IOException E) {
            E.printStackTrace();
        }
    }
Template t= cfg.getTemplate("todoHTML");
    try{
        t.process();
    }

    public TestServlet() throws IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String what = req.getParameter("task");
        list.Add(what);
        resp.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder buf = new StringBuilder();
        List<Item> items = list.View();
        for (Item i : items) {
            buf.append("<li>" + i.getMessage() + "</li>\n");
        }

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Список дел</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form method=\"post\" action=\"get \">\n" +
                "    <input name=\"task\">\n" +
                "    <input type=\"submit\" value=\"добаваить\">\n" +
                "</form>\n" +
                "<ol>\n" +
                buf +
                "</ol>\n" +
                "</body>\n" +
                "</html>");

    }
}
