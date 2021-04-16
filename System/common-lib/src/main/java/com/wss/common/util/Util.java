package com.wss.common.util;

import com.wss.common.exception.FileCreateException;
import com.wss.documentationCommon.dto.UserMappingDTOWP;
import com.wss.documentationCommon.entity.UserMappingEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

import java.io.File;
import java.nio.file.Path;

public class Util {


    /**
     * @throws FileCreateException
     * @param path
     * @return if 1 ok if -1 failed
     */
    public static void createFile(String path){

        try {

            // returns pathnames for files and directory
            File file = new File(path);

            System.out.println("\n\n\n "+path+"\n\n\n");

            // create directories
            file.mkdirs();

        } catch(Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }

    public static ModelMapper modelMapperStrict(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PropertyMap<UserMappingEntity,UserMappingDTOWP> userMapping = new PropertyMap<UserMappingEntity, UserMappingDTOWP>() {
            protected void configure() {
                map().setProviderId(source.getProvider().getId());
            }
        };

        modelMapper.addMappings(userMapping);

        return modelMapper;
    }


}
