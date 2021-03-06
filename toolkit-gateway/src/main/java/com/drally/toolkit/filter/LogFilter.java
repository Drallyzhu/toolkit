package com.drally.toolkit.filter;

import cn.hutool.core.date.DateUtil;
import com.drally.toolkit.domain.gateway.log.LogModel;
import com.drally.toolkit.common.util.IPUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Enumeration;

/**
 * @ClassName LogFilter
 * @Description TODO
 * @Author Drally
 * @Date 2019/3/22 16:32
 **/
@Slf4j
@Component
public class LogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return  FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 2;
    }

    @Override
    public boolean shouldFilter() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        String auth = request.getHeader("Authorization");
//        if (StringUtils.isBlank(auth) || auth.contains("Basic")){
//            return false;
//        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        LogModel logModel = new LogModel();
        logModel.setIp(IPUtil.getIpAddr(request));
        logModel.setMethod(request.getMethod());
        logModel.setPath(request.getServletPath());
        logModel.setRequestTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
        ServletInputStream inp = null;
        try {
            Enumeration<String> names = request.getParameterNames();
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            while (names.hasMoreElements()) {
                String key = names.nextElement();
                sb.append("[").append(key).append("=").append(request.getParameter(key)).append("]");
            }
            sb.append("}");
            logModel.setParams(sb.toString());
            inp = request.getInputStream();
            String boby = IOUtils.toString(inp, Charset.forName("UTF-8"));
            logModel.setBody(boby);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert inp != null;
                inp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        InputStream out = null;
        try {
            out = ctx.getResponseDataStream();
            String outBody = StreamUtils.copyToString(out, Charset.forName("UTF-8"));
            ctx.setResponseBody(outBody);
            logModel.setOutBody(outBody);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info(logModel.toString());
        return null;
    }



}
