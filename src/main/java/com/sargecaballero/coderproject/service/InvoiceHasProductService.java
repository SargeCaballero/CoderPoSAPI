package com.sargecaballero.coderproject.service;

import com.sargecaballero.coderproject.repository.InvoiceHasProductRepository;
import com.sargecaballero.coderproject.repository.entity.InvoiceHasProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceHasProductService {

    private final InvoiceHasProductRepository invoiceHasProductRepository;

    @Autowired
    public InvoiceHasProductService(InvoiceHasProductRepository invoiceHasProductRepository) {
        this.invoiceHasProductRepository = invoiceHasProductRepository;
    }

    public List<InvoiceHasProduct> getAllInvoiceHasProducts() {
        return invoiceHasProductRepository.findAll();
    }

    public Optional<InvoiceHasProduct> getInvoiceHasProductById(InvoiceHasProduct.InvoiceHasProductKey id) {
        return invoiceHasProductRepository.findById(id);
    }

    public InvoiceHasProduct saveInvoiceHasProduct(InvoiceHasProduct invoiceHasProduct) {
        return invoiceHasProductRepository.save(invoiceHasProduct);
    }

    public void deleteInvoiceHasProduct(InvoiceHasProduct.InvoiceHasProductKey id) {
        invoiceHasProductRepository.deleteById(id);
    }
}

