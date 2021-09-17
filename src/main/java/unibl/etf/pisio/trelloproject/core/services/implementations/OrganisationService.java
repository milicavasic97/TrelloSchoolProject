package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.exceptions.ConflictException;
import unibl.etf.pisio.trelloproject.core.models.entities.OrganisationEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.OrganisationEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.IOrganisationService;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrganisationService extends CrudJpaService<OrganisationEntity, String> implements IOrganisationService {
    private final ModelMapper modelMapper;
    private final OrganisationEntityRepository repository;

    public OrganisationService(ModelMapper _mapper, OrganisationEntityRepository _repository) {
        super(_repository, _mapper, OrganisationEntity.class);
        modelMapper = _mapper;
        repository = _repository;
    }

    @Override
    public <T, U> T insert(U objectToInsert, Class<T> resultDtoClass) {
        if (repository.existsByName(modelMapper.map(objectToInsert, getEntityClass()).getName()))
            throw new ConflictException();
        return super.insert(objectToInsert, resultDtoClass);
    }

    @Override
    public <T, U> T update(String id, U objectToInsert, Class<T> resultDtoClass) {
        if (repository.existsByNameAndIdNot(modelMapper.map(objectToInsert, getEntityClass()).getName(), id))
            throw new ConflictException();
        return super.update(id, objectToInsert, resultDtoClass);
    }
}
