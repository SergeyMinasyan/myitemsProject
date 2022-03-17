package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/singleItem")
public class SingleItemServlet extends HttpServlet {
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Item itemById = itemManager.getItemById(id);
        if (itemById == null){
            resp.sendRedirect("/home");
        }else {
            req.setAttribute("item", itemById);
            req.setAttribute("categories", categoryManager.getAllCategories());
            req.getRequestDispatcher("/WEB-INF/singleItem.jsp").forward(req, resp);

        }



    }
}
