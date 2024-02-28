package com.sargecaballero.coderproject.repository;

import com.sargecaballero.coderproject.repository.entity.Invoice;
import com.sargecaballero.coderproject.repository.entity.InvoiceHasProduct;
import com.sargecaballero.coderproject.repository.entity.Product;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceHasProductRepository extends JpaRepository<InvoiceHasProduct, InvoiceHasProduct.InvoiceHasProductKey> {

    Logger logger = org.slf4j.LoggerFactory.getLogger(InvoiceHasProductRepository.class);

    @Transactional
    default void saveProductToInvoice(Invoice invoice, Product product, Integer amount) {
        logger.info("-------------------------------------------------------------------");
        logger.info("Llegamos al repositorio InvoiceHasProductRepository");
        logger.info("-------------------------------------------------------------------");
        InvoiceHasProduct invoiceHasProduct = new InvoiceHasProduct();
        logger.info("Ya creamos el elemnto de liga");
        logger.info("Vamos a crear la llave con {} y {}", invoice.getIdInvoice(), product.getIdProduct());
        InvoiceHasProduct.InvoiceHasProductKey key = new InvoiceHasProduct.InvoiceHasProductKey(invoice.getIdInvoice(), product.getIdProduct());
        logger.info("Ya creamos la llave, quedó así: {}", key);
        invoiceHasProduct.setInvoiceHasProductKey(key);
        logger.info("Ya quedó asignada. Ahora amount {}", amount);
        invoiceHasProduct.setAmount(amount);
        logger.info("Hay que guardar el precio para siempre");
        invoiceHasProduct.setPrice(product.getPrice());
        logger.info("Ya quedó todo, solo falta guardar");
        invoiceHasProduct.setInvoice(invoice);
        invoiceHasProduct.setProduct(product);
        logger.info("El nuevo elemento será: {}", invoiceHasProduct);
        save(invoiceHasProduct);
        logger.info("LISTO Terminamos");
    }
}
