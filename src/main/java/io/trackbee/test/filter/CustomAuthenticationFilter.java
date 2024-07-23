package io.trackbee.test.filter;

import io.trackbee.test.domain.Store;
import io.trackbee.test.service.StoreService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Order(1)
public class CustomAuthenticationFilter implements Filter {
    private final StoreService storeService;

    /**
     * Checks if the request contains a single `X-Authorization` header and checks it with the given store ID
     * If they are equal continue with request if not equal return UNAUTHORIZED
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Enumeration<String> headers = ((RequestFacade) servletRequest).getHeaders("X-Authorization");

        // No X-Authorization header
        if (!headers.hasMoreElements()) {
            ((HttpServletResponse) servletResponse).sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        String firstHeaderValue = headers.nextElement();

        // Multiple X-Authorization header
        if (headers.hasMoreElements()) {
            ((HttpServletResponse) servletResponse).sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        //TODO: handle this correct instead of one line
        String storeId = ((RequestFacade) servletRequest).getServletPath().split("/")[2];

        Optional<Store> store = storeService.retrieveStore(Long.valueOf(storeId));
        if (store.isEmpty()) {
            ((HttpServletResponse) servletResponse).sendError(HttpStatus.NOT_FOUND.value());
            return;
        }

        if (!Objects.equals(store.get().getApiKey(), firstHeaderValue)) {
            ((HttpServletResponse) servletResponse).sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
