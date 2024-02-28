package com.sargecaballero.coderproject.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sargecaballero.coderproject.repository.entity.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Long idProduct;
    private String name;
    private String description;
    private String sku;
    private Double price;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public ProductDTO (Product product) {
        this.idProduct = product.getIdProduct();
        this.name = product.getName();
        this.description = product.getDescription();
        this.sku = product.getSku();
        this.price = product.getPrice();
        this.status = product.getStatus();
        this.createTime = product.getCreateTime();
        this.updateTime = product.getUpdateTime();
    }
}