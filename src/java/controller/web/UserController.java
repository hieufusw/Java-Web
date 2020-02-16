package controller.web;

import constant.SystemConstant;
import model.UserModel;
import service.IUserService;
import util.FormUtil;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/dang-ky", "/cap-nhat-tai-khoan"})
public class UserController extends HttpServlet {

    @Inject
    private IUserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserModel model = FormUtil.populate(UserModel.class, request);
        String urlMapping = request.getServletPath();
        if (urlMapping.equals("/dang-ky")) {
            request.getRequestDispatcher("/views/web/user/register.jsp").forward(request, response);
        } else if (urlMapping.equals("/cap-nhat-tai-khoan")) {
            model = userService.findById(model.getId());
            request.setAttribute(SystemConstant.MODEL, model);
            request.getRequestDispatcher("/views/web/user/profile.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserModel model = FormUtil.populate(UserModel.class, request);
        String urlMapping = request.getServletPath();
        if (urlMapping.equals("/dang-ky")) {
            request.setAttribute(SystemConstant.ALERT, "danger");
            request.setAttribute(SystemConstant.MESSAGE_RESPONSE, "Register Successfully, Please Login.");
            userService.save(model);
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
        if (urlMapping.equals("/cap-nhat-tai-khoan")) {
            userService.update(model);
            response.sendRedirect(request.getContextPath() + "/trang-chu");
        }
    }
}
