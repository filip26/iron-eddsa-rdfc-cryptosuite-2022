package com.apicatalog.ld.signature.eddsa;

import java.net.URI;
import java.time.Instant;

import com.apicatalog.ld.DocumentError;
import com.apicatalog.ld.DocumentError.ErrorType;
import com.apicatalog.ld.signature.CryptoSuite;
import com.apicatalog.ld.signature.VerificationMethod;
import com.apicatalog.ld.signature.primitive.MessageDigest;
import com.apicatalog.ld.signature.primitive.Urdna2015;
import com.apicatalog.multicodec.Multicodec;
import com.apicatalog.multicodec.MulticodecDecoder;
import com.apicatalog.multicodec.codec.KeyCodec;
import com.apicatalog.multikey.MultiKey;
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
            KeyCodec.ED25519_PRIVATE_KEY);

    public static final String CRYPTOSUITE_NAME = "eddsa-rdfc-2022";

    public static final MethodAdapter METHOD_ADAPTER = new MultiKeyAdapter(CODECS) {

        @Override
        protected Multicodec getPublicKeyCodec(String algo, int keyLength) {
            return KeyCodec.ED25519_PUBLIC_KEY;
        }

        @Override
        protected Multicodec getPrivateKeyCodec(String algo, int keyLength) {
            return KeyCodec.ED25519_PRIVATE_KEY;
        }

        protected void validate(MultiKey method) throws DocumentError {
            if (method.publicKey() != null
                    && method.publicKey().length != 32
                    && method.publicKey().length != 57
                    && method.publicKey().length != 114) {
                throw new DocumentError(ErrorType.Invalid, "PublicKeyLength");
            }
        };
    };

    public EdDSASignature2022() {
        super(CRYPTOSUITE_NAME, METHOD_ADAPTER);
    }

    @Override
    protected void validateProofValue(byte[] proofValue) throws DocumentError {
        if (proofValue != null && proofValue.length != 64) {
            throw new DocumentError(ErrorType.Invalid, "ProofValueLength");
        }
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