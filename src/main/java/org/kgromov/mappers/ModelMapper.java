package org.kgromov.mappers;

import java.util.List;

public interface ModelMapper<T, ID> {

    List<T> findAll();

    T findById(ID id);

    int insert(T t);

    int update(T t);

    int delete(ID id);
}
