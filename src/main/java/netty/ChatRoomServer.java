package netty;

import design.mode.UserMapSingleton;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatRoomServer {

    private ServerBootstrap serverBootstrap = null;
    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;
    private int port = 8080;
    private static final Logger logger = LogManager.getLogger(ChatRoomServer.class);

    private ChatRoomServer(Builder builder){
        this.serverBootstrap = builder.serverBootstrap;
        this.bossGroup = builder.bossGroup;
        this.workerGroup = builder.workerGroup;
        this.port = builder.port;
    }

    public void serverStart(){
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //The delimiter is used to deal with the sticky packet problem.
                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(2048, Delimiters.lineDelimiter()))
                                .addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new ChatServerHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_KEEPALIVE,true);
        try {
            ChannelFuture future = serverBootstrap.bind(port).sync();
            logger.error("Server is starting...");
            future.channel().closeFuture().sync();
            logger.error("Server is shutting down...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static class Builder{
        private static ServerBootstrap serverBootstrap = null;
        private static EventLoopGroup bossGroup = null;
        private static EventLoopGroup workerGroup = null;
        private static int port = 8080;

        public Builder serverBootstrap(ServerBootstrap serverBootstrap){
            this.serverBootstrap = serverBootstrap;
            return this;
        }

        public Builder bossGroup(EventLoopGroup bossGroup){
            this.bossGroup = bossGroup;
            return this;
        }

        public Builder workerGroup(EventLoopGroup workerGroup){
            this.workerGroup = workerGroup;
            return this;
        }

        public Builder port(int port){
            this.port = port;
            return this;
        }

        public ChatRoomServer build(){
            UserMapSingleton.init();
            return new ChatRoomServer(this);
        }
    }
}
