package com.wss.common.service;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Entity service interface
 * @param <T> dto
 */
public interface IEntityService<T> {

    /**
     * Insert
     * @param dto
     * @return
     */
    public T insert(T dto);


    /**
     * Update
     * @param dto
     * @return
     */
    public T update(T dto);


    /**
     * Get all
     * @return
     */
    public List<T> getAll();


    /**
     * Find by id
     * @param id
     * @return
     */
    public T findById(String id);

    /**
     * Delete by id
     * @param id
     */
    public void delete(String id);


}
