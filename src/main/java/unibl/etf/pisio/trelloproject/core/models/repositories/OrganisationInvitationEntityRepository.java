package unibl.etf.pisio.trelloproject.core.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.core.models.entities.OrganisationInvitationEntity;

public interface OrganisationInvitationEntityRepository
        extends JpaRepository<OrganisationInvitationEntity, String> {
}
