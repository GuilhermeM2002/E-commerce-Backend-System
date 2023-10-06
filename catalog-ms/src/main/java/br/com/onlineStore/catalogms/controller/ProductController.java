package br.com.onlineStore.catalogms.controller;

import br.com.onlineStore.catalogms.aplication.dto.ProductDto;
import br.com.onlineStore.catalogms.aplication.useCasesImpl.PersistProductUseCaseImpl;
import br.com.onlineStore.catalogms.aplication.useCasesImpl.UpdateProductUseCaseImpl;
import br.com.onlineStore.catalogms.infra.exception.PriceInvalidException;
import br.com.onlineStore.catalogms.infra.ProductRepositoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductRepositoryService productRepository;
    @Autowired
    private PersistProductUseCaseImpl persistProduct;
    @Autowired
    private UpdateProductUseCaseImpl updateProduct;
    @PostMapping
    public ResponseEntity persist(@RequestBody @Valid ProductDto dto, UriComponentsBuilder builder) throws PriceInvalidException {
        var product = persistProduct.persistProduct(dto);
        var uri = builder.path("product/{code}").buildAndExpand(product.getCodeProduct()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping("{code}")
    public ResponseEntity update(@RequestBody @Valid ProductDto dto, @PathVariable Long code){
        var product = updateProduct.updateProduct(code, dto);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{code}")
    public ResponseEntity delete(@PathVariable Long code){
        productRepository.deleteProduct(code);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<ProductDto> getAll(@PageableDefault(size = 20, sort = "name") Pageable pageable){
        return productRepository.findAllProduct(pageable);
    }

    @GetMapping("{code}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long code){
        var product = productRepository.findByCodeProduct(code);
        return ResponseEntity.ok(product);
    }
}
