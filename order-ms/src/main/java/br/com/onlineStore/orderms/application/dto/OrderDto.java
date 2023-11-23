package br.com.onlineStore.orderms.application.dto;

import br.com.onlineStore.orderms.core.domain.Address;
import br.com.onlineStore.orderms.core.domain.ItemCart;
import br.com.onlineStore.orderms.core.domain.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Set<ItemCart> itemCart;
    private Address address;
    private OffsetDateTime date;
    private double value;
    private String TrackingCode;
    private Status status;
}
