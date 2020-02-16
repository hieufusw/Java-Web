package controller.admin;

import constant.SystemConstant;
import dao.IManufacturerDao;
import dao.IOrderDetailDAO;
import dao.IProductDAO;
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

@WebServlet(urlPatterns = {"/manufacturer-list","/manufacturer-edit", "/manufacturer-delete", 
                            "/manufacturer-update-status"})
public class ManufacturerController extends HttpServlet {
    
    @Inject
    private IManufacturerDao manufacturerDao;
    
    @Inject
    private IProductDAO productDAO;
    
    @Inject
    private IOrderDetailDAO orderDetailDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ManufacturerModel model = FormUtil.populate(ManufacturerModel.class, request);
        String urlMapping = request.getServletPath();
        if (urlMapping.equals("/manufacturer-list")) {
            model.setListResult(manufacturerDao.findAll(null));
            request.setAttribute(SystemConstant.MODEL, model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/manufacturer/list.jsp");
            rd.forward(request, response);
        } else if (urlMapping.equals("/manufacturer-edit")) {
            if (model.getId() != 0) {
                model = manufacturerDao.findById(model.getId());
            }
            request.setAttribute(SystemConstant.MODEL, model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/manufacturer/edit.jsp");
            rd.forward(request, response);
        } else if (urlMapping.equals("/manufacturer-update-status")) {
            model = manufacturerDao.findById(model.getId());
            if (model.getStatus() == 1) {
                model.setStatus(0);
            } else if (model.getStatus() == 0) {
                model.setStatus(1);                
            }            
            manufacturerDao.save(model);
            response.sendRedirect(request.getContextPath() + "/manufacturer-list");
        } else if (urlMapping.equals("/manufacturer-delete")) {
            List<ProductModel> products = productDAO.findByManufacturerId(model.getId());
            for (ProductModel product: products) {
                orderDetailDAO.deleteByProduct(product.getId());
                productDAO.delete(product.getId());
            }
            manufacturerDao.delete(model.getId());
            response.sendRedirect(request.getContextPath() + "/manufacturer-list");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ManufacturerModel model = FormUtil.populate(ManufacturerModel.class, request);
        String urlMapping = request.getServletPath();
        if (urlMapping.equals("/manufacturer-edit")) { 
            if (model.getId() != 0) {
                ManufacturerModel existModel = manufacturerDao.findById(model.getId());
                model.setStatus(existModel.getStatus());
                manufacturerDao.save(model);
            } else {
                model.setStatus(1);
                manufacturerDao.save(model);
            } 
            response.sendRedirect(request.getContextPath() + "/manufacturer-list");
        }
    }
}
