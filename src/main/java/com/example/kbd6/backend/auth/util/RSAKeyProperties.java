package com.example.kbd6.backend.auth.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

@Component
@Data
public class RSAKeyProperties {
    private RSAPrivateCrtKey privateCrtKey;
    private RSAPublicKey publicKey;

    public RSAKeyProperties() {
        KeyPair pair = KeyGeneratorUtility.generateRsaKey();
        this.publicKey = (RSAPublicKey) pair.getPublic();
        this.privateCrtKey = (RSAPrivateCrtKey) pair.getPrivate();
    }
}
