package loadbalancer;

import model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

public interface LoadBalancer {
    /**
     * 根据请求参数和可用服务列表, 选择一个服务进行调用
     * @param requestParams
     * @param serviceMetaInfoList
     * @return
     */
    ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList);
}
