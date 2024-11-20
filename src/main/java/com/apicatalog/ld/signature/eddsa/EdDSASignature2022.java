package com.apicatalog.ld.signature.eddsa;

import java.net.URI;

import com.apicatalog.controller.key.KeyPair;
import com.apicatalog.controller.method.VerificationMethod;
import com.apicatalog.cryptosuite.CryptoSuite;
import com.apicatalog.cryptosuite.primitive.MessageDigest;
import com.apicatalog.cryptosuite.primitive.Urdna2015;
import com.apicatalog.jsonld.loader.DocumentLoader;
import com.apicatalog.ld.DocumentError;
import com.apicatalog.ld.DocumentError.ErrorType;
import com.apicatalog.multibase.Multibase;
import com.apicatalog.multicodec.MulticodecDecoder;
import com.apicatalog.multicodec.codec.KeyCodec;
import com.apicatalog.vc.issuer.Issuer;
import com.apicatalog.vc.model.VerifiableMaterial;
import com.apicatalog.vc.proof.ProofValue;
import com.apicatalog.vc.solid.SolidIssuer;
import com.apicatalog.vc.solid.SolidProofValue;
import com.apicatalog.vcdi.DataIntegrityProofDraft;
import com.apicatalog.vcdi.DataIntegritySuite;

public final class EdDSASignature2022 extends DataIntegritySuite {

    public static final String CRYPTOSUITE_NAME = "eddsa-rdfc-2022";

    public static final MulticodecDecoder CODECS = MulticodecDecoder.getInstance(
            KeyCodec.ED25519_PUBLIC_KEY,
            KeyCodec.ED25519_PRIVATE_KEY);

    public static final CryptoSuite CRYPTO = new CryptoSuite(
            CRYPTOSUITE_NAME,
            32,
            new Urdna2015(),
            new MessageDigest("SHA-256"),
            new NativeSignatureProvider("Ed25519"));

    public EdDSASignature2022() {
        super(CRYPTOSUITE_NAME, Multibase.BASE_58_BTC);
    }

    @Override
    public Issuer createIssuer(KeyPair keyPair) {
        return new SolidIssuer(this, CRYPTO, keyPair, proofValueBase);
    }

    public DataIntegrityProofDraft createDraft(VerificationMethod verificationMethod, URI purpose) {
        return new DataIntegrityProofDraft(this, CRYPTO, verificationMethod, purpose);
    }

    public DataIntegrityProofDraft createDraft(URI verificationMethod, URI purpose) {
        return new DataIntegrityProofDraft(this, CRYPTO, verificationMethod, purpose);
    }

    protected ProofValue getProofValue(VerifiableMaterial data, VerifiableMaterial proof, byte[] proofValue, DocumentLoader loader) throws DocumentError {
        if (proofValue == null) {
            return null;
        }

        if (proofValue.length != 64) {
            throw new DocumentError(ErrorType.Invalid, "ProofValueLength");
        }
        return SolidProofValue.of(CRYPTO, data, proof, proofValue);
    }

    @Override
    protected CryptoSuite getCryptoSuite(String cryptoName, ProofValue proofValue) throws DocumentError {
        if (!CRYPTOSUITE_NAME.equals(cryptoName)) {
            return null;
        }
        return CRYPTO;
    }
}