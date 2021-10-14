package unibl.etf.pisio.trelloproject.core.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import unibl.etf.pisio.trelloproject.api.models.requests.OrganisationRequest;
import unibl.etf.pisio.trelloproject.core.base.CrudJpaService;
import unibl.etf.pisio.trelloproject.core.exceptions.ConflictException;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationDTO;
import unibl.etf.pisio.trelloproject.core.models.entities.OrganisationEntity;
import unibl.etf.pisio.trelloproject.core.models.repositories.OrganisationEntityRepository;
import unibl.etf.pisio.trelloproject.core.services.IOrganisationService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    public OrganisationDTO insert(OrganisationRequest objectToInsert) {
        String newName = objectToInsert.getDisplayName();
        newName = newName.toLowerCase();
        newName = newName.replaceAll("[^a-z0-9_]", "_");
        objectToInsert.setName(newName);
        while (repository.existsByName(modelMapper.map(objectToInsert, getEntityClass()).getName())) {
            newName += "1";
            objectToInsert.setName(newName);
        }
        return super.insert(objectToInsert, OrganisationDTO.class);
    }

    @Override
    public <T, U> T update(String id, U objectToInsert, Class<T> resultDtoClass) {
        if (repository.existsByNameAndIdNot(modelMapper.map(objectToInsert, getEntityClass()).getName(), id))
            throw new ConflictException();
        return super.update(id, objectToInsert, resultDtoClass);
    }

    @Override
    public List<OrganisationDTO> getMembersEmptyOrganisations(String memberId) {
        List<OrganisationEntity> entities = repository.getAllByCreatedBy_Id(memberId);
        return entities.stream()
//                .filter(o -> o.getBoards().isEmpty())
                .map(e -> modelMapper.map(e, OrganisationDTO.class))
                .collect(Collectors.toList());
    }
}
