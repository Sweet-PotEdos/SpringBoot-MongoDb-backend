package com.wirail.DBTest.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

//import com.auth0.jwt.interfaces.Claim;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    //256 bit key, wich is the minimum for JWT token
    private static final String SECRET_KEY = "XIZLSGnJcv+3yUoVg1XiWngCnF7HES0aGROgg+mS7SU7RsZnDnZUHhLuOMtWazD57hp/iv14tmzHWYCSutHtPkzJEHWq58etTTHyjXS8CSZq33YzyqHgZn+BgyLQPC5OJ3lEgGH/hM46RvnP9LjbXzhSMalL308eAon3yC2L2mPar0nERVPaO4QV2HudJ0ZOyUcrylj/1XRxuoiGmFbgDQSu8b1JJYXT2touP0rbxHjXFJOETVRAgnznsewH3LcssMmOLo5CoC4Bfv2+Y2mVOij40F2+GY1oGFRz+5nSrpqLWaomoqyr2aFVOzL2FoBkSfrmWAzHWo7Q15Wul6XgLKmSU2UNx1dk0UF3oykPD4A";

    //claims in JWT are encoded in a JSON object, wich is structured like: 
    //Header(alogrithm and token type) - Payload(data) - VerifySignature
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //with this method extracting claims from tokens will be easy
    public<T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact(); //.compact is the one that will generate the token
    }

    private Claims extractAllClaims(String token){
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey()) //signing key is used to verify that the sender is who it claims to be and not someone else! Signing key is used in conjunction with signing algorithm
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    //down here was just Key and not SecretKey. We will see how it plays out
    private SecretKey getSignInKey(){

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
