import common.model.User;
import common.service.UserService;
import config.RpcConfig;
import proxy.ServiceProxyFactory;
import utils.ConfigUtils;

public class ConsumerExample {
    public static void main(String[] args) {
//        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
//        System.out.println(rpc);
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("yupi");

        User newUser = userService.getUser(user);
        if(newUser!=null){
            System.out.println(newUser.getName());
        }else {
            System.out.println("null user");
        }
        System.out.println(userService.getNumber());
    }
}
