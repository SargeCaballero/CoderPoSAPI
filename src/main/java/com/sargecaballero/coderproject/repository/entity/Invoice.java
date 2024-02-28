package com.sargecaballero.coderproject.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvoice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idClient", nullable = false)
    private Client client;
    @Column(insertable = false, updatable = false)
    private Double total;
    @Column(insertable = false)
    private Integer status;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;
    @Column(insertable = false, updatable = false)
    private LocalDateTime updateTime;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "InvoiceHasProduct",
            joinColumns = @JoinColumn(name = "idInvoice"),
            inverseJoinColumns = @JoinColumn(name = "idProduct"))
    private List<Product> products;
}
