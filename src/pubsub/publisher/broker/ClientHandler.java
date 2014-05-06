/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pubsub.publisher.broker;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import pubsub.message.NetworkMessage.Messages;
import pubsub.publisher.Login;


class ClientHandler extends SimpleChannelInboundHandler<Messages> {

    private static final Logger logger = Logger.getLogger(
            ClientHandler.class.getName());

    private static final Pattern DELIM = Pattern.compile("/");
    // Stateful properties
    private volatile Channel channel;

    public ClientHandler() {
        super(false);
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
        System.out.println("hello");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.", cause);
        ctx.close();
    }

    

    

    @Override
    protected void channelRead0(ChannelHandlerContext chc, Messages msg) throws Exception {
        
        
            System.out.println("message received");

            if(msg.getMessageType() == Messages.MessageType.LOGIN){
            
                Login.reply = msg;
                System.out.println(msg.getMessage());
            }
            
            
       

   }

    boolean send(Messages msg) {
        
        System.out.println(channel.isOpen() + "-" + channel.isWritable() + channel.remoteAddress());
        ChannelFuture write = channel.writeAndFlush(msg);
        return write.isSuccess();
    }

    void read() {
        channel.read();
    }
}
