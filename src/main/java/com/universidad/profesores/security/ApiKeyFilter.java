package com.universidad.profesores.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Se ejecuta ANTES de llegar al controlador
@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${app.security.api-key}")
    private String apiKeyValida;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String apiKey = request.getHeader("X-API-KEY");

        if (apiKey != null && apiKey.equals(apiKeyValida)) {
            filterChain.doFilter(request, response);   // deja pasar
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);   // 401
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":401,\"mensaje\":\"API Key invalida o ausente\"}");
        }
    }
}
