package io.codeforall.bootcamp.javabank.converters;

import io.codeforall.bootcamp.javabank.dtos.AddressDto;
import io.codeforall.bootcamp.javabank.model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation that converts {@link Address} objects to {@link AddressDto} objects.
 */
@Component
public class AddressToAddressDto implements Converter<Address, AddressDto> {

    /**
     * @see Converter#convert(Object)
     */
    @Override
    public AddressDto convert(Address address) {

        AddressDto addressDto = new AddressDto();

        addressDto.setStreet(address.getStreet());
        addressDto.setCity(address.getCity());
        addressDto.setZipCode(address.getZipCode());

        return addressDto;
    }
}
