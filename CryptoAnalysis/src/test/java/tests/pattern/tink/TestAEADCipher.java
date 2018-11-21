package tests.pattern.tink;

import java.security.GeneralSecurityException;

import org.junit.Test;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadFactory;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.proto.KeyTemplate;

import test.UsagePatternTestingFramework;
import test.assertions.Assertions;

public class TestAEADCipher extends UsagePatternTestingFramework {
	
	@Test
	public void generateNewAES128GCMKeySet() throws GeneralSecurityException {
		KeyTemplate kt = AeadKeyTemplates.AES128_GCM;
		KeysetHandle ksh = KeysetHandle.generateNew(kt);
		Assertions.hasEnsuredPredicate(kt);
		Assertions.mustBeInAcceptingState(kt);
		Assertions.mustBeInAcceptingState(ksh);		
	}
	
	@Test
	public void generateNewAES256GCMKeySet() throws GeneralSecurityException {
		KeyTemplate kt = AeadKeyTemplates.AES256_GCM;
		KeysetHandle ksh = KeysetHandle.generateNew(kt);
		Assertions.mustBeInAcceptingState(kt);
		Assertions.mustBeInAcceptingState(ksh);
	}
	
	@Test
	public void generateNewAES128EAXKeySet() throws GeneralSecurityException {
		KeyTemplate kt = AeadKeyTemplates.AES128_EAX;
		KeysetHandle ksh = KeysetHandle.generateNew(kt);
		Assertions.mustBeInAcceptingState(kt);
		Assertions.mustBeInAcceptingState(ksh);		
	}
	
	@Test
	public void generateNewAES256EAXKeySet() throws GeneralSecurityException {
		KeyTemplate kt = AeadKeyTemplates.AES256_EAX;
		KeysetHandle ksh = KeysetHandle.generateNew(kt);
		Assertions.mustBeInAcceptingState(kt);
		Assertions.mustBeInAcceptingState(ksh);		
	}
	
	@Test
	public void encryptUsingAES128GCM() throws GeneralSecurityException {
		KeyTemplate kt = AeadKeyTemplates.AES128_GCM;
		
		KeysetHandle ksh = KeysetHandle.generateNew(kt);
		
		//Assertions.hasEnsuredPredicate(kt);
		
		final String plainText = "Just testing the encryption mode of AEAD"; 
		final String aad = "cryptsl";
		
		Aead aead = AeadFactory.getPrimitive(ksh);
		byte[] out = aead.encrypt(plainText.getBytes(), aad.getBytes());
		
		Assertions.mustBeInAcceptingState(aead);
		Assertions.hasEnsuredPredicate(out);
  	}
}
