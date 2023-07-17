package com.apicatalog.ld.signature.eddsa;

import com.apicatalog.jsonld.schema.LdProperty;
import com.apicatalog.jsonld.schema.LdSchema;
import com.apicatalog.jsonld.schema.LdTerm;
import com.apicatalog.ld.signature.CryptoSuite;
import com.apicatalog.ld.signature.primitive.MessageDigest;
import com.apicatalog.ld.signature.primitive.Urdna2015;
import com.apicatalog.multibase.Multibase.Algorithm;
import com.apicatalog.multicodec.Multicodec.Codec;
import com.apicatalog.vc.VcVocab;
import com.apicatalog.vc.integrity.DataIntegritySchema;
import com.apicatalog.vc.integrity.DataIntegritySuite;

public final class EdDSASignature2022 extends DataIntegritySuite {

    static final CryptoSuite CRYPTO = new CryptoSuite(
            "eddsa-2022",
            new Urdna2015(),
            new MessageDigest("SHA-256"),
            new EdDSASignature2022Provider());

    public static final LdTerm VERIFICATION_KEY_TYPE = LdTerm.create("Ed25519VerificationKey2020", VcVocab.SECURITY_VOCAB);

    public static final LdTerm KEY_PAIR_TYPE = LdTerm.create("Ed25519KeyPair2020", VcVocab.SECURITY_VOCAB);

    static final LdSchema METHOD_SCHEMA = DataIntegritySchema.getVerificationKey(
            VERIFICATION_KEY_TYPE,
            DataIntegritySchema.getPublicKey(
                    Algorithm.Base58Btc,
                    Codec.Ed25519PublicKey,
                    key -> key == null || (key.length == 32
                            || key.length == 57
                            || key.length == 114)));
    
    
    static final LdProperty<byte[]> PROOF_VALUE_PROPERTY = DataIntegritySchema.getProofValue(
            Algorithm.Base58Btc,
            key -> key.length == 64);

    public EdDSASignature2022() {
        super(CRYPTO, METHOD_SCHEMA, PROOF_VALUE_PROPERTY);
    }
}