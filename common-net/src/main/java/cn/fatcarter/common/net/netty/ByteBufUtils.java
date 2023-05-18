package cn.fatcarter.common.net.netty;

import io.netty.buffer.ByteBuf;

public class ByteBufUtils {
    public static byte[] readBytes(ByteBuf buf, int len) {
        int readable = buf.readableBytes();
        if (readable < len) {
            throw new IllegalStateException(String.format("readableBytes is %d less then required len %d", readable, len));
        }
        byte[] bytes = new byte[len];
        buf.readBytes(bytes);
        return bytes;
    }
}
