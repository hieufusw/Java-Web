package controller.admin;

import constant.SystemConstant;
import dao.IOrderDAO;
import dao.impl.OrderDetailDAO;
import model.OrderDetailModel;
import model.OrdersModel;
import model.ProductModel;
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

@WebServlet(urlPatterns = {"/bill-list", "/bill-change-status", "/bill-detail"})
public class BillController extends HttpServlet {
    
    @Inject
    private IOrderDAO orderDAO;
    
    @Inject
    private OrderDetailDAO orderDetailDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrdersModel model = FormUtil.populate(OrdersModel.class, request);
        String urlMapping = request.getServletPath();
        if (urlMapping.equals("/bill-list")) {
            model.setListResult(orderDAO.findAll());
            request.setAttribute(SystemConstant.MODEL, model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/bill/list.jsp");
            rd.forward(request, response);
        } else if (urlMapping.equals("/bill-detail")) {
            model = orderDAO.findById(model.getId());
            List<OrderDetailModel> orderDetailModels = orderDetailDAO.findByOrderId(model.getId());            
            request.setAttribute("orderDetailModels", orderDetailModels);
            request.setAttribute("order", model);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/bill/detail.jsp");
            rd.forward(request, response);
        } else if (urlMapping.equals("/bill-change-status")) {
            model = orderDAO.findById(model.getId());
            model.setStatus(1);
            orderDAO.save(model);
            response.sendRedirect(request.getContextPath() + "/bill-list");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
    }
}
