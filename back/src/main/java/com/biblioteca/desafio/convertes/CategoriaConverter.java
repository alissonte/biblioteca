package com.biblioteca.desafio.convertes;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.biblioteca.desafio.entities.Categoria;

@Converter(autoApply = true)
public class CategoriaConverter implements AttributeConverter<Categoria, Integer>{
	
	@Override
    public Integer convertToDatabaseColumn(Categoria attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute.getCodigo();
    }

    @Override
    public Categoria convertToEntityAttribute(Integer status) {
        return Categoria.valueOf(status);
    }

}
