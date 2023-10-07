package br.com.onlineStore.authenticationms.adapters.repository;

import br.com.onlineStore.authenticationms.core.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
