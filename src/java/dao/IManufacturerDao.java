package dao;

import model.ManufacturerModel;
import java.util.List;

public interface IManufacturerDao {
    List<ManufacturerModel> findAll(Integer status);
    void save(ManufacturerModel model);
    ManufacturerModel findById(int id);
    void delete(int id);
}
