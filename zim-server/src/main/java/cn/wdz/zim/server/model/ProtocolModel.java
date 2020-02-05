package cn.wdz.zim.server.model;

import java.util.Arrays;

/**
 * @author <a href="mailto:jhoncycn@gmail.com">Jhoncy</a>
 * @date 2020/2/5
 */
public class ProtocolModel {

    /** 协议包长度 */
    public static final short PROTOCOL_HEADER_LENGTH = Integer.SIZE/8 + Short.SIZE/8 + Integer.SIZE/8 + Long.SIZE/8;

    public static final short CUR_VERSION = 1;

    // 协议包长
    private int packetLen;
    // 协议版本
    private short version;
    // 操作类型
    private int commandId;
    // 请求编号
    private long requestId;
    // 协议包体
    private byte[] body;

    public int getPacketLen() {
        return packetLen;
    }

    public void setPacketLen(int packetLen) {
        this.packetLen = packetLen;
    }

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ProtocolModel{" +
                "packetLen=" + packetLen +
                ", version=" + version +
                ", commandId=" + commandId +
                ", requestId=" + requestId +
                ", body=" + Arrays.toString(body) +
                '}';
    }
}
