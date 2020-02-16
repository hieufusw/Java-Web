package service.impl;

import dao.IProductDAO;
import model.ProductModel;
import service.IProductService;
import util.UploadFileUtil;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

public class ProductService implements IProductService {
    
    @Inject
    private IProductDAO productDAO;

    @Override
    public void save(ProductModel model) {
        if (StringUtils.isNotBlank(model.getBase64Image())) {
            byte[] decodedBase64 = Base64.decodeBase64(model.getBase64Image().getBytes());
            String path = "/product/"+ model.getNameImage();
            UploadFileUtil.writeOrUpdate(path, decodedBase64);
            model.setPicture("/product/"+model.getNameImage());
        }
        if (model.getId() != 0) {
            ProductModel existModel = productDAO.findById(model.getId());
            model.setStatus(existModel.getStatus());
            if (StringUtils.isBlank(model.getBase64Image())) {
                model.setPicture(existModel.getPicture());
            }
            productDAO.save(model);
        } else {
            model.setStatus(1);
            productDAO.save(model);
        }
    }
    
}
