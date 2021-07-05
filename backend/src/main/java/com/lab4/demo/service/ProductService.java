package com.lab4.demo.service;

import com.lab4.demo.dto.ProductDTO;
import com.lab4.demo.model.Product;
import com.lab4.demo.repo.ProductRepository;
import com.lab4.demo.service.mapper.ProductMapper;
import com.lab4.demo.websocket.WsServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    
        private final ProductRepository productRepository;
        private final ProductMapper productMapper;

        public List<ProductDTO> findAll() {
            return productRepository.findAll().stream()
                    .map(productMapper::toDto)
                    .collect(Collectors.toList());
        }

        private Product findById(Long id) {
            return productRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
        }

        public ProductDTO create(ProductDTO product) {

            WsServerEndpoint.sendAlert("Product " + product.getName() + " is now for sale in the store!");
            return productMapper.toDto(productRepository.save(
                    productMapper.fromDto(product)
            ));
        }

        public ProductDTO edit(ProductDTO product, Long id) {
            Product actProduct = findById(id);
            actProduct.setId(product.getId());
            actProduct.setName(product.getName());
            actProduct.setPrice(product.getPrice());
            actProduct.setQuantity(product.getQuantity());
            return productMapper.toDto(
                    productRepository.save(actProduct)
            );
        }

        public void delete(Long id){

            WsServerEndpoint.sendAlert("Product with id " + id + " is no longer available");
            productRepository.deleteById(id);
        }
 }


