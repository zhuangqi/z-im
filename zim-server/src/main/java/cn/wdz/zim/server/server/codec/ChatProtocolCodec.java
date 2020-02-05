package cn.wdz.zim.server.server.codec;

import cn.wdz.zim.server.model.ProtocolModel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @author <a href="mailto:jhoncycn@gmail.com">Jhoncy</a>
 * @date 2020/2/5
 */
@ChannelHandler.Sharable
public class ChatProtocolCodec extends MessageToMessageCodec<ByteBuf, ProtocolModel> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
                          ProtocolModel protocolModel, List<Object> list) throws Exception {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        if (protocolModel.getBody() != null) {
            byteBuf.writeInt(ProtocolModel.PROTOCOL_HEADER_LENGTH + protocolModel.getBody().length);
            byteBuf.writeShort(protocolModel.getVersion());
            byteBuf.writeInt(protocolModel.getCommandId());
            byteBuf.writeLong(protocolModel.getRequestId());
            byteBuf.writeBytes(protocolModel.getBody());
        } else {
            byteBuf.writeInt(ProtocolModel.PROTOCOL_HEADER_LENGTH);
            byteBuf.writeShort(protocolModel.getVersion());
            byteBuf.writeInt(protocolModel.getCommandId());
            byteBuf.writeLong(protocolModel.getRequestId());
        }
        list.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {
        ProtocolModel protocolModel = new ProtocolModel();
        protocolModel.setPacketLen(byteBuf.readInt());
        protocolModel.setVersion(byteBuf.readShort());
        protocolModel.setCommandId(byteBuf.readInt());
        protocolModel.setRequestId(byteBuf.readLong());
        if (protocolModel.getPacketLen() > ProtocolModel.PROTOCOL_HEADER_LENGTH) {
            byte[] bytes = new byte[protocolModel.getPacketLen() - ProtocolModel.PROTOCOL_HEADER_LENGTH];
            byteBuf.readBytes(bytes);
            protocolModel.setBody(bytes);
        }

        list.add(protocolModel);
    }
}
