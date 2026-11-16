package br.com.doit.SuperHero.model;

import static com.wounit.matchers.EOAssert.canBeSaved;
import static com.wounit.matchers.EOAssert.cannotBeSavedBecause;
import static com.wounit.matchers.EOAssert.confirm;
import com.webobjects.foundation.NSValidation;

import org.junit.Rule;
import org.junit.Test;

import com.wounit.annotations.Dummy;
import com.wounit.annotations.UnderTest;

public class SuperHeroTest {

    @Rule
    public SuperHeroMockEditingContext ec = new SuperHeroMockEditingContext("SuperHero");

    @UnderTest
    private SuperHero heroi;

    @Dummy
    private Ability poder;

    @Test
    public void heroiComNomeIneditoPodeSerSalvo() {
        heroi.setName("FLASH");
        heroi.setAbilityRelationship(poder);

        confirm(heroi, canBeSaved());
    }

    @Test
    public void heroiComNomeRepetidoNaoPodeSerSalvo() {
        SuperHero existente = ec.createSavedObject(SuperHero.class);
        existente.setName("FLASH");
        existente.setAbilityRelationship(poder);

        heroi.setName("FLASH");
        heroi.setAbilityRelationship(poder);

        confirm(heroi, cannotBeSavedBecause("Duplicated"));
    }



    @Test
    public void heroiSemNomeNaoPodeSerSalvo() {
        heroi.setAbilityRelationship(poder);
        confirm(heroi, cannotBeSavedBecause("name"));

    }
    @Test
    public void heroiComEspacosNoNomeNaoPodeSerSalvo() {
    	heroi.setName("  "); // trim();
        heroi.setAbilityRelationship(poder);
    	confirm(heroi, cannotBeSavedBecause("name"));
    }
    @Test
    public void heroiComNomeVazioNaoPodeSerSalvo() {
        heroi.setName("");
        heroi.setAbilityRelationship(poder);
        confirm(poder, cannotBeSavedBecause("Insert"));

    }
    @Test
    public void poderSemRelationshipNaoPodeSerSalvo() {
        heroi.setName("FLASH");
        confirm(heroi, cannotBeSavedBecause("abilityRelationship"));

    } 
    
    
}