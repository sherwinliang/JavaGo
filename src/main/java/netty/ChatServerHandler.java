package netty;

import common.User;
import design.mode.UserMapSingleton;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    /* @description: To handle a new channel
     * @author: Sherwin Liang
     * @timestamp: 2020/12/27 12:06
    */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel inComing = ctx.channel();
        inComing.writeAndFlush("Please enter your name which end with ## : \n");
    }

    /* @description: When user leaved
     * @author: Sherwin Liang
     * @timestamp: 2021/1/3 1:09
    */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel outComing = ctx.channel();
        String userId = outComing.id().asShortText();
        //Tell others he/she leaves the chat room
        for (Channel channel : channels){
            if (channel != outComing)
                channel.writeAndFlush( UserMapSingleton.getUserById(userId).getName() + " leaves this chat room.\n");
        }
        //Remove user in memory
        UserMapSingleton.removeUserById(userId);
        channels.remove(outComing);
    }

    /* @description: Read msg
     * @author: Sherwin Liang
     * @timestamp: 2021/1/3 1:09
    */
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel inComing = channelHandlerContext.channel();
        String channelId = inComing.id().asShortText();
        User user = UserMapSingleton.getUserById(channelId);
        if(user!=null){//User is in chat room, broadcast his/her msg
            for (Channel channel : channels){
                if (channel != inComing){
                    channel.writeAndFlush(user.getName() + ": " + s + "\n");
                }else {
                    channel.writeAndFlush("I: " + s + "\n");
                }
            }
        }else{//This is a new user. Store the username.
            if(s!=null && s.endsWith("##")){
                String userName = s.replaceAll("#", "");
                if(UserMapSingleton.getUserByName(userName)!=null){
                    inComing.writeAndFlush("This name is existed, please enter again.\n");
                }else{
                    UserMapSingleton.putUser(new User(channelId, userName, inComing.localAddress().toString()));
                    channels.add(inComing);
                    for (Channel channel : channels){
                        channel.writeAndFlush("Welcome " + userName + " to join in this chat room!\n");
                    }
                }
            }else{
                inComing.writeAndFlush("Input is invalid, please enter again.\n");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel inComing = ctx.channel();
        channels.remove(inComing);
        UserMapSingleton.removeUserById(inComing.id().asShortText());
        ctx.close();
    }
}
