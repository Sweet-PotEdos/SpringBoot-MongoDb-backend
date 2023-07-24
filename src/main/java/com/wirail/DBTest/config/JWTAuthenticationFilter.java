package com.wirail.DBTest.config;

import java.io.IOException;



import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    //we have to implement a Bean of type UserDetailsService so that we can fetch users from our own dbs
    private final UserDetailsService userDetailsService;

    public JWTAuthenticationFilter(UserDetailsService userDetailsService, JwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;

        //This is for setting the URL login path if you remove this line the endpoint will be "/login"
        //setFilterProcessesUrl("/api/services/controller/user/login"); 
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // we can intercept requests and responses here, the filter chain is the list of filters responsable to check our requests/responses
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if(authHeader == null || !authHeader.startsWith(("Bearer "))){
            filterChain.doFilter(request, response);
            return;
        }
        //we take the substring 7 wich is the lenght of "Bearer " (counting the space too)
        jwt = authHeader.substring(7);

        //extract username from JWT token
        username = jwtService.extractUsername(jwt);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if ( jwtService.isTokenValid(jwt, userDetails)){
                //if our token is valid we have to update our securety context and send the request to our dispatcher
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null, 
                    userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
            
    }
}
