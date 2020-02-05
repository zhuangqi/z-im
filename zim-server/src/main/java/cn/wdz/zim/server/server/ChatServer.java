package cn.wdz.zim.server.server;

import cn.wdz.zim.server.server.init.ChatServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * @author <a href="mailto:jhoncycn@gmail.com">Jhoncy</a>
 * @date 2020/2/4
 */
@Component
public class ChatServer {

    @Value("${server.chat.port:9002}")
    private int webSocketPort;

    private Logger logger = LoggerFactory.getLogger(ChatServer.class);

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workGroup = new NioEventLoopGroup();

    /**
     * 启动
     * @throws Exception
     */
    @PostConstruct
    public void start() throws Exception {
        logger.info("starting chat server ... Port: {}", webSocketPort);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.handler(new LoggingHandler(LogLevel.INFO));
            bootstrap.childHandler(new ChatServerInitializer());
            ChannelFuture future = bootstrap.bind().sync();
            if (future.isSuccess()) {
                logger.info("start chat server success!");
            }
        } finally {
            Runtime.getRuntime().addShutdownHook(new Thread(this::destroy));
        }
    }

    /**
     * 关闭
     */
    @PreDestroy
    public void destroy() {
        bossGroup.shutdownGracefully().syncUninterruptibly();
        workGroup.shutdownGracefully().syncUninterruptibly();
        logger.info("stop chat server success!");
    }
}
