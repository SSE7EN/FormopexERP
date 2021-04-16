package com.wss.documentation.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wss.common.util.Util;
import com.wss.documentationCommon.dto.*;
import com.wss.documentationCommon.entity.EDocumentationStatus;
import com.wss.documentationTemplate.dto.DocumentationTemplateDTO;
import com.wss.documentationTemplate.dto.ElementDocumentationTemplateDTO;
import com.wss.documentationTemplate.dto.ItemDocumentationTemplateDTO;
import com.wss.projectElementCommon.dto.ElementDTO;
import com.wss.projectElementCommon.dto.ItemDTO;
import com.wss.projectElementCommon.dto.ProjectWithElementsAndItemListsDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class MainServiceImpl implements IMainService {

    private final IDocumentationService documentationService;
    private final IItemMappingService itemMappingService;
    private final IItemDocumentService itemDocumentService;
    private final IElementMappingService elementMappingService;
    private final IElementDocumentService elementDocumentService;
    private final WebClient.Builder webClient;
    private final RestTemplate restTemplate;

    public MainServiceImpl(@Qualifier("documentationServiceImpl") IDocumentationService documentationService, @Qualifier("itemMappingServiceImpl") IItemMappingService itemMappingService,
                           @Qualifier("itemDocumentServiceImpl") IItemDocumentService itemDocumentService, @Qualifier("elementMappingServiceImpl") IElementMappingService elementMappingService, @Qualifier("elementDocumentServiceImpl") IElementDocumentService elementDocumentService, WebClient.Builder webClient, RestTemplate restTemplate) {
        this.documentationService = documentationService;
        this.itemMappingService = itemMappingService;
        this.itemDocumentService = itemDocumentService;
        this.elementMappingService = elementMappingService;
        this.elementDocumentService = elementDocumentService;
        this.webClient = webClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public DocumentationDTO generate(DocumentationDTO documentationDTO, String token) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DocumentationTemplateDTO documentationTemplateDTO = null;
        ProjectWithElementsAndItemListsDTO projectDTO = null;
        List<ItemDocumentDTO> itemDocumentDTOS = null;
        List<ElementDocumentDTO> elementDocumentDTOS = null;


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        //Get documentation template
        ResponseEntity<String> responseEntityDocumentationTemplateDTO =
                restTemplate.exchange(
                        "http://DOCUMENTATIONTEMPLATE-SERVICE/dTemplate/dTemplate/"+documentationDTO.getDocumentationTemplateId()
                        , HttpMethod.GET
                        , entity
                        , String.class);

        //Get projectDTO
        ResponseEntity<String> responseEntityProjectDTO =
                restTemplate.exchange(
                        "http://PROJECT-SERVICE/project/iLAE/"+documentationDTO.getProjectId()
                        , HttpMethod.GET
                        , entity
                        , String.class);

        try{
            documentationTemplateDTO =  new ObjectMapper().readValue(responseEntityDocumentationTemplateDTO.getBody(), DocumentationTemplateDTO.class);
            projectDTO =  new ObjectMapper().readValue(responseEntityProjectDTO.getBody(), ProjectWithElementsAndItemListsDTO.class);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if(documentationTemplateDTO != null && projectDTO != null){
            documentationDTO = createDocumentation(documentationDTO,projectDTO,documentationTemplateDTO);

        }


        return documentationDTO;
    }

    private DocumentationDTO createDocumentation(DocumentationDTO documentationDTO, ProjectWithElementsAndItemListsDTO projectDTO, DocumentationTemplateDTO documentationTemplateDTO){

        //Create folder
            documentationDTO = documentationService.insert(documentationDTO);
            documentationDTO.getDocuments().addAll(createElementsAndItemsDocuments(documentationDTO, documentationTemplateDTO, projectDTO));


        return documentationDTO;
    }

    private List<DocumentDTO> createElementItemsDocuments(DocumentationDTO documentationDTO, ElementMappingDTO elementMappingDTO, List<ItemDTO> itemDTOS, List<ItemDocumentationTemplateDTO> itemDocumentationTemplateDTOS){
        List<DocumentDTO> documentDTOS = new ArrayList<>();

        itemDTOS.forEach(itemDTO -> {
            ItemMappingDTO itemMappingDTO = createItemMapping(itemDTO, elementMappingDTO);
            itemDocumentationTemplateDTOS.forEach(itemDocumentationTemplateDTO -> {
                if(itemDTO.getItemType().getId().equals(itemDocumentationTemplateDTO.getItemTypeId())){
                    documentDTOS.add(createItemDocument(itemDocumentationTemplateDTO,itemMappingDTO, documentationDTO));
                }
            });
        });


        return documentDTOS;
    }


    private List<DocumentDTO> createElementsAndItemsDocuments(DocumentationDTO documentationDTO,DocumentationTemplateDTO documentationTemplateDTO, ProjectWithElementsAndItemListsDTO projectDTO){
        List<DocumentDTO> documentDTOS = new ArrayList<>();

            projectDTO.getElements().forEach(elementDTO -> {
                ElementMappingDTO elementMappingDTO = createElementMapping(elementDTO, documentationDTO.getProjectId());
               //
                documentDTOS.addAll(createElementItemsDocuments(documentationDTO, elementMappingDTO, elementDTO.getItemList().getItems(), documentationTemplateDTO.getItemDocumentationTemplates()));
                documentationTemplateDTO.getElementDocumentationTemplates().forEach(elementDocumentationTemplateDTO -> {
                    if(elementDocumentationTemplateDTO.getElementTypeId().equals(elementDTO.getElementType().getId())){
                        documentDTOS.add(createElementDocument(elementDocumentationTemplateDTO,elementMappingDTO,documentationDTO));

                }
            });
        });

        return documentDTOS;

    }

    private ElementMappingDTO createElementMapping(ElementDTO elementDTO, String projectId){
        ElementMappingDTO elementMappingDTO = new ElementMappingDTO();
        elementMappingDTO.setElementId(elementDTO.getId());
        elementMappingDTO.setProjectId(projectId);
        elementMappingDTO.setName(elementDTO.getName()+"_"+elementDTO.getElementIndexNumber());
        elementMappingDTO = elementMappingService.insert(elementMappingDTO);

        return elementMappingDTO;
    }

    private ElementDocumentDTO createElementDocument(ElementDocumentationTemplateDTO elementDocumentationTemplateDTO, ElementMappingDTO elementMappingDTO, DocumentationDTO documentationDTO){
        ElementDocumentDTO elementDocumentDTO = new ElementDocumentDTO();
        elementDocumentDTO.getElementMappings().add(elementMappingDTO);
        elementDocumentDTO.setDocumentation(documentationDTO);
        elementDocumentDTO.setPath(documentationDTO.getId());
        elementDocumentDTO.setName(elementDocumentationTemplateDTO.getName());
        elementDocumentDTO.setStatus(EDocumentationStatus.INPROGRESS);

        elementDocumentDTO = elementDocumentService.insert(elementDocumentDTO);
        return elementDocumentDTO;
    }

    private ItemMappingDTO createItemMapping(ItemDTO itemDTO, ElementMappingDTO elementMappingDTO){
        ItemMappingDTO itemMappingDTO = new ItemMappingDTO();
        itemMappingDTO.setItemId(itemDTO.getId());
        itemMappingDTO.setName(itemDTO.getName());
        itemMappingDTO.setElementMapping(elementMappingDTO);
        itemMappingDTO = itemMappingService.insert(itemMappingDTO);
        return itemMappingDTO;
    }

    private ItemDocumentDTO createItemDocument(ItemDocumentationTemplateDTO itemDocumentationTemplateDTO, ItemMappingDTO itemMappingDTO, DocumentationDTO documentationDTO){
        ItemDocumentDTO itemDocumentDTO = new ItemDocumentDTO();
        itemDocumentDTO.getItemMappings().add(itemMappingDTO);
        itemDocumentDTO.setDocumentation(documentationDTO);
        itemDocumentDTO.setPath(documentationDTO.getId());
        itemDocumentDTO.setName(itemDocumentationTemplateDTO.getName());
        itemDocumentDTO.setStatus(EDocumentationStatus.INPROGRESS);

        itemDocumentDTO = itemDocumentService.insert(itemDocumentDTO,itemDocumentationTemplateDTO.getItemDocumentationType());

        return itemDocumentDTO;
    }


}
