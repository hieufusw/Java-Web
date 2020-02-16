package dao;

import model.OrdersModel;
import java.util.List;

public interface IOrderDAO {
    int save(OrdersModel model);
    List<OrdersModel> findAll();
    OrdersModel findById(int id);
}
