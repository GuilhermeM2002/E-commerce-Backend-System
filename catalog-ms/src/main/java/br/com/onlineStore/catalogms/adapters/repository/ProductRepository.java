package br.com.onlineStore.catalogms.adapters.repository;

import br.com.onlineStore.catalogms.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
