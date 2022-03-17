package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@WebServlet(urlPatterns = "/getImage")
public class GetImageServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "C:\\Users\\Minasyan\\Desktop\\lessons\\myitemsProject\\images";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String picUrl = req.getParameter("pic_url");
        String filePath = UPLOAD_DIR + picUrl;
        File downoadFile = new File(filePath);
        if (downoadFile.exists()) {
            try {
                FileInputStream inStream = new FileInputStream(downoadFile);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
