package com.wss.project.service;

import com.wss.project.repository.IProjectRepository;
import com.wss.projectElementCommon.dto.ProjectDTO;
import com.wss.projectElementCommon.dto.ProjectWithElementsDTO;
import com.wss.projectElementCommon.dto.ProjectWithItemListsDTO;
import com.wss.projectElementCommon.entity.ProjectEntity;
import org.checkerframework.checker.units.qual.A;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
* Project service interface implementation
 *
 * @author se7en
 *
 */
@Transactional
@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private IProjectRepository repository;


    @Override
    public ProjectDTO insert(ProjectDTO projectDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ProjectEntity projectEntity = modelMapper.map(projectDTO,ProjectEntity.class);
        projectEntity.setId(UUID.randomUUID().toString());

        projectEntity = repository.save(projectEntity);
        projectDTO = modelMapper.map(projectEntity, ProjectDTO.class);

        return projectDTO;
    }

    @Override
    public ProjectDTO update(ProjectDTO projectDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ProjectEntity projectEntity = modelMapper.map(projectDTO, ProjectEntity.class);
        projectEntity = repository.save(projectEntity);
        projectDTO = modelMapper.map(projectEntity, ProjectDTO.class);

        return projectDTO;
    }

    @Override
    public List<ProjectDTO> getAll() {
        ArrayList<ProjectEntity> projects = (ArrayList<ProjectEntity>) repository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        List<ProjectDTO> projectDTOList = projects
                .stream()
                .map(projectEntity -> modelMapper.map(projectEntity, ProjectDTO.class))
                .collect(Collectors.toList());

        return projectDTOList;
    }

    @Override
    public <T> T findById(String id, Type type) {
        ProjectEntity projectEntity = repository.findById(id).orElse(null);
        if(projectEntity == null) return null;
        T projectDTO = new ModelMapper().map(projectEntity, type);

        return  projectDTO;
    }



    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
