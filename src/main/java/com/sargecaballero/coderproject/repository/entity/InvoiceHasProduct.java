package com.sargecaballero.coderproject.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class InvoiceHasProduct implements Serializable {
    @EmbeddedId
    private InvoiceHasProductKey id;
    private Integer amount;
    private Double price;
    @Column(insertable = false)
    private Integer status;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;
    @Column(insertable = false, updatable = false)
    private LocalDateTime updateTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idInvoice")
    @JoinColumn(name = "idInvoice", nullable = false)
    private Invoice invoice;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idProduct")
    @JoinColumn(name = "idProduct", nullable = false)
    private Product product;

    @Data
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvoiceHasProductKey implements Serializable {
        @Column(name = "idInvoice")
        private Long idInvoice;
        @Column(name = "idProduct")
        private Long idProduct;
    }
}

