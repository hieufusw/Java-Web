package dao;

import model.OrderDetailModel;
import java.util.List;

public interface IOrderDetailDAO {
    void save(OrderDetailModel model);
    List<OrderDetailModel> findByOrderId(int orderId);
    void deleteByProduct(int productId);
    void deleteByOrder(int orderId);
}
