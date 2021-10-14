package unibl.etf.pisio.trelloproject.core.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${trello.http.auth-token-header-name}")
    private String apiKeyHeaderName;
    @Value("${trello.http.auth-token}")
    private String apiKeyValue;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String apiKey = httpServletRequest.getHeader(apiKeyHeaderName) == null ? "" : httpServletRequest.getHeader(apiKeyHeaderName);

        if(apiKey.equals(apiKeyValue)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            String errorMessage = "Invalid API Key";

            httpServletResponse.reset();
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setContentLength(errorMessage.length());
            httpServletResponse.getWriter().write(errorMessage);
        }
    }
}
