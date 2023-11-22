package br.com.onlineStore.orderms.adapters.repository;

import br.com.onlineStore.orderms.core.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
