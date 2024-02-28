package com.sargecaballero.coderproject.controller;

import com.sargecaballero.coderproject.controller.dto.InvoiceDTO;
import com.sargecaballero.coderproject.repository.entity.Invoice;
import com.sargecaballero.coderproject.service.InvoiceService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${base.api.path}/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(invoice -> new ResponseEntity<>(invoice, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Invoice> saveInvoice(@RequestBody Invoice invoice) {
        return new ResponseEntity<>(invoiceService.saveInvoice(invoice), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice updatedInvoice) {
        return invoiceService.updateInvoice(id, updatedInvoice)
                .map(invoice -> new ResponseEntity<>(invoice, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("addProducts")
    public ResponseEntity<Invoice> saveProductToInvoice(@RequestBody InvoiceDTO invoice) {
        logger.info("Entramos al método addProduct");
        logger.info("Llega {}", invoice);
        invoiceService.saveProductToInvoice(invoice.getIdInvoice(), invoice.getItems());
        return invoiceService.getInvoiceById(invoice.getIdInvoice())
                .map(invoice1 -> new ResponseEntity<>(invoice1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
