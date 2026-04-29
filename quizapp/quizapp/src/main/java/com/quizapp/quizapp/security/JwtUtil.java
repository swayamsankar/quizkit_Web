package com.quizapp.quizapp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkey"; // 🔐 keep strong & secret

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // 🔐 Generate token (WITH ROLE)
    public static String generateToken(String username, String role) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // 🔥 role added
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔍 Extract username
    public static String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // 🔍 Extract role
    public static String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    // 🔐 Common claims method (clean code)
    private static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ Validate token
    public static boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username);
    }
}