package com.greatseo.restapiserver.controller;

import com.greatseo.restapiserver.common.exception.ApplicationException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.greatseo.restapiserver.common.Constants.ExceptionClass;
import com.greatseo.restapiserver.common.exception.ApplicationException;
import com.greatseo.restapiserver.data.dto.ProductDTO;
import com.greatseo.restapiserver.service.ProductService;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/api/v1/product-api/product/{productId}
    @GetMapping(value = "/product/{productId}")
    public ProductDTO getProduct(@PathVariable String productId) {

        long startTime = System.currentTimeMillis();
        LOGGER.info("[getProduct] perform {} of REST API.", "getProduct");

        ProductDTO ProductDTO = productService.getProduct(productId);

        LOGGER.info(
            "[getProduct] Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms",
            ProductDTO.getProductId(),
            ProductDTO.getProductName(), ProductDTO.getProductPrice(), ProductDTO.getProductStock(),
            (System.currentTimeMillis() - startTime));
        return ProductDTO;
    }

    // http://localhost:8080/api/v1/product-api/product
    /*@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })*/
    @PostMapping(value = "/product")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO ProductDTO) {

        LOGGER.info("[createProduct] perform {} of REST API.", "createProduct");

        // Validation Code Example
        if (ProductDTO.getProductId().equals("") || ProductDTO.getProductId().isEmpty()) {
            LOGGER.error("[createProduct] failed Response :: productId is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ProductDTO);
        }

        String productId = ProductDTO.getProductId();
        String productName = ProductDTO.getProductName();
        int productPrice = ProductDTO.getProductPrice();
        int productStock = ProductDTO.getProductStock();

        ProductDTO response = productService.saveProduct(productId, productName, productPrice, productStock);

        LOGGER.info(
            "[createProduct] Response >> productId : {}, productName : {}, productPrice : {}, productStock : {}",
            response.getProductId(), response.getProductName(), response.getProductPrice(),
            response.getProductStock());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // http://localhost:8080/api/v1/product-api/product/{productId}
    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @DeleteMapping(value = "/product/{productId}")
    public ProductDTO deleteProduct(@PathVariable String productId) {
        return null;
    }

    @PostMapping(value = "/product/exception")
    public void exceptionTest() throws ApplicationException {
        throw new ApplicationException(ExceptionClass.PRODUCT, HttpStatus.FORBIDDEN, "접근이 금지되었습니다.");
    }

}
