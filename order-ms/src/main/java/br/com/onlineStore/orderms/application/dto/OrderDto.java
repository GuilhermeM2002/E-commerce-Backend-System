package br.com.onlineStore.orderms.application.dto;

import br.com.onlineStore.orderms.core.domain.ItemCart;
import br.com.onlineStore.orderms.core.domain.Status;
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
    private AddressDto address;
    private OffsetDateTime date;
    private double value;
    private String TrackingCode;
    private Status status;
}
