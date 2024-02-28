package com.sargecaballero.coderproject.service;

import com.sargecaballero.coderproject.controller.dto.ProductDTO;
import com.sargecaballero.coderproject.repository.ProductRepository;
import com.sargecaballero.coderproject.repository.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getStatus() > 0)
                .map(ProductDTO::new)
                .toList();
    }

    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .filter(product -> product.getStatus() > 0)
                .map(ProductDTO::new);
    }

    public List<ProductDTO> getProductsByName(String name) {
        return productRepository.findByName(name)
                .stream()
                .filter(product -> product.getStatus() > 0)
                .map(ProductDTO::new)
                .toList();
    }

    public ProductDTO saveProduct(Product product) {
        return new ProductDTO(productRepository.save(product));
    }

    public Optional<ProductDTO> updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    if (updatedProduct.getName() != null)
                        existingProduct.setName(updatedProduct.getName());
                    if (updatedProduct.getDescription() != null)
                        existingProduct.setDescription(updatedProduct.getDescription());
                    if (updatedProduct.getSku() != null)
                        existingProduct.setSku(updatedProduct.getSku());
                    if (updatedProduct.getPrice() != null)
                        existingProduct.setPrice(updatedProduct.getPrice());
                    if (updatedProduct.getStatus() != null)
                        existingProduct.setStatus(updatedProduct.getStatus());
                    return productRepository.save(existingProduct);
                })
                .map(ProductDTO::new);
    }

    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .ifPresent(existingProduct -> {
                    existingProduct.setStatus(0);
                    productRepository.save(existingProduct);
                });
    }
}