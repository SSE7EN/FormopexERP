package com.wss.documentationCommon.entity.util;

import com.wss.documentationCommon.entity.EDocumentationStatus;
import com.wss.documentationCommon.entity.EOrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<EOrderStatus, String> {

    @Override
    public String convertToDatabaseColumn(EOrderStatus eOrderStatus) {
        if(eOrderStatus == null)
            return null;
        return eOrderStatus.getCode();
    }

    @Override
    public EOrderStatus convertToEntityAttribute(String code) {
        if(code == null)
            return null;
        return Stream.of(EOrderStatus.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
