package controller.admin;

import constant.SystemConstant;
import dao.IManufacturerDao;
import dao.IOrderDetailDAO;
import dao.IProductDAO;
import dao.IUserDAO;
import model.ManufacturerModel;
import model.ProductModel;
import model.UserModel;
import util.FormUtil;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/customer-list", "/customer-delete", "/customer-update-status"})
public class CustomerController extends HttpServlet {
    
    @Inject
    private IManufacturerDao manufacturerDao;
    
    @Inject
    private IProductDAO productDAO;
    
    @Inject
    private IOrderDetailDAO orderDetailDAO;
    
    @Inject
    private IUserDAO userDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserModel model = FormUtil.populate(UserModel.class, request);
        String urlMapping = request.getServletPath();
        if (urlMapping.equals("/customer-list")) {
            model.setListResult(userDAO.findByRole("user"));
            request.setAttribute(SystemConstant.MODEL, model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/customer/list.jsp");
            rd.forward(request, response);
        } else if (urlMapping.equals("/customer-update-status")) {
            model = userDAO.findById(model.getId());
            if (model.getStatus() == 1) {
                model.setStatus(0);
            } else if (model.getStatus() == 0) {
                model.setStatus(1);                
            } 
            userDAO.save(model);
            response.sendRedirect(request.getContextPath() + "/customer-list");
        } else if (urlMapping.equals("/customer-delete")) {
            model = userDAO.findById(model.getId());
            model.setDeleted(1);
            userDAO.save(model);
            response.sendRedirect(request.getContextPath() + "/customer-list");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
