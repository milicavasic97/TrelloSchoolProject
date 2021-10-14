package unibl.etf.pisio.trelloproject.core.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import unibl.etf.pisio.trelloproject.api.models.requests.OrganisationInvitationRequest;
import unibl.etf.pisio.trelloproject.core.models.entities.OrganisationInvitationEntity;

import java.util.List;

public interface OrganisationInvitationEntityRepository
        extends JpaRepository<OrganisationInvitationEntity, String> {

    OrganisationInvitationEntity findByOrganisation_IdAndMember_Email(String idOrganisation, String email);

    List<OrganisationInvitationEntity> findAllByMember_Id(String idMember);

    List<OrganisationInvitationEntity> findAllByOrganisation_Id(String idOrganisation);
}
