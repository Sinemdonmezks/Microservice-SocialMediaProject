package com.sinem.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sinem.exception.AuthServiceException;
import com.sinem.exception.ErrorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
   @Value("${myjwt.secretkey}")
    private String secretkey;
    @Value("${myjwt.audience}")
    private String audience;
    @Value("${myjwt.issuer}")

    private String issuer;

    public String createToken(Long id){
        String token=null;
        try{
         //   Algorithm algorithm= Algorithm.HMAC256(secretkey);
            token= JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+(1000*60)))
                    .withClaim("id",id)
                    .sign(Algorithm.HMAC256(secretkey));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return token;
    }
    public Optional<Long> getByIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretkey);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withAudience(audience)//Kitle
                    .withIssuer(issuer)//yayımcı
                    .build();
            DecodedJWT decodedToken = jwtVerifier.verify(token);//token geçerliliği doğrulanır
            if (decodedToken == null) throw new AuthServiceException(ErrorType.GECERSIZ_TOKEN);
            Long authid = decodedToken.getClaim("authid").asLong();
            return Optional.of(authid);
        } catch (Exception e) {
            throw new AuthServiceException(ErrorType.GECERSIZ_TOKEN);
        }
    }
}
