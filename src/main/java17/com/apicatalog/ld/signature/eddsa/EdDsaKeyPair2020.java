package com.apicatalog.ld.signature.eddsa;

import java.net.URI;
import java.util.Objects;

import com.apicatalog.ld.signature.key.KeyPair;

public record EdDsaKeyPair2020(
        URI id,
        URI controller,
        URI type,
        byte[] publicKey,
        byte[] privateKey
        ) implements KeyPair {

    public EdDsaKeyPair2020 {
        Objects.requireNonNull(id);
    }
}
