package br.com.onlineStore.orderms.application.useCasesImpl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.onlineStore.orderms.adapters.repository.AddressRepository;
import br.com.onlineStore.orderms.application.dto.AddressDto;
import br.com.onlineStore.orderms.core.domain.Address;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RegisterAddressUseCaseImplTest {
      @Mock
      private ModelMapper mapper;
      @Mock
      private AddressRepository addressRepository;
      @InjectMocks
      private RegisterAddressUseCaseImpl registerAddressUseCaseImpl;
      private Address address;
      private AddressDto addressDto;

      @Test
      @DisplayName("Should save order and return order dto")
      void testRegisterAddressCase1() {

            address = mock(Address.class);
            addressDto = mock(AddressDto.class);
            when(addressDto.getCep()).thenReturn("12345");
            when(addressDto.getCity()).thenReturn("City");
            when(addressDto.getNeighborhood()).thenReturn("Neighborhood");
            when(addressDto.getNumber()).thenReturn("Number");
            when(addressDto.getState()).thenReturn("State");
            when(addressDto.getComplement()).thenReturn("Complement");
            when(addressDto.getStreet()).thenReturn("Street");

            when(mapper.map(addressDto, Address.class)).thenReturn(address);
            when(addressRepository.save(any())).thenReturn(address);
            when(mapper.map(address, AddressDto.class)).thenReturn(addressDto);

            var result = registerAddressUseCaseImpl.registerAddress(addressDto);

            assertAll(
                        () -> assertNotNull(result),
                        () -> assertEquals(addressDto, result),
                        () -> verify(mapper).map(addressDto, Address.class),
                        () -> verify(addressRepository).save(address),
                        () -> verify(mapper).map(address, AddressDto.class));
      }

      @Test
      @DisplayName("Should throw an exception if the dto is invalid")
      void testRegisterAddressCase2() {
            AddressDto addressDtoEmpity = mock(AddressDto.class);
            assertThrows(
                        IllegalArgumentException.class,
                        () -> registerAddressUseCaseImpl.registerAddress(addressDtoEmpity));
      }
}
