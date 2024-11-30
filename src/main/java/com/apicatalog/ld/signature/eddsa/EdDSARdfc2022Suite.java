package com.apicatalog.ld.signature.eddsa;

import java.net.URI;

import com.apicatalog.controller.key.KeyPair;
import com.apicatalog.cryptosuite.CryptoSuite;
import com.apicatalog.cryptosuite.primitive.MessageDigest;
import com.apicatalog.cryptosuite.primitive.RDFC;
import com.apicatalog.jsonld.loader.DocumentLoader;
import com.apicatalog.multibase.Multibase;
import com.apicatalog.multicodec.MulticodecDecoder;
import com.apicatalog.multicodec.codec.KeyCodec;
import com.apicatalog.vc.di.DataIntegrityDraft;
import com.apicatalog.vc.di.DataIntegritySuite;
import com.apicatalog.vc.issuer.Issuer;
import com.apicatalog.vc.model.DocumentError;
import com.apicatalog.vc.model.DocumentModel;
import com.apicatalog.vc.model.VerifiableMaterial;
import com.apicatalog.vc.model.DocumentError.ErrorType;
import com.apicatalog.vc.proof.Proof;
import com.apicatalog.vc.proof.ProofValue;
import com.apicatalog.vc.solid.SolidIssuer;
import com.apicatalog.vc.solid.SolidProofValue;

public final class EdDSARdfc2022Suite extends DataIntegritySuite {

    public static final String CRYPTOSUITE_NAME = "eddsa-rdfc-2022";

    public static final MulticodecDecoder CODECS = MulticodecDecoder.getInstance(
            KeyCodec.ED25519_PUBLIC_KEY,
            KeyCodec.ED25519_PRIVATE_KEY);

    public static final CryptoSuite CRYPTO = new CryptoSuite(
            CRYPTOSUITE_NAME,
            256,
            new RDFC(),
            new MessageDigest("SHA-256"),
            new NativeSignatureProvider("Ed25519"));

    public EdDSARdfc2022Suite() {
        super(CRYPTOSUITE_NAME, Multibase.BASE_58_BTC);
    }

    @Override
    public Issuer createIssuer(KeyPair keyPair) {
        return new SolidIssuer(
                this,
                CRYPTO,
                keyPair,
                proofValueBase,
                method -> new DataIntegrityDraft(this, CRYPTO, method));
    }

    @Override
    protected ProofValue getProofValue(Proof proof, DocumentModel model, byte[] proofValue, DocumentLoader loader, URI base) throws DocumentError {
        if (proofValue == null) {
            return null;
        }

        if (proofValue.length != 64) {
            throw new DocumentError(ErrorType.Invalid, "ProofValueLength");
        }
        
        VerifiableMaterial verifiable =  model.data();
        VerifiableMaterial unsignedProof = model.proofs().iterator().next();
        
        return SolidProofValue.of(CRYPTO, verifiable, unsignedProof, proofValue, proof);
    }

    @Override
    protected CryptoSuite getCryptoSuite(String cryptoName, ProofValue proofValue) throws DocumentError {
        if (!CRYPTOSUITE_NAME.equals(cryptoName)) {
            return null;
        }
        return CRYPTO;
    }
}