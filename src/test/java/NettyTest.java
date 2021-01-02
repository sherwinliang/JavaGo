import design.mode.UserMapSingleton;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import netty.ChatRoomServer;
import org.junit.Test;

public class NettyTest {
    @Test
    public void serverStart(){
        UserMapSingleton.init();
        ChatRoomServer chatRoomServer = new ChatRoomServer.Builder().serverBootstrap(new ServerBootstrap())
                .bossGroup(new NioEventLoopGroup())
                .workerGroup(new NioEventLoopGroup())
                .port(8080).build();
        chatRoomServer.serverStart();
    }
}
