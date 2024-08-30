package tn.bna_backend.service;

import tn.bna_backend.domain.User;
import tn.bna_backend.dto.UserDTO;

public interface UserService {
    UserDTO createUser(User user);
    UserDTO getUserByEmail(String email);

}
