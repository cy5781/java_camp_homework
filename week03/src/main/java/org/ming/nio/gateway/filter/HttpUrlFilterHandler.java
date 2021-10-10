package org.ming.nio.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.catalina.authenticator.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HttpUrlFilterHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest req = (FullHttpRequest)msg;
        System.out.println(req.uri());
        boolean flag = checkUrl(ctx,req);
        if(flag) {
            System.out.println("接受到有效的请求");
            ctx.fireChannelRead(req);
        }else {
            System.out.println("接受到无效的请求");
           // HttpUtil.send(ctx, "无效的请求", HttpResponseStatus.NOT_FOUND);
            return;
        }
    }

    private boolean checkUrl(ChannelHandlerContext ctx, FullHttpRequest req) {

        List<String> validUrlList = new ArrayList<>();
        validUrlList.add("/hello");
        validUrlList.add("/good");
        validUrlList.add("/test");

        if(validUrlList.contains(req.uri())){
            return true;
        }
        else {
            return false;
        }
    }
}
