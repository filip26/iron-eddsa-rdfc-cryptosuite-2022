package com.apicatalog.ld.signature.eddsa;

import java.net.URI;
import java.util.Objects;

import com.apicatalog.ld.signature.key.KeyPair;

public record EdDSAKeyPair2022(
        URI id,
        URI controller,
        URI type,
        byte[] publicKey,
        byte[] privateKey
        ) implements KeyPair {

    public EdDSAKeyPair2022 {
        Objects.requireNonNull(id);
    }
}
