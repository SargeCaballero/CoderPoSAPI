package com.sargecaballero.coderproject.service;

import com.sargecaballero.coderproject.controller.dto.InvoiceDTO;
import com.sargecaballero.coderproject.repository.InvoiceHasProductRepository;
import com.sargecaballero.coderproject.repository.InvoiceRepository;
import com.sargecaballero.coderproject.repository.ProductRepository;
import com.sargecaballero.coderproject.repository.entity.Invoice;
import com.sargecaballero.coderproject.repository.entity.Product;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;
    private final InvoiceHasProductRepository invoiceHasProductRepository;
    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    public InvoiceService(ProductRepository productRepository,
                          InvoiceRepository invoiceRepository,
                          InvoiceHasProductRepository invoiceHasProductRepository) {
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
        this.invoiceHasProductRepository = invoiceHasProductRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .filter(invoice -> invoice.getStatus() > 0)
                .toList();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .filter(invoice -> invoice.getStatus() > 0);
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Optional<Invoice> updateInvoice(Long id, Invoice updatedInvoice) {
        return invoiceRepository.findById(id)
                .map(existingInvoice -> {
                    if (updatedInvoice.getClient() != null)
                        existingInvoice.setClient(updatedInvoice.getClient());
                    if (updatedInvoice.getStatus() != null)
                        existingInvoice.setStatus(updatedInvoice.getStatus());
                    return invoiceRepository.save(existingInvoice);
                });
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.findById(id)
                .ifPresent(existingInvoice -> {
                    existingInvoice.setStatus(0);
                    invoiceRepository.save(existingInvoice);
                });
    }

    @Transactional
    public void saveProductToInvoice(Long invoiceId, List<InvoiceDTO.InvoiceItem> products) {
        logger.info("Saving products to invoice");
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " + invoiceId));
        logger.info("Invoice encontrado: {}", invoice);

        for (InvoiceDTO.InvoiceItem item : products) {
            Product product = productRepository.findById(item.getIdProduct())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + item.getIdProduct()));
            logger.info("Estamos a punto de salvar el invoiceHasProduct");
            logger.info("Invoice: {}", invoice);
            logger.info("Product: {}", product);
            logger.info("Amount: {}", item.getQuantity());
            invoiceHasProductRepository.saveProductToInvoice(invoice, product, item.getQuantity());
        }
        logger.info("Estamos a punto de salvar el invoice: {}", invoice);
        invoiceRepository.save(invoice);
        logger.info("Producto salvado exitosamente");
    }
}