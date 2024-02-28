package com.sargecaballero.coderproject.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceDTO {

    private Long idInvoice;
    private List<InvoiceItem> items;

    @Data
    public static class InvoiceItem {
        private Long idProduct;
        private Integer quantity;
    }
}
