package org.metrodataacademy.finalproject.clientapp.utils;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;

public class BasicHeaderUtil {

    public static String createBasicToken(String username, String password) {
        String authentication = username + ":" + password;

        byte[] encodedAuthentication = Base64.encodeBase64(
                authentication.getBytes(Charset.forName("US-ASCII"))
        );

        return new String(encodedAuthentication);
    }

    public static HttpHeaders createBasicHeader() {
        Authentication authentication = AuthSessionUtil.getAuthentication();

        String token = createBasicToken(
                authentication.getPrincipal().toString(),
                authentication.getCredentials().toString()
        );

        return new HttpHeaders() {
            {set("Authorization", "Basic " + token);}
        };
    }
}