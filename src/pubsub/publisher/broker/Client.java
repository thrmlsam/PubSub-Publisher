/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pubsub.publisher.broker;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.IOException;
import pubsub.message.NetworkMessage;

/**
 *
 * @author thirumalaisamy
 */
public class Client {
    
    private int port;
    private String host;
    private Channel ch;
     EventLoopGroup group;
       Bootstrap b;
       ClientHandler handler;
    
    
    public Client(String host,int port){
        this.host = host;
        this.port = port;
    }
    
     public void connect() throws Exception{
            
        group = new NioEventLoopGroup();
        b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientInitializer());
        ch = b.connect(host, port).sync().channel();
        handler =
                ch.pipeline().get(ClientHandler.class);
        
    }
     
         
    public void send(NetworkMessage.Messages msg) throws IOException{
       
           handler.send(msg);
       
    }
    
}
