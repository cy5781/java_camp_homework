package org.ming.nio.gateway.inbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;

public class HttpInboundHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        // 获取请求的uri
        String uri = req.uri();

        String msg = "<html><head><title>DEMO</title></head><body>你请求uri为：" + uri + "</body></html>";
        // 创建http响应
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        // 设置头信息
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        //response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        // 将html write到客户端
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest req = (FullHttpRequest) msg;
        System.out.println(req.uri());
        boolean flag = checkUrl(ctx, req);
        if (flag) {
            System.out.println("接受到有效的请求");
            ctx.fireChannelRead(req);
            // channelRead0(ctx,(FullHttpRequest) msg);
            printResponse(ctx,req,"接受到有效的请求");

        } else {
            System.out.println("接受到无效的请求");
            // channelRead0(ctx,(FullHttpRequest) msg);
            printResponse(ctx,req,"接受到无效的请求");
            return;
        }
    }

    private boolean checkUrl(ChannelHandlerContext ctx, FullHttpRequest req) {

        List<String> validUrlList = new ArrayList<>();
        validUrlList.add("/hello");
        validUrlList.add("/good");
        validUrlList.add("/test");

        if (validUrlList.contains(req.uri())) {
            return true;
        } else {
            return false;
        }
    }

    private void printResponse(ChannelHandlerContext ctx, FullHttpRequest req, String requestType) {
        String uri = req.uri();

        String resp = "<html><head><title>DEMO</title></head><body>你请求uri为：" + uri + "</body></html>" + requestType;
        // 创建http响应
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(resp, CharsetUtil.UTF_8));
        // 设置头信息
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        //response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        // 将html write到客户端
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }


}
