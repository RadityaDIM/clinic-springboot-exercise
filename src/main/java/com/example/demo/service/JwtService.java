// package com.example.demo.service;

// import java.security.Key;
// import java.sql.Date;

// import org.springframework.stereotype.Service;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.io.Decoders;
// import io.jsonwebtoken.security.Keys;

// @Service
// public class JwtService {
// private static final String SECRET_KEY =
// "IniAdalahKunciRahasiaSangatPanjangYangDigunakanUntukSigningJWTBase64Encoded";

// public String generateToken(String username) {
// return Jwts.builder()
// .setSubject(username)
// .setIssuedAt(new Date(System.currentTimeMillis()))
// .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
// .signWith(getSignInKey(), SignatureAlgorithm.HS256)
// .compact();
// }

// public String extractUsername(String token) {
// return Jwts.parserBuilder()
// .setSigningKey(getSignInKey())
// .build()
// .parseClaimsJws(token)
// .getBody()
// .getSubject();
// }

// private Key getSignInKey() {
// byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
// return Keys.hmacShaKeyFor(keyBytes);
// }
// }
