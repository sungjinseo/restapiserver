package com.greatseo.restapiserver.service;

import com.greatseo.restapiserver.data.dto.ProductDTO;

public interface ProductService {

  ProductDTO saveProduct(String productId, String productName, int productPrice, int productStock);

  ProductDTO getProduct(String productId);

}
