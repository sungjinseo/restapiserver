package com.greatseo.restapiserver.data.handler;

import com.greatseo.restapiserver.data.entity.Product;

public interface ProductDataHandler {

  Product saveProductEntity(String productId, String productName, int productPrice, int productStock);

  Product getProductEntity(String productId);

}
