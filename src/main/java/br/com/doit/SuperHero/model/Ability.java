package br.com.doit.SuperHero.model;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSValidation;

import er.extensions.eof.ERXFetchSpecification;
import er.extensions.eof.ERXQ;

public class Ability extends _Ability {
    private static final long serialVersionUID = 1L;

    // Métodos temporários caso o EOF ainda não tenha gerado
    //private String name;
    //private String strength;

    //public String Name() { return name; }
    //public void setName(String name) { this.name = name; }
    
    //public String strength() { return strength; }
    //public void setStrength(String strength) { this.strength = strength; }
    
    @Override
    public void validateForSave() throws NSValidation.ValidationException {
        super.validateForSave();

        NSArray<Ability> descricaoDuplicada = new ERXFetchSpecification<Ability>(
                ENTITY_NAME, ERXQ.equals(DESCRIPTION_KEY, description()), null)
                .fetchObjects(editingContext());

        for (Ability outro : descricaoDuplicada) {
            if (!outro.equals(this)) {
                throw new NSValidation.ValidationException("Duplicated");
            }
        }
            NSArray<Ability> descricaoVazia = new ERXFetchSpecification<Ability>(
                    ENTITY_NAME, ERXQ.equals(DESCRIPTION_KEY, description()), null)
                    .fetchObjects(editingContext());

            for (Ability outro : descricaoVazia) {
                if (outro.equals(null) || outro.equals("")) {
                    throw new NSValidation.ValidationException("Description");
                }    
            
        }
    }
}
