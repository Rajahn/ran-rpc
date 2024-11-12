import common.model.User;
import common.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.printf("User name is: "+user.getName());
        return user;
    }
}
