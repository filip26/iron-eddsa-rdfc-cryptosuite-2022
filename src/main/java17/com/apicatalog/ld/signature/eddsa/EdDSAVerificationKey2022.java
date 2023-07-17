package com.apicatalog.ld.signature.eddsa;

import java.net.URI;
import java.util.Objects;

import com.apicatalog.ld.signature.key.VerificationKey;

public record EdDSAVerificationKey2022(
        URI id,
        URI controller,
        URI type,
        byte[] publicKey
        ) implements VerificationKey {

    public EdDSAVerificationKey2022 {
        Objects.requireNonNull(id);
    }
}
