package com.niit.userauthservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTTockenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        ServletOutputStream sos = httpServletResponse.getOutputStream();
        String authHeader = httpServletRequest.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer")){
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            sos.print("Invalid Or Missing Tocken");
            sos.close();
        }else{
            String jwtTocken = authHeader.substring(7);
            Claims claims = Jwts.parser().setSigningKey("userKey").parseClaimsJws(jwtTocken).getBody();
            httpServletRequest.setAttribute("username",claims);
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
