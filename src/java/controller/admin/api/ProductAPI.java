package controller.admin.api;

import dao.IProductDAO;
import model.ProductModel;
import service.IProductService;
import util.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/api-product"})
public class ProductAPI extends HttpServlet {
    
    @Inject
    private IProductService productService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        ProductModel model = HttpUtil.of(request.getReader()).toDto(ProductModel.class);
        productService.save(model);
        mapper.writeValue(response.getOutputStream(), "{}");
    }
}
