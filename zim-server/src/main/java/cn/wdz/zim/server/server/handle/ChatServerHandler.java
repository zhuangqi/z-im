package cn.wdz.zim.server.server.handle;

import cn.wdz.zim.common.constant.Commond;
import cn.wdz.zim.server.model.ProtocolModel;
import cn.wdz.zim.server.server.utils.SessionSocketHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:jhoncycn@gmail.com">Jhoncy</a>
 * @date 2020/2/5
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<ProtocolModel> {

    private Logger logger = LoggerFactory.getLogger(ChatServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                ProtocolModel msg) throws Exception {
        logger.info("收到msg={}", msg.toString());
        if (Commond.LOGIN == msg.getCommandId()) {
            handleLogin(ctx, msg);
        }
    }


    private void handleLogin(ChannelHandlerContext ctx, ProtocolModel msg) {
        SessionSocketHolder.put(msg.getRequestId(), (NioSocketChannel)ctx.channel());
    }

}
