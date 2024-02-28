package com.sargecaballero.coderproject.repository;

import com.sargecaballero.coderproject.repository.entity.Invoice;
import com.sargecaballero.coderproject.repository.entity.InvoiceHasProduct;
import com.sargecaballero.coderproject.repository.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceHasProductRepository extends JpaRepository<InvoiceHasProduct, InvoiceHasProduct.InvoiceHasProductKey> {

    @Transactional
    default void saveProductToInvoice(Invoice invoice, Product product, Integer amount) {
        InvoiceHasProduct invoiceHasProduct = new InvoiceHasProduct();
        InvoiceHasProduct.InvoiceHasProductKey key = new InvoiceHasProduct.InvoiceHasProductKey(invoice.getIdInvoice(), product.getIdProduct());
        invoiceHasProduct.setId(key);
        invoiceHasProduct.setAmount(amount);
        save(invoiceHasProduct);
    }
}
