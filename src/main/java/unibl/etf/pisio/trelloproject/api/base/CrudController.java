package unibl.etf.pisio.trelloproject.api.base;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import unibl.etf.pisio.trelloproject.core.base.ICrudService;
import unibl.etf.pisio.trelloproject.core.exceptions.NotFoundException;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@Getter
public abstract class CrudController<ID extends Serializable, REQ, RESP> {

    private final Class<RESP> responseClass;
    private final ICrudService<ID> crudService;

    protected CrudController(ICrudService<ID> crudService, Class<RESP> responseClass) {
        this.responseClass = responseClass;
        this.crudService = crudService;
    }

    @GetMapping
    public List<RESP> findAll(){
        return crudService.findAll(responseClass);
    }

    @GetMapping("/{id}")
    public RESP findById(@PathVariable ID id) throws NotFoundException {
        return crudService.findById(id, responseClass);
    }

    @DeleteMapping
    public void delete(@PathVariable ID id){
        crudService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RESP insert(@RequestBody @Valid REQ objectToInsert) throws NotFoundException{
        return crudService.insert(objectToInsert, responseClass);
    }

    @PutMapping("/{id}")
    public RESP update(@PathVariable ID id, @RequestBody @Valid REQ objectToUpdate){
        return crudService.update(id, objectToUpdate, responseClass);
    }
}
