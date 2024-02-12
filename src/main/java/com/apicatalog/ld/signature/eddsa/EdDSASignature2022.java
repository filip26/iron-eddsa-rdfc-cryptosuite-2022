package com.apicatalog.ld.signature.eddsa;

import java.net.URI;
import java.time.Instant;

import com.apicatalog.ld.DocumentError;
import com.apicatalog.ld.signature.CryptoSuite;
import com.apicatalog.ld.signature.VerificationMethod;
import com.apicatalog.ld.signature.primitive.MessageDigest;
import com.apicatalog.ld.signature.primitive.Urdna2015;
import com.apicatalog.multicodec.Multicodec;
import com.apicatalog.multicodec.MulticodecDecoder;
import com.apicatalog.multicodec.codec.KeyCodec;
import com.apicatalog.multikey.MultiKeyAdapter;
import com.apicatalog.vc.integrity.DataIntegrityProof;
import com.apicatalog.vc.integrity.DataIntegritySuite;
import com.apicatalog.vc.method.MethodAdapter;

public final class EdDSASignature2022 extends DataIntegritySuite {

    public static final CryptoSuite CRYPTO = new CryptoSuite(
            new Urdna2015(),
            new MessageDigest("SHA-256"),
            new EdDSASignature2022Provider());

    public static final MulticodecDecoder CODECS = MulticodecDecoder.getInstance(
            KeyCodec.ED25519_PUBLIC_KEY,
            KeyCodec.ED25519_PRIVATE_KEY
            );
    
    public static final MethodAdapter METHOD_ADAPTER = new MultiKeyAdapter(CODECS) {
        
        @Override
        protected Multicodec getPublicKeyCodec(String algo, int keyLength) {
            System.out.println("PUB " + algo + ", " + keyLength);
            // TODO Auto-generated method stub
            return null;
        }
        
        @Override
        protected Multicodec getPrivateKeyCodec(String algo, int keyLength) {
            System.out.println("PRIV " + algo + ", " + keyLength);
            return null;
        }
    };
    
//    public static final LdTerm VERIFICATION_KEY_TYPE = LdTerm.create("Ed25519VerificationKey2020", VcVocab.SECURITY_VOCAB);
//
//    public static final LdTerm KEY_PAIR_TYPE = LdTerm.create("Ed25519KeyPair2020", VcVocab.SECURITY_VOCAB);

//    static final LdSchema METHOD_SCHEMA = DataIntegritySchema.getVerificationKey(
//            VERIFICATION_KEY_TYPE,
//            DataIntegritySchema.getPublicKey(
//                    Algorithm.Base58Btc,
//                    Codec.Ed25519PublicKey,
//                    key -> key == null || (key.length == 32
//                            || key.length == 57
//                            || key.length == 114)));

//    static final LdProperty<byte[]> PROOF_VALUE_PROPERTY = DataIntegritySchema.getProofValue(
//            Algorithm.Base58Btc,
//            key -> key.length == 64);

    public EdDSASignature2022() {
        super("eddsa-2022", METHOD_ADAPTER);
    }

    @Override
    protected CryptoSuite getCryptoSuite(String cryptoName) throws DocumentError {
        return CRYPTO;
    }

    public DataIntegrityProof createDraft(
            VerificationMethod verificationMethod,
            URI purpose,
            Instant created,
            String domain,
            String challenge) throws DocumentError {
        return super.createDraft(CRYPTO, verificationMethod, purpose, created, domain, challenge);
    }
}