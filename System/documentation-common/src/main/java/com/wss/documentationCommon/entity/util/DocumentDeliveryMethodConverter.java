package com.wss.documentationCommon.entity.util;

import com.wss.documentationCommon.entity.EDocumentDeliveryMethod;
import com.wss.documentationCommon.entity.EDocumentationStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class DocumentDeliveryMethodConverter implements AttributeConverter<EDocumentDeliveryMethod, String> {
    @Override
    public String convertToDatabaseColumn(EDocumentDeliveryMethod eDocumentDeliveryMethod) {
        if(eDocumentDeliveryMethod == null)
            return null;
        return eDocumentDeliveryMethod.getCode();
    }

    @Override
    public EDocumentDeliveryMethod convertToEntityAttribute(String code) {
        if(code == null)
            return null;
        return Stream.of(EDocumentDeliveryMethod.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
