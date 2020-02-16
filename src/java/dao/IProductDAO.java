package dao;

import model.ProductModel;
import java.util.List;

public interface IProductDAO {
    List<ProductModel> findAll(Integer status);
    void save(ProductModel model);
    ProductModel findById(int id);
    List<ProductModel> findByManufacturerId(int manufacturerId);
    void delete(int id);
}
