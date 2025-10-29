package com.shubham.expense.config;

import com.shubham.expense.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String email = null;
        String jwt = null;

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                jwt = authHeader.substring(7);
                email = jwtUtil.extractEmail(jwt);
            }

            // ✅ If valid token and no authentication set yet
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                if (jwtUtil.validateToken(jwt, email)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    new User(email, "", Collections.emptyList()),
                                    null,
                                    Collections.emptyList()
                            );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (ExpiredJwtException e) {
            System.out.println("⚠️ JWT expired: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️ JWT error: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
