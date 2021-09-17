package unibl.etf.pisio.trelloproject.core.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.core.models.entities.OrganisationEntity;

public interface OrganisationEntityRepository extends JpaRepository<OrganisationEntity, String> {

    boolean existsById(String id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, String id);
}
