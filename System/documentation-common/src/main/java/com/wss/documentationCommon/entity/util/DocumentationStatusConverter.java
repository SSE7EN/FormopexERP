package com.wss.documentationCommon.entity.util;

import com.wss.documentationCommon.entity.EDocumentationStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * Converter for EDocumentationStatus
 *
 * @author se7en
 */
@Converter(autoApply = true)
public class DocumentationStatusConverter implements AttributeConverter<EDocumentationStatus, String> {
    @Override
    public String convertToDatabaseColumn(EDocumentationStatus eDocumentationStatus) {
        if(eDocumentationStatus == null)
            return null;
        return eDocumentationStatus.getCode();
    }

    @Override
    public EDocumentationStatus convertToEntityAttribute(String code) {
        if(code == null)
            return null;
        return Stream.of(EDocumentationStatus.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
