package cn.odyssey.reggie.filter;

import cn.odyssey.reggie.common.BaseContext;
import cn.odyssey.reggie.common.R;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    // 路径匹配
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest; // 强制转换
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取本次请求的URI
        String requestURI = request.getRequestURI();

        //定义不需要处理的请求
        String[] urls = {"/employee/login", "/employee/logout", "/backend/**", "/front/**","/common/**"};

        //2.判断本次请求是否需要处理
        if (check(urls, requestURI)) {
            log.info("本次请求的页面不需要权限访问，无需处理……");
            filterChain.doFilter(request, response);
            return;
        }

        //3.判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("employee") != null) {
            log.info("本次请求的页面需要权限访问，同时用户已经登陆了，用户id为{}，放行……", request.getSession().getAttribute("employee"));

            log.info("filter的线程id是{}", Thread.currentThread().getId());
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request, response);
            return;
        }

        //4.是需要拦截处理的页面，且用户未登录
        log.info("本次请求的页面需要权限访问，但是用户还没有登陆，拦截！！！");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    public boolean check(String[] urls, String requestURL) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURL);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
