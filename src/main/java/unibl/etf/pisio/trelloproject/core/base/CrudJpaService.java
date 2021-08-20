package unibl.etf.pisio.trelloproject.core.base;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.core.exceptions.NotFoundException;
import unibl.etf.pisio.trelloproject.core.util.IdGeneratorUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Getter
public class CrudJpaService<E extends BaseEntity<ID>, ID extends Serializable> implements ICrudService<ID> {

    private final JpaRepository<E, ID> repository;
    private final Class<E> entityClass;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public CrudJpaService(JpaRepository<E, ID> _repository, ModelMapper _modelMapper, Class<E> _entityClass) {
        this.repository = _repository;
        this.entityClass = _entityClass;
        this.modelMapper = _modelMapper;
    }

    @Override
    public <T, U> T insert(U objectToInsert, Class<T> resultDtoClass) {
        E entity = modelMapper.map(objectToInsert, entityClass);
        String id = IdGeneratorUtil.generateId();
        while (repository.existsById((ID)id))
            id = IdGeneratorUtil.generateId();
        entity.setId((ID)id);
        entity = repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return modelMapper.map(entity, resultDtoClass);
    }

    @Override
    public <T> List<T> findAll(Class<T> resultDtoClass) {
        return repository.findAll().stream().map(e -> modelMapper.map(e, resultDtoClass)).collect(Collectors.toList());
    }

    @Override
    public <T> T findById(ID id, Class<T> resultDtoClass) {
        return modelMapper.map(findEntityById(id), resultDtoClass);
    }

    public E findEntityById(ID id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public <T, U> T update(ID id, U objectToUpdate, Class<T> resultDtoClass) {
        if (!repository.existsById(id))
            throw new NotFoundException();
        E entity = modelMapper.map(objectToUpdate, entityClass);
        entity.setId(id);
        entity = repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return modelMapper.map(entity, resultDtoClass);
    }

    @Override
    public void delete(ID id) {
        if(!repository.existsById(id))
                throw new NotFoundException();
        repository.deleteById(id);
    }
}
