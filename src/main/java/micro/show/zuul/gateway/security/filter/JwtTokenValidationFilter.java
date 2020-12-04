package micro.show.zuul.gateway.security.filter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenValidationFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 2;
    private static final boolean SHOULD_FILTER = false;
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenValidationFilter.class);

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

    public Object run() {
        logger.info(">>> JwtTokendValidationFilter at work...");
        return null;
    }
}
