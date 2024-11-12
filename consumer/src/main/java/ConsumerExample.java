import common.model.User;
import common.service.UserService;
import proxy.ServiceProxyFactory;

public class ConsumerExample {
    public static void main(String[] args) {
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("yupi");

        User newUser = userService.getUser(user);
        if(newUser!=null){
            System.out.println(newUser.getName());
        }else {
            System.out.println("null user");
        }
    }
}
