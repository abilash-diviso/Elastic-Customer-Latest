package com.diviso.graeshoppe.service.mapper;

import com.diviso.graeshoppe.domain.*;
import com.diviso.graeshoppe.service.dto.CustomerDTO;
import com.diviso.graeshoppe.avro.Customer;

import org.mapstruct.*;

/**
 * Mapper for the entity Customer and its Avro  .
 */
@Mapper(componentModel = "spring", uses = {ContactAvroMapper.class})
public interface CustomerAvroMapper extends AvroMapper<Customer, com.diviso.graeshoppe.avro.Customer> {

    //@Mapping(source = "contact.id", target = "contact")
    com.diviso.graeshoppe.avro.Customer toAvro(com.diviso.graeshoppe.domain.Customer customer);


}
