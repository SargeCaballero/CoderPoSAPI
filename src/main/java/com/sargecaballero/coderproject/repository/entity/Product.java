package com.sargecaballero.coderproject.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sargecaballero.coderproject.controller.dto.InvoiceDTO;
import com.sargecaballero.coderproject.controller.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"invoices"})
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String name;
    private String description;
    private String sku;
    private Double price;
    @Column(insertable = false)
    private Integer status;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;
    @Column(insertable = false, updatable = false)
    private LocalDateTime updateTime;
    @ManyToMany(mappedBy = "products")
    private List<Invoice> invoices;
}