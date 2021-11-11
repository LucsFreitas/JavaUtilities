package br.com.lucsfreitas.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CpfCnpjValidatorTest {

    private static final String CPF_VALID = "14538220620";
    private static final String CNPJ_VALID = "54731378000194";

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"123456789101", "00000000000", "111"})
    void should_isInvalidCpf(String dataTest) {
        Assertions.assertFalse(CpfCnpjValidator.isValidCPF(dataTest));
    }

    @Test
    void should_isValidCpf() {
        Assertions.assertTrue(CpfCnpjValidator.isValidCPF(CPF_VALID));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"123456789105551", "00000000000000", "111"})
    void should_isInvalidCnpj(String dataTest) {
        Assertions.assertFalse(CpfCnpjValidator.isValidCNPJ(dataTest));
    }

    @Test
    void should_isValidCnpj() {
        Assertions.assertTrue(CpfCnpjValidator.isValidCNPJ(CNPJ_VALID));
    }
}
