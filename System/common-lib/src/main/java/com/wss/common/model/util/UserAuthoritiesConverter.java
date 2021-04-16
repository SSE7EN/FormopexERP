package com.wss.common.model.util;

import com.wss.common.model.EUserPermission;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserAuthoritiesConverter implements AttributeConverter<EUserPermission, String> {

    @Override
    public String convertToDatabaseColumn(EUserPermission eUserPermissions) {
        if(eUserPermissions == null)
            return null;
        return eUserPermissions.getCode();
    }

    @Override
    public EUserPermission convertToEntityAttribute(String code) {
        if(code == null)
            return null;
        return Stream.of(EUserPermission.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

}
