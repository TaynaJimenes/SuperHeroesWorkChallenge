// $LastChangedRevision$ DO NOT EDIT.  Make changes to Ability.java instead.
package br.com.doit.SuperHero.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _Ability extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Ability";

	// Attributes
	public static final String DESCRIPTION_KEY = "description";
	public static final String STRENTH_KEY = "strenth";

	// Relationships
	public static final String SUPER_HEROS_KEY = "superHeros";

  private static Logger LOG = Logger.getLogger(_Ability.class);

  public Ability localInstanceIn(EOEditingContext editingContext) {
    Ability localInstance = (Ability)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String description() {
    return (String) storedValueForKey("description");
  }

  public void setDescription(String value) {
    if (_Ability.LOG.isDebugEnabled()) {
    	_Ability.LOG.debug( "updating description from " + description() + " to " + value);
    }
    takeStoredValueForKey(value, "description");
  }

  public br.com.doit.SuperHero.model.Strenth strenth() {
    return (br.com.doit.SuperHero.model.Strenth) storedValueForKey("strenth");
  }

  public void setStrenth(br.com.doit.SuperHero.model.Strenth value) {
    if (_Ability.LOG.isDebugEnabled()) {
    	_Ability.LOG.debug( "updating strenth from " + strenth() + " to " + value);
    }
    takeStoredValueForKey(value, "strenth");
  }

  public NSArray<br.com.doit.SuperHero.model.SuperHero> superHeros() {
    return (NSArray<br.com.doit.SuperHero.model.SuperHero>)storedValueForKey("superHeros");
  }

  public NSArray<br.com.doit.SuperHero.model.SuperHero> superHeros(EOQualifier qualifier) {
    return superHeros(qualifier, null, false);
  }

  public NSArray<br.com.doit.SuperHero.model.SuperHero> superHeros(EOQualifier qualifier, boolean fetch) {
    return superHeros(qualifier, null, fetch);
  }

  public NSArray<br.com.doit.SuperHero.model.SuperHero> superHeros(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<br.com.doit.SuperHero.model.SuperHero> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(br.com.doit.SuperHero.model.SuperHero.ABILITY_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = br.com.doit.SuperHero.model.SuperHero.fetchSuperHeros(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = superHeros();
      if (qualifier != null) {
        results = (NSArray<br.com.doit.SuperHero.model.SuperHero>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<br.com.doit.SuperHero.model.SuperHero>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToSuperHerosRelationship(br.com.doit.SuperHero.model.SuperHero object) {
    if (_Ability.LOG.isDebugEnabled()) {
      _Ability.LOG.debug("adding " + object + " to superHeros relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "superHeros");
  }

  public void removeFromSuperHerosRelationship(br.com.doit.SuperHero.model.SuperHero object) {
    if (_Ability.LOG.isDebugEnabled()) {
      _Ability.LOG.debug("removing " + object + " from superHeros relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "superHeros");
  }

  public br.com.doit.SuperHero.model.SuperHero createSuperHerosRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("SuperHero");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "superHeros");
    return (br.com.doit.SuperHero.model.SuperHero) eo;
  }

  public void deleteSuperHerosRelationship(br.com.doit.SuperHero.model.SuperHero object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "superHeros");
    editingContext().deleteObject(object);
  }

  public void deleteAllSuperHerosRelationships() {
    Enumeration objects = superHeros().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteSuperHerosRelationship((br.com.doit.SuperHero.model.SuperHero)objects.nextElement());
    }
  }


  public static Ability createAbility(EOEditingContext editingContext, String description
, br.com.doit.SuperHero.model.Strenth strenth
) {
    Ability eo = (Ability) EOUtilities.createAndInsertInstance(editingContext, _Ability.ENTITY_NAME);    
		eo.setDescription(description);
		eo.setStrenth(strenth);
    return eo;
  }

  public static NSArray<Ability> fetchAllAbilities(EOEditingContext editingContext) {
    return _Ability.fetchAllAbilities(editingContext, null);
  }

  public static NSArray<Ability> fetchAllAbilities(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Ability.fetchAbilities(editingContext, null, sortOrderings);
  }

  public static NSArray<Ability> fetchAbilities(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Ability.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Ability> eoObjects = (NSArray<Ability>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Ability fetchAbility(EOEditingContext editingContext, String keyName, Object value) {
    return _Ability.fetchAbility(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Ability fetchAbility(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Ability> eoObjects = _Ability.fetchAbilities(editingContext, qualifier, null);
    Ability eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Ability)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Ability that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Ability fetchRequiredAbility(EOEditingContext editingContext, String keyName, Object value) {
    return _Ability.fetchRequiredAbility(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Ability fetchRequiredAbility(EOEditingContext editingContext, EOQualifier qualifier) {
    Ability eoObject = _Ability.fetchAbility(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Ability that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Ability localInstanceIn(EOEditingContext editingContext, Ability eo) {
    Ability localInstance = (eo == null) ? null : (Ability)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
