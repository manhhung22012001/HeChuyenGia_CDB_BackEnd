/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.security;
import org.springframework.stereotype.Component;
import com.Hechuyengia.Chuandoanbenh.details.CustomUserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import static java.lang.Math.log;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author tranm
 */

@Component
@Slf4j
public class JwtTokenProvider {

    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "lodaaaaaa";

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;

    // Tạo ra jwt từ thông tin user
    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
         // Tạo payload của token
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDetails.getUserEnity().getId_user());
        claims.put("username", userDetails.getUserEnity().getUsername());
        claims.put("fullname", userDetails.getUserEnity().getFullname());
        claims.put("phonenumber", userDetails.getUserEnity().getPhonenumber());
        claims.put("role", userDetails.getUserEnity().getRole());
        claims.put("status", userDetails.getUserEnity().getStatus());
        claims.put("email", userDetails.getUserEnity().getEmail());
        // Tạo chuỗi json web token từ id của user.
         return Jwts.builder()
                .setClaims(claims) // Thêm payload vào token
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // Lấy thông tin user từ jwt
    public String getUserNameFromJWT(String token) {
    Claims claims = Jwts.parser()
            .setSigningKey(JWT_SECRET)
            .parseClaimsJws(token)
            .getBody();

    return claims.get("username", String.class);
}

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
