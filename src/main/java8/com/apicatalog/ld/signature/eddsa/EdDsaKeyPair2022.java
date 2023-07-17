package com.apicatalog.ld.signature.ed25519;

import java.net.URI;

import com.apicatalog.ld.signature.key.KeyPair;

public final class EdDsaKeyPair2022 extends EdDsaVerificationKey2022 implements KeyPair {

    private final byte[] privateKey;
    
    public EdDsaKeyPair2022(
                URI id,
                URI controller,
                URI type,
                byte[] publicKey,
                byte[] privateKey
                ) {
        super(id, controller, type, publicKey);
        this.privateKey = privateKey;
    }

    @Override
    public byte[] privateKey() {
        return privateKey;
    }
}
