package controller.admin;

import constant.SystemConstant;
import dao.IManufacturerDao;
import dao.IOrderDetailDAO;
import dao.IProductDAO;
import model.ProductModel;
import util.FormUtil;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/product-list","/product-edit", "/product-delete", 
                            "/product-update-status"})
public class ProductController extends HttpServlet {
    
    @Inject
    private IProductDAO productDAO;
    
    @Inject
    private IManufacturerDao manufacturerDao;
    
    @Inject
    private IOrderDetailDAO orderDetailDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductModel model = FormUtil.populate(ProductModel.class, request);
        String urlMapping = request.getServletPath();
        if (urlMapping.equals("/product-list")) {
            model.setListResult(productDAO.findAll(null));
            request.setAttribute(SystemConstant.MODEL, model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/list.jsp");
            rd.forward(request, response);
        } else if (urlMapping.equals("/product-edit")) {
            if (model.getId() != 0) {
                model = productDAO.findById(model.getId());
            }
            request.setAttribute(SystemConstant.MODEL, model);
            request.setAttribute("manufacturers", manufacturerDao.findAll(1));
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/edit.jsp");
            rd.forward(request, response);
        } else if (urlMapping.equals("/product-update-status")) {
            model = productDAO.findById(model.getId());
            if (model.getStatus() == 1) {
                model.setStatus(0);
            } else if (model.getStatus() == 0) {
                model.setStatus(1);                
            }            
            productDAO.save(model);
            response.sendRedirect(request.getContextPath() + "/product-list");
        } else if (urlMapping.equals("/product-delete")) {
            orderDetailDAO.deleteByProduct(model.getId());
            productDAO.delete(model.getId());
            response.sendRedirect(request.getContextPath() + "/product-list");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
