package com.diviso.graeshoppe.service.mapper;

import java.util.List;

/**
 * 
 *
 * @param <A> - Avro type parameter.
 * @param <E> - Entity type parameter.
 */


public interface AvroMapper<E, A> {


  //  E toEntity(A avr);

    A toAvro(E entity);

  //  List <E> toEntityList(List<A> avrList);

    //List <A> toAvroList(List<E> entityList);
	
}
