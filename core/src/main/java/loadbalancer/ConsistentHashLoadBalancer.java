package loadbalancer;

import model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ConsistentHashLoadBalancer implements LoadBalancer{

    /**
     * 一致性 Hash 环，存放虚拟节点
     */
    private final TreeMap<Integer, ServiceMetaInfo> virtualNodes = new TreeMap<>();


    /**
     * 虚拟节点数
     */
    private static final int VIRTUAL_NODE_NUM = 100;

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if (serviceMetaInfoList.isEmpty()) {
            return null;
        }
        //构建hash环, 将环上的一个hash值映射到一个具体的配置信息上
        for(ServiceMetaInfo serviceMetaInfo:serviceMetaInfoList){
            for(int i=0;i<VIRTUAL_NODE_NUM;i++){
                int hash = getHash(serviceMetaInfo.getServiceAddress()+"#"+i);
                virtualNodes.put(hash,serviceMetaInfo);
            }
        }

        int hash = getHash(requestParams);

        Map.Entry<Integer,ServiceMetaInfo> entry= virtualNodes.ceilingEntry(hash);
        if(entry==null){
            entry = virtualNodes.firstEntry();
        }
        return entry.getValue();
    }

    private int getHash(Object key) {
        return key.hashCode();
    }

}
