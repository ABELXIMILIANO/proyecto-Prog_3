package com.example.sportmode.services;

import com.example.sportmode.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public abstract class BaseServiceImpl<E,ID extends Serializable> implements BaseService<E,ID> {

    
    protected BaseRepository<E,ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }


    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try {
            List<E>entities = baseRepository.findAll();
            return entities;
        }catch(Exception e ){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception {
        try{
           Page<E> entities=baseRepository.findAll(pageable);
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try {
            Optional<E> entityOptional=baseRepository.findById(id);
            return entityOptional.get();
        }catch(Exception e ){
            throw new Exception(e.getMessage());

        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try {
            entity=baseRepository.save(entity);
            return entity;
        }catch( Exception e ){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try {
            Optional<E>entityOptional=baseRepository.findById(id);//en esta línea obtengo la entidad que quiero actualzar
            E entityUpdate=entityOptional.get(); //en esta linea guardo la entity en un objeto de tipo generico"E" llamado entityUpdate
            entityUpdate=baseRepository.save(entity);//en esta línea
            return entityUpdate;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try {
            baseRepository.deleteById(id);
            return true;
        }catch(Exception e  ){
            throw new Exception(e.getMessage());
        }
    }
}
