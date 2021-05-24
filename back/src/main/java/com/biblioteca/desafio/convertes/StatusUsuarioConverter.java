package com.biblioteca.desafio.convertes;

import javax.persistence.AttributeConverter;

import com.biblioteca.desafio.entities.StatusUsuario;

public class StatusUsuarioConverter implements AttributeConverter<StatusUsuario, Integer>{
	
	@Override
    public Integer convertToDatabaseColumn(StatusUsuario attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute.getStatus();
    }

    @Override
    public StatusUsuario convertToEntityAttribute(Integer status) {
        return StatusUsuario.valueOf(status);
    }

}
