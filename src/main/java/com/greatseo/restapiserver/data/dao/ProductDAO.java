package com.greatseo.restapiserver.data.dao;

import com.greatseo.restapiserver.data.entity.Product;

public interface ProductDAO {

    Product saveProduct(Product product);

    Product getProduct(String productId);

}
