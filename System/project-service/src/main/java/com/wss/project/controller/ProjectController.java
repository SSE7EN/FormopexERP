package com.wss.project.controller;


import com.wss.common.exception.ResourceNotFoundException;
import com.wss.project.request.ProjectCreateRequestModel;
import com.wss.project.request.ProjectUpdateRequestModel;
import com.wss.project.service.IProjectService;
import com.wss.projectElementCommon.dto.ProjectDTO;
import com.wss.projectElementCommon.dto.ProjectWithElementsAndItemListsDTO;
import com.wss.projectElementCommon.dto.ProjectWithElementsDTO;
import com.wss.projectElementCommon.dto.ProjectWithItemListsDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * Project Controller
 *
 * @author se7en
 */
@RestController
@RequestMapping("/project")
public class ProjectController {


    @Qualifier("projectServiceImpl")
    @Autowired
    private IProjectService projectService;

    /**
     *
     * @param projectRequest
     * @return {@link ResponseEntity}
     */
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProjectDTO> insert(@Valid @RequestBody ProjectCreateRequestModel projectRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProjectDTO projectDTO = modelMapper.map(projectRequest, ProjectDTO.class);
        projectDTO = projectService.insert(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectDTO);
    }

    /**
     *
     * @param projectId
     * @return {@link ResponseEntity}
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/{projectId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProjectWithElementsDTO> getById(@PathVariable("projectId") String projectId) throws ResourceNotFoundException{
        ProjectWithElementsDTO projectDTO = projectService.<ProjectWithElementsDTO>findById(projectId, ProjectWithElementsDTO.class);
        if (projectDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(projectDTO);
        else throw new ResourceNotFoundException("project not found");
    }

    /**
     *
     * @param projectId
     * @return {@link ResponseEntity}
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/itemList/{projectId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProjectWithItemListsDTO> getByIdWithItemList(@PathVariable("projectId") String projectId) throws ResourceNotFoundException{
        ProjectWithItemListsDTO projectDTO = projectService.<ProjectWithItemListsDTO>findById(projectId, ProjectWithItemListsDTO.class);
        if (projectDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(projectDTO);
        else throw new ResourceNotFoundException("project not found");
    }

    /**
     *
     * @param projectId
     * @return {@link ResponseEntity}
     * @throws ResourceNotFoundException
     */
    @GetMapping(value = "/iLAE/{projectId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProjectWithElementsAndItemListsDTO> getByIdWILAE(@PathVariable("projectId") String projectId) throws ResourceNotFoundException{
        ProjectWithElementsAndItemListsDTO projectDTO = projectService.<ProjectWithElementsAndItemListsDTO>findById(projectId, ProjectWithElementsAndItemListsDTO.class);
        if (projectDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(projectDTO);
        else throw new ResourceNotFoundException("project not found");
    }


    /**
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<ProjectDTO>> getAll() {
        List<ProjectDTO> projectDTOList = projectService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(projectDTOList);
    }

    /**
     *
     * @param projectRequest
     * @return {@link ResponseEntity}
     */
    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProjectDTO> update(@Valid @RequestBody ProjectUpdateRequestModel projectRequest) {
        if(projectService.findById(projectRequest.getId(), ProjectWithElementsDTO.class) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProjectDTO projectDTO = modelMapper.map(projectRequest, ProjectDTO.class);

        projectDTO = projectService.update(projectDTO);
        return ResponseEntity.status(HttpStatus.OK).body(projectDTO);
    }

    /**
     * Delete a project
     * @return
     * @throws
     */
    @DeleteMapping(path = "/{projectId}")
    public ResponseEntity<Void> delete(@PathVariable String projectId) {
        if(projectService.findById(projectId, ProjectDTO.class) == null)
            return ResponseEntity.notFound().build();

        projectService.delete(projectId);
        return ResponseEntity.ok().build();
    }








}
