package micro.show.zuul.gateway.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthCheckFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = false;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationManager.class);

    @Autowired
    private FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isAuthorizationHeaderPresent() {
        if(filterUtils.getAuthorizationHeader() == null) {
            return false;
        }
        return true;
    }

    public Object run() {
        if (isAuthenticationRequest()) {
            if (isAuthorizationHeaderPresent()) {
                logger.info(">>> Authorization header is set");
            } else {
                logger.info(">>> Authorization header is missing, return 401!");
                setFailedRequest("401 Error", 401);
            }
        }
        return null;
    }

    private void setFailedRequest(String body, int code) {
        logger.debug(">>> Reporting error ({}): {}", code, body);
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.setResponseStatusCode(code);
        requestContext.setResponseBody(body);
        requestContext.setSendZuulResponse(false);
    }

    private boolean isAuthenticationRequest() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        return request.getRequestURI().equals("/api/auth/authenticate");
    }

}
