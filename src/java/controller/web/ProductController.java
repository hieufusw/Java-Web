package controller.web;

import constant.SystemConstant;
import dao.IManufacturerDao;
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

@WebServlet(urlPatterns = {"/san-pham"})
public class ProductController extends HttpServlet {

    @Inject
    private IProductDAO productDAO;
    
    @Inject
    private IManufacturerDao manufacturerDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductModel model = FormUtil.populate(ProductModel.class, request);
        model.setListResult(productDAO.findByManufacturerId(model.getManufacturerId()));
        request.setAttribute("manufacturers", manufacturerDao.findAll(1));
        request.setAttribute(SystemConstant.MODEL, model);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/product/list.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
