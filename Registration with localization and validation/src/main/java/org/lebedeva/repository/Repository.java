package org.lebedeva.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    ID save(T data);

    List<T> findAll();

    Optional<T> findById(ID id);

    boolean userExists(String data);
}
