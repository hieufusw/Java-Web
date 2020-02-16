package controller.web;

import constant.SystemConstant;
import dao.IManufacturerDao;
import dao.IProductDAO;
import model.UserModel;
import service.IUserService;
import util.FormUtil;
import util.SessionUtils;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat"})
public class HomeController extends HttpServlet {

    @Inject
    private IUserService userService;

    @Inject
    private IProductDAO productDAO;

    @Inject
    private IManufacturerDao manufacturerDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlMapping = request.getServletPath();
        if (urlMapping.equals("/dang-nhap")) {
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        } else if (urlMapping.equals("/thoat")) {
            SessionUtils.getInstance().remove(request, "FULLNAME");
            SessionUtils.getInstance().remove(request, "id");
            SessionUtils.getInstance().remove(request, "USERNAME");
            
            SessionUtils.getInstance().remove(request, "FULLNAME1");
            SessionUtils.getInstance().remove(request, "id1");
            SessionUtils.getInstance().remove(request, "USERNAME1");
            response.sendRedirect(request.getContextPath() + "/trang-chu");
        } else if (urlMapping.equals("/trang-chu")) {
            request.setAttribute("manufacturers", manufacturerDao.findAll(1));
            request.setAttribute("products", productDAO.findAll(1));
            request.getRequestDispatcher("/views/web/home.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserModel model = FormUtil.populate(UserModel.class, request);
        String urlMapping = request.getServletPath();
        if (urlMapping.equals("/dang-nhap")) {
            model = userService.authenticatedUser(model.getUserName(), model.getPassword());
            if (model != null) {
                if (model.getRoleCode().equals("admin")) {
                    SessionUtils.getInstance().putValue(request, "FULLNAME", model.getFullName());
                    SessionUtils.getInstance().putValue(request, "id", model.getId());
                    SessionUtils.getInstance().putValue(request, "USERNAME", model.getUserName());
                    response.sendRedirect(request.getContextPath() + "/home");
                } else if (model.getRoleCode().equals("user")) {
                    SessionUtils.getInstance().putValue(request, "FULLNAME1", model.getFullName());
                    SessionUtils.getInstance().putValue(request, "id1", model.getId());
                    SessionUtils.getInstance().putValue(request, "USERNAME1", model.getUserName());
                    response.sendRedirect(request.getContextPath() + "/trang-chu");
                }
            } else {
                request.setAttribute(SystemConstant.ALERT, "danger");
                request.setAttribute(SystemConstant.MESSAGE_RESPONSE, "Login failed: Username or Password is incorrect.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            }
        }
    }
}
