package com.sprint.mission.discodeit.db.common;

import java.util.Optional;

public interface CrudRepository<T, ID> extends Repository<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    Iterable<T> findAll();

    int count();

    void deleteById(ID id);

    boolean isExistsById(ID id);
}
