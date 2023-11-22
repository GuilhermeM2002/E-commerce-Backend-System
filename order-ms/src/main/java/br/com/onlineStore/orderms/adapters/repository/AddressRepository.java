package br.com.onlineStore.orderms.adapters.repository;

import br.com.onlineStore.orderms.core.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
