package com.diviso.graeshoppe.service.mapper;

import com.diviso.graeshoppe.domain.*;
import com.diviso.graeshoppe.service.dto.CustomerDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity Customer and its Avro  .
 */
@Mapper(componentModel = "spring", uses = {ContactAvroMapper.class})
public interface CustomerAvroMapper extends AvroMapper<Customer, com.diviso.graeshoppe.avro.CustomerInfo> {

    //@Mapping(source = "contact.id", target = "contact")
    com.diviso.graeshoppe.avro.CustomerInfo toAvro(com.diviso.graeshoppe.domain.Customer customer);


}
