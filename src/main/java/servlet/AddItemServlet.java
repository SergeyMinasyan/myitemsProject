package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(urlPatterns = "/addItem")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddItemServlet extends HttpServlet {
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private static final String IMAGE_PATH = "C:\\Users\\Minasyan\\Desktop\\lessons\\myitemsProject\\images";

    //    User user = new User();
    private final String UPLOAD_DIR = "C:\\Users\\Minasyan\\Desktop\\lessons\\taskManagement\\upload";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryManager.getAllCategories());
        req.getRequestDispatcher("/WEB-INF/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Part filePart = req.getPart("picture");
        String fileName = filePart.getSubmittedFileName();
        String picUrl = System.nanoTime() + "_" + fileName;
        filePart.write(IMAGE_PATH + picUrl);

        String title = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int catId = Integer.parseInt("cat_id");
        Category category = categoryManager.getCategoryById(catId);
        Item item = Item.builder()
                .title(title)
                .description(description)
                .price(price)
                .User(user)
                .picUrl(picUrl)
                .build();
        itemManager.add(item);
        resp.sendRedirect("/home");


//
//        for (Part part : req.getParts()) {
//            if (getFileName(part) != null) {
//                String fileName = System.currentTimeMillis() + getFileName(part);
//                String fullFileName = UPLOAD_DIR + File.separator + fileName;
//                part.write(fullFileName);
//                user.setPictureUrl(fileName);
//            }
//        }
//        userManager.addUser(user);
//        resp.sendRedirect("/managerHome");
//    }
//
//    private String getFileName(Part part) {
//        for (String content : part.getHeader("content-disposition").split(";")) {
//            if (content.trim().startsWith("filename"))
//                return content.substring(content.indexOf("=") + 2, content.length() - 1);
//        }
//        return null;
//    }
    }
}
