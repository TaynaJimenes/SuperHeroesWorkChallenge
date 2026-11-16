package br.com.doit.SuperHero.model;

import static com.wounit.matchers.EOAssert.canBeSaved;
import static com.wounit.matchers.EOAssert.cannotBeSavedBecause;
import static com.wounit.matchers.EOAssert.confirm;

import org.junit.Rule;
import org.junit.Test;

import com.wounit.annotations.UnderTest;
import com.wounit.rules.MockEditingContext;

public class AbilityTest {

	@Rule
	public SuperHeroMockEditingContext ec = new SuperHeroMockEditingContext("SuperHero");

    @UnderTest
    private Ability poder;

    @Test
    public void poderComDescricaoIneditaPodeSerSalvo() {
        poder.setDescription("SPEED");
        poder.setStrenth(Strenth.STRONG);

        confirm(poder, canBeSaved());
    }

    @Test
    public void poderComDescricaoRepetidaNaoPodeSerSalvo() {
        Ability existente = ec.createSavedObject(Ability.class);
        existente.setDescription("SPEED");
        existente.setStrenth(Strenth.WEAK);

        poder.setDescription("SPEED");
        poder.setStrenth(Strenth.STRONG);

        confirm(poder, cannotBeSavedBecause("Duplicated"));
    }
    @Test
    public void poderSemDescricaoNaoPodeSerSalvo() {
    	poder.setStrenth(Strenth.STRONG);
        confirm(poder, cannotBeSavedBecause("Description"));

    }
    @Test
    public void poderComDescricaoVaziaNaoPodeSerSalvo() {
        poder.setDescription("");
        poder.setStrenth(Strenth.STRONG);
        confirm(poder, cannotBeSavedBecause("Description"));

    }
    @Test
    public void poderSemStrenthNaoPodeSerSalvo() {
        poder.setDescription("SPEED");
        confirm(poder, cannotBeSavedBecause("Strenth"));

    }    
    
    
}