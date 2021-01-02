package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatRoomClient2 {
    private static final Bootstrap bootstrap = new Bootstrap();
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private static final int port = 8080;
    private static final String host = "localhost";
    private static final Logger logger = LogManager.getLogger(ChatRoomClient.class);

    private ChatRoomClient2(){
    }

    public static void main(String[] args){
        bootstrap.group(bossGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //The delimiter is used to deal with the sticky packet problem.
                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(2048, Delimiters.lineDelimiter()))
                                .addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new ChatClientHandler());
                    }
                });
        try {
            final ChannelFuture future = bootstrap.connect("localhost", port).sync();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String msg = br.readLine();
                if ("quit".equalsIgnoreCase(msg)){
                    break;
                }
                future.channel().writeAndFlush(msg+"\n");
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
        }
    }
}
