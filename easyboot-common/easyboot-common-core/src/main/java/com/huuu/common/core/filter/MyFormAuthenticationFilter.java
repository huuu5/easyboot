package com.huuu.common.core.filter;

import com.huuu.base.result.Result;
import com.huuu.base.result.ResultCode;
import com.huuu.common.core.security.session.ShiroRedisSessionManager;
import com.huuu.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author huuu
 */
@Slf4j
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        if ("OPTIONS".equals(httpServletRequest.getMethod())) {
            return true;
        }
        String token = httpServletRequest.getHeader(ShiroRedisSessionManager.TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            return false;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        response.reset();
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(GsonUtils.toJson(Result.error(ResultCode.NEED_LOGIN)));
    }
}
