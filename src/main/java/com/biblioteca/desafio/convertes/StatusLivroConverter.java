package com.biblioteca.desafio.convertes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.biblioteca.desafio.entities.StatusLivro;

@Converter(autoApply = true)
public class StatusLivroConverter implements AttributeConverter<StatusLivro, Integer>{
	
	@Override
    public Integer convertToDatabaseColumn(StatusLivro attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute.getStatus();
    }

    @Override
    public StatusLivro convertToEntityAttribute(Integer status) {
        return StatusLivro.valueOf(status);
    }

}
