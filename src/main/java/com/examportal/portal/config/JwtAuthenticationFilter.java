package com.examportal.portal.config;

import com.examportal.portal.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       final String requestTokenHeader =request.getHeader("Authorization");
       System.out.print(requestTokenHeader);
       String username=null;
       String jwtToken=null;

       if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")){
           // yes

           jwtToken = requestTokenHeader.substring(7);

           try {
               username = this.jwtUtils.extractUsername(jwtToken);
           }catch(ExpiredJwtException e)
           {
               e.printStackTrace();
               System.out.print(" jwt token has expired");
           }catch(Exception e)
           {
               e.printStackTrace();
               System.out.print("error");
           }
       }else{
           System.out.print("invalid token , not start with bearer String");
       }

       //validate token
        if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
           final UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
           if(this.jwtUtils.validateToken(jwtToken,userDetails))
           {
               // token is valid
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
        }else
        {
            System.out.print("token is not valid");
        }

        filterChain.doFilter(request,response);

    }
}
