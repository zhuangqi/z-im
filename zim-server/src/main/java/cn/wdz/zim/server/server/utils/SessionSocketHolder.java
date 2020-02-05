package cn.wdz.zim.server.server.utils;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:jhoncycn@gmail.com">Jhoncy</a>
 * @date 2020/2/5
 */
public class SessionSocketHolder {

    private static final Map<Long, NioSocketChannel> CHANNEL_MAP = new ConcurrentHashMap<>(16);

    public static void put(Long id, NioSocketChannel socketChannel) {
        CHANNEL_MAP.put(id, socketChannel);
    }

    public static NioSocketChannel get(Long id) {
        return CHANNEL_MAP.get(id);
    }

}
