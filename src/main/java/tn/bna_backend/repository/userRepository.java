package tn.bna_backend.repository;

import tn.bna_backend.domain.Role;
import tn.bna_backend.domain.User;


import java.util.Collection;
import java.util.List;

public interface userRepository <T extends User>{
    /* Basic CRUD Operations */
    User create(T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);
}
