package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class RegisterServlet extends HttpServlet {
    private UserManager userManager = new UserManager();


    User user = new User();
    private final String UPLOAD_DIR = "C:\\Users\\Minasyan\\Desktop\\lessons\\taskManagement\\upload";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        if (userManager.getByEmail(email) == null) {
            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .phone(phone)
                    .password(password)
                    .build();
            userManager.add(user);
        }
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
