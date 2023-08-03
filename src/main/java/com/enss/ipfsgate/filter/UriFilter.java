package com.enss.ipfsgate.filter;



import com.enss.ipfsgate.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * URI过滤器
 */
@WebFilter(filterName = "uriFilter",urlPatterns = {"/*"})
@Configuration
public class UriFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest; //获取 request;
        HttpServletResponse response = (HttpServletResponse) servletResponse;//获取 response;
        HttpSession session = request.getSession(false); //获取 session;
        //String token = request.getHeader("token");
        //设置跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,token");

        String uri = request.getRequestURI();
        boolean needFilter = isNeedFilter(uri);

        logger.info("URL:" + uri + " - is not need filter");
        //api接口放行
        filterChain.doFilter(request, response);
        //return;
    }

    @Override
    public void destroy() {

    }

    /**
     * @return true 需要 false 不需要
     * @author dell
     * @Description: 是否需要过滤
     * @DATE:2018年10月25日 上午11:57:08
     */
    public boolean isNeedFilter(String uri) {

        for (String includeUrl : Constant.includeUrls) {
            if (uri.contains(includeUrl)) {
                //System.out.println("then filter.....");
                return false;
            }
        }
        //System.out.println("not filter.....");
        return true;
    }
}
