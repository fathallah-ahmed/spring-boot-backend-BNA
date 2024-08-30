package tn.bna_backend.service.implementation;

import tn.bna_backend.dtomapper.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.bna_backend.domain.User;
import tn.bna_backend.dto.UserDTO;
import tn.bna_backend.service.UserService;
import tn.bna_backend.repository.userRepository;

@Service
@RequiredArgsConstructor
public class UserSrviceImpl  implements UserService {
    final userRepository <User>  userRepository;

    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(userRepository.create(user));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return UserDTOMapper.fromUser(userRepository.getUserByEmail(email));
    }

}
