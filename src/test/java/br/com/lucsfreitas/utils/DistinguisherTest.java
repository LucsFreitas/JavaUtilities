package br.com.lucsfreitas.utils;

import br.com.lucsfreitas.domain.CivilStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

public class DistinguisherTest {

    private static final String NAME = "João da Silva Conceição";

    @Test
    void should_testEqualsIgnoreCaseWithNulls (){
        Assertions.assertTrue(Distinguisher.equalsIgnoreCaseAndAccents(null, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Joao da Silva Conceicao", "JOÃO DA SILVA CONCEIÇÃO"})
    void should_testEqualsIgnoreCase (String input){
        Assertions.assertTrue(Distinguisher.equalsIgnoreCaseAndAccents(NAME, input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"oão da Silva Conceição"})
    @NullAndEmptySource
    void should_failEqualsIgnoreCase(String input) {
        Assertions.assertFalse(Distinguisher.equalsIgnoreCaseAndAccents(NAME, input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"divorced", "DIVORCED", "DÍvôRÇed"})
    @NullSource
    void should_verifyWhen_Duplicated_value(String value){
        List<CivilStatus> list = getList();
        list.add(createCivilStatus(5L, value));

        Assertions.assertTrue(Distinguisher.verifyDuplicates(list, CivilStatus::getDescription));
    }

    @Test
    void should_verifyWhen_NotDuplicated(){

        List<CivilStatus> list = getList();
        list.add(createCivilStatus(5L, "ENGAGED"));

        Assertions.assertFalse(Distinguisher.verifyDuplicates(list, CivilStatus::getDescription));
    }

    private List<CivilStatus> getList() {
        List<CivilStatus> list = new ArrayList<>();

        list.add(createCivilStatus(1L, "DIVORCED"));
        list.add(createCivilStatus(2L, "WIDOWER"));
        list.add(createCivilStatus(3L, "SINGLE"));
        list.add(createCivilStatus(4L, null));

        return list;
    }

    private CivilStatus createCivilStatus(Long id, String description) {
        return CivilStatus.builder()
                .id(id)
                .description(description)
                .build();
    }
}
