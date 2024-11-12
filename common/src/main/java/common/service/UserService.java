package common.service;

import common.model.User;

public interface UserService {
    User getUser(User user);

    default short getNumber(){
        return 1;
    }
}
