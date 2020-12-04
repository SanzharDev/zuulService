package micro.show.zuul.gateway.security.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {

    public static final String PRE_FILTER_TYPE = "pre";

    public static final String AUTHORIZATION = "Authorization";

    public String getAuthorizationHeader() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        if (requestContext.getRequest().getHeader(AUTHORIZATION) != null) {
            return requestContext.getRequest().getHeader(AUTHORIZATION);
        }
        return null;
    }
}
