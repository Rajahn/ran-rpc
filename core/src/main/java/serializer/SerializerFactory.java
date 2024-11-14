package serializer;

import org.codehaus.jackson.map.JsonSerializer;
import spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

public class SerializerFactory {
//    static {
//        SpiLoader.load(Serializer.class);
//    }
    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
//        SpiLoader.load(Serializer.class); // 延迟加载
//        return SpiLoader.getInstance(Serializer.class, key);
        return DEFAULT_SERIALIZER;
    }

}
