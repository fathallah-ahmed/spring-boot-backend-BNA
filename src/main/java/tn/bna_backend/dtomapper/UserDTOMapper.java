package tn.bna_backend.dtomapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tn.bna_backend.dto.UserDTO;
import tn.bna_backend.domain.User;

@Component
public class UserDTOMapper {
    public static UserDTO fromUser(User user)
    {UserDTO UserDTO =new UserDTO();
        BeanUtils.copyProperties(user,UserDTO);
        return UserDTO;
    }

    public static User toUser(UserDTO userDTO)
    {User user =new User();
        BeanUtils.copyProperties(userDTO,user);
        return user;

}}
