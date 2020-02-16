package controller.web;

import constant.SystemConstant;
import dao.IOrderDAO;
import dao.IOrderDetailDAO;
import dao.IProductDAO;
import model.CartModel;
import model.OrderDetailModel;
import model.OrdersModel;
import model.ProductModel;
import util.FormUtil;
import util.SessionUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/gio-hang", "/them-san-pham-gio-hang", 
                            "/cap-nhat-cart", "/check-out", "/xoa-san-pham-cart", "/xoa-tat-ca-san-pham-cart"})
public class CartController extends HttpServlet {

    @Inject
    private IProductDAO productDAO;

    @Inject
    private IOrderDAO orderDAO;

    @Inject
    private IOrderDetailDAO orderDetailDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlMapping = request.getServletPath();
        CartModel model = FormUtil.populate(CartModel.class, request);
        if (urlMapping.equals("/them-san-pham-gio-hang")) {
            List<CartModel> carts = new ArrayList<>();
            if (SessionUtils.getInstance().getValue(request, "carts") != null) {
                carts = (List<CartModel>) SessionUtils.getInstance().getValue(request, "carts");
                SessionUtils.getInstance().remove(request, "carts");
            }
            ProductModel productModel = productDAO.findById(model.getProductId());
            CartModel cartModel = new CartModel();
            boolean exist = false;
            if (carts != null && carts.size() > 0) {
                for (CartModel item : carts) {
                    if (item.getProductId() == productModel.getId()) {
                        cartModel = item;
                        cartModel.setQuantity(cartModel.getQuantity() + 1);
                        cartModel.setTotal(cartModel.getPrice() * cartModel.getQuantity());
                        exist = true;
                        break;
                    }
                }
            }
            if (exist == false) {
                cartModel.setId(productModel.getId());
                cartModel.setName(productModel.getName());
                cartModel.setPrice(productModel.getPrice());
                cartModel.setQuantity(1);
                cartModel.setTotal(cartModel.getPrice() * cartModel.getQuantity());
                cartModel.setProductId(productModel.getId());
                carts.add(cartModel);
            }
            SessionUtils.getInstance().putValue(request, "carts", carts);
            response.sendRedirect(request.getContextPath() + "/gio-hang");
        } else if (urlMapping.equals("/gio-hang")) {
            if (request.getParameter("message") != null) {
                request.setAttribute(SystemConstant.ALERT, "danger");
                request.setAttribute(SystemConstant.MESSAGE_RESPONSE, "Số lượng quá số lượng sản phẩm");
            }
            int total = 0;
            if (SessionUtils.getInstance().getValue(request, "carts") != null) {
                model.setListResult((List<CartModel>) SessionUtils.getInstance().getValue(request, "carts"));
                for (CartModel item: model.getListResult()) {
                    total += item.getTotal();
                }
            }
            request.setAttribute(SystemConstant.MODEL, model);
            request.setAttribute("total", total);
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/cart/list.jsp");
            rd.forward(request, response);
        } else if (urlMapping.equals("/check-out")) {
            List<CartModel> carts = (List<CartModel>) SessionUtils.getInstance().getValue(request, "carts");
            OrdersModel ordersModel = new OrdersModel();
            int total = 0;
            for (CartModel item : carts) {
                total += item.getTotal();
            }
            ordersModel.setTotal(total);
            request.setAttribute(SystemConstant.MODEL, ordersModel);
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/cart/checkout.jsp");
            rd.forward(request, response);
        } else if (urlMapping.equals("/xoa-san-pham-cart")) {
            List<CartModel> carts = (List<CartModel>) SessionUtils.getInstance().getValue(request, "carts");
            SessionUtils.getInstance().remove(request, "carts");
            carts.removeIf(item -> item.getProductId() == model.getProductId());
            SessionUtils.getInstance().putValue(request, "carts", carts);
            response.sendRedirect(request.getContextPath() + "/gio-hang");
        } else if (urlMapping.equals("/xoa-tat-ca-san-pham-cart")) {
            SessionUtils.getInstance().remove(request, "carts");
            response.sendRedirect(request.getContextPath() + "/gio-hang");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlMapping = request.getServletPath();
        if (urlMapping.equals("/cap-nhat-cart")) {
            CartModel model = FormUtil.populate(CartModel.class, request);
            ProductModel productModel = productDAO.findById(model.getProductId());
            if (model.getQuantity() <= productModel.getQuantity()) {
                List<CartModel> carts = (List<CartModel>) SessionUtils.getInstance().getValue(request, "carts");
                carts.forEach(item -> {
                    if (item.getId() == model.getId()) {
                        item.setQuantity(model.getQuantity());
                        item.setTotal(item.getQuantity() * item.getPrice());
                    }
                });
                SessionUtils.getInstance().remove(request, "carts");
                SessionUtils.getInstance().putValue(request, "carts", carts);
                response.sendRedirect(request.getContextPath() + "/gio-hang");
            } else {
                response.sendRedirect(request.getContextPath() + "/gio-hang?message=QUANTITY_LIMIT");
            }
        } else if (urlMapping.equals("/check-out")) {
            OrdersModel ordersModel = FormUtil.populate(OrdersModel.class, request);
            ordersModel.setStatus(0);
            ordersModel.setCustomerId((int) SessionUtils.getInstance().getValue(request, "id1"));
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            ordersModel.setDate(simpleDateFormat.format(new Date()));
            int orderId = orderDAO.save(ordersModel);
            List<CartModel> carts = (List<CartModel>) SessionUtils.getInstance().getValue(request, "carts");
            for (CartModel cartModel : carts) {
                OrderDetailModel orderDetailModel = new OrderDetailModel();
                orderDetailModel.setOrderId(orderId);
                orderDetailModel.setProductId(cartModel.getProductId());
                orderDetailModel.setQuantity(cartModel.getQuantity());
                orderDetailDAO.save(orderDetailModel);
                ProductModel productModel = productDAO.findById(cartModel.getProductId());
                productModel.setQuantity(productModel.getQuantity() - cartModel.getQuantity());
                productDAO.save(productModel);
            }
            SessionUtils.getInstance().remove(request, "carts");
            response.sendRedirect(request.getContextPath() + "/gio-hang");
        }
    }
}
