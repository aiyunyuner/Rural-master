package com.TypeDelta.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

//@Component
public class LoginFilter extends ZuulFilter {
    @Value("${server.port}")
    private String server_port;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1; //负数的优先级最高
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //        获取请求上下文
        RequestContext ctx = RequestContext.getCurrentContext(); //有点像request域 zuul整个流程都有这个域
        HttpServletRequest request = ctx.getRequest();
//        获取请求的参数
        String token = request.getParameter("access-token");
//        判断是否存在
        if (StringUtils.isBlank(token)) {
//            不存在，未登录，拦截
            ctx.setSendZuulResponse(false); //默认是true false中断前行
            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());//返回403
        }
        System.out.println("现在是zuul："+server_port);
//        存在不做处理，存在
        return null;
    }
}
