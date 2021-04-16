package com.wss.common.model.util;

import com.wss.common.model.EUserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<EUserRole, String> {

    @Override
    public String convertToDatabaseColumn(EUserRole eUserRole) {
        if(eUserRole == null)
            return null;
        return eUserRole.getCode();
    }

    @Override
    public EUserRole convertToEntityAttribute(String code) {
        if(code == null)
            return null;
        return Stream.of(EUserRole.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
