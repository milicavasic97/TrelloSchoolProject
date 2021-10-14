package unibl.etf.pisio.trelloproject.core.services;

import unibl.etf.pisio.trelloproject.api.models.requests.OrganisationRequest;
import unibl.etf.pisio.trelloproject.core.base.ICrudService;
import unibl.etf.pisio.trelloproject.core.models.dto.OrganisationDTO;

import java.util.List;

public interface IOrganisationService extends ICrudService<String> {
    List<OrganisationDTO> getMembersEmptyOrganisations(String memberId);

    OrganisationDTO insert(OrganisationRequest objectToInsert);
}
