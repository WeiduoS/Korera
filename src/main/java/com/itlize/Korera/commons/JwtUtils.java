package com.itlize.Korera.commons;

import com.itlize.Korera.entities.JWTRemeberToken;
import com.itlize.Korera.entities.Payload;
import com.itlize.Korera.entities.User;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.UUID;

/**
 * @author: Weiduo
 * generate token and valid the token
 */
public class JwtUtils {

    private static final String JWT_PAYLOAD_USER_KEY = "user";

    /**
     * private key encrypt token
     *
     * @param userInfo   payload data
     * @param privateKey private key
     * @param expire     expire time, minutes
     * @return JWT
     */
    public static JWTRemeberToken generateTokenExpireInMinutes(Object userInfo, PrivateKey privateKey, int expire) {

        String id = createJTI();

        String username = ((User)userInfo).getUsername();

        DateTime dateTime = DateTime.now();

        String tokenValue = Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
                .setId(id)
                .setExpiration(dateTime.plusMinutes(expire).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();

        JWTRemeberToken token = new JWTRemeberToken(username, id, tokenValue,
                new Timestamp(dateTime.toDateTime().getMillis()));

        return token;
    }

    /**
     * encrypt token
     *
     * @param userInfo   payload data
     * @param privateKey private key
     * @param expire     expire time, seconds
     * @return JWT
     */
    public static String generateTokenExpireInSeconds(Object userInfo, PrivateKey privateKey, int expire) {
        return Jwts.builder()
                .claim(JWT_PAYLOAD_USER_KEY, JsonUtils.toString(userInfo))
                .setId(createJTI())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    /**
     * parsing token
     *
     * @param token     user request token
     * @param publicKey public key
     * @return Jws<Claims>
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * get user info from token
     *
     * @param token     user request token
     * @param publicKey public key
     * @return user information
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey, Class<T> userType) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setUserInfo(JsonUtils.toBean(body.get(JWT_PAYLOAD_USER_KEY).toString(), userType));

        claims.setExpiration(body.getExpiration());
        return claims;
    }

    /**
     * get playload from token
     *
     * @param token     user request token
     * @param publicKey public key
     * @return user information
     */
    public static <T> Payload<T> getInfoFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Payload<T> claims = new Payload<>();
        claims.setId(body.getId());
        claims.setExpiration(body.getExpiration());
        return claims;
    }
}