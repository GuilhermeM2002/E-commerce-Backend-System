package br.com.onlineStore.orderms.adapters.http;

import br.com.onlineStore.orderms.application.dto.ItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@FeignClient("shoppingCart-ms")
public interface CartClient {
    @RequestMapping(method = RequestMethod.GET, value = "shoppingCart/{token}")
    Set<ItemDto> getAllItems(@PathVariable String token);
}
