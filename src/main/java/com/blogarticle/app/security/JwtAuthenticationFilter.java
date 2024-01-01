package com.blogarticle.app.security;

import com.blogarticle.app.exceptions.RequestDataValidationException;
import com.blogarticle.app.exceptions.ResourceNotFoundException;
import com.blogarticle.app.services.CustomUserDetailsService;
import com.blogarticle.app.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {

       String authorizationHeader = request.getHeader("Authorization");
       String token = null;
       String username = null;

       if(authorizationHeader != null && authorizationHeader.startsWith("Bearer"))
       {
           token = authorizationHeader.substring(7);
           try
           {
               username = this.jwtUtils.getUsernameFromToken(token);
           }
           catch(MalformedJwtException e)
           {
                //throw new RequestDataValidationException("JWT","jwt is malformed"+"---"+e.getMessage());
               System.out.println("jwt is malformed"+"---"+e.getMessage());
           }
           catch(ExpiredJwtException e)
           {
               //throw new RequestDataValidationException("JWT","jwt is expired"+"---"+e.getMessage());
               System.out.println("jwt is expired"+"---"+e.getMessage());
           }
           catch(Exception e)
           {
               //throw new RequestDataValidationException("JWT","jwt is not valid"+"---"+e.getMessage());
               System.out.println("jwt is not valid"+"---"+e.getMessage());

           }
       }
       else {
           //throw new RequestDataValidationException("JWT", "jwt token is missing");
           System.out.println("jwt token is missing");
       }

       if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
       {
           UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
           try
           {
               boolean isValid = this.jwtUtils.validateToken(token,userDetails);
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
           catch(Exception e)
           {
               //throw new RequestDataValidationException("JWT","jwt is invalid"+"---"+e.getMessage());
               System.out.println("jwt is invalid"+"---"+e.getMessage());
           }
       }
       else {
           //throw new RequestDataValidationException("JWT", "jwt token is invalid(username/context may be null)");
           System.out.println("jwt token is invalid(username/context may be null)");
       }

       filterChain.doFilter(request,response);

    }
}
