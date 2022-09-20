package com.securityjwt.JwtAuthentication.JwtFilter;

import com.securityjwt.JwtAuthentication.Exception.GlobalException;
import com.securityjwt.JwtAuthentication.Service.CustomUserDetailsService;
import com.securityjwt.JwtAuthentication.Service.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired JwtUtilService jwtUtilService;
    @Autowired CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws GlobalException, ServletException, IOException {
        String parsingHeaderInfo=request.getHeader("Authorization");
        String token=null,userName=null;
        if(parsingHeaderInfo!=null && parsingHeaderInfo.startsWith("Bearer ")){
            token=parsingHeaderInfo.split(" ")[1];
            userName= jwtUtilService.extractUsername(token);
        }
        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=customUserDetailsService.loadUserByUsername(userName);
            if(jwtUtilService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
