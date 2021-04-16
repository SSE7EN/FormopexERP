package com.wss.documentationTemplate.entity.util;

import com.wss.documentationTemplate.entity.EItemDocumentationType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ItemDocumentationTypeConverter implements AttributeConverter<EItemDocumentationType, String> {
    @Override
    public String convertToDatabaseColumn(EItemDocumentationType eItemDocumentationType) {
        if(eItemDocumentationType ==null){
            return null;
        }
        return eItemDocumentationType.getCode();
    }

    @Override
    public EItemDocumentationType convertToEntityAttribute(String code) {
        if(code == null)
            return null;
        return Stream.of(EItemDocumentationType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
