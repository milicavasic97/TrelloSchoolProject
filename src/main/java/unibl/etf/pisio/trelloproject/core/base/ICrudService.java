package unibl.etf.pisio.trelloproject.core.base;

import java.io.Serializable;
import java.util.List;

public interface ICrudService<ID extends Serializable> {
    <T, U> T insert(U objectToInsert, Class<T> resultDtoClass);

    <T> List<T> findAll(Class<T> resultDtoClass);

    <T> T findById(ID id, Class<T> resultDtoClass);

    <T, U> T update(ID id, U objectToUpdate, Class<T> resultDtoClass);

    void delete(ID id);
}
