package br.com.doit.SuperHero.migration;


import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class SuperHeroModel0 extends ERXMigrationDatabase.Migration {

    @Override
    public NSArray<ERXModelVersion> modelDependencies() {
        return null;
    }

    
	
    @Override
    public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
       
    }

    @Override
    public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
        ERXMigrationTable abilityTable = database.newTableNamed("ability");
        abilityTable.newIntegerColumn("id", NOT_NULL);
        abilityTable.newStringColumn("description", 100, NOT_NULL);
        abilityTable.newStringColumn("strenth", 50, NOT_NULL);
        abilityTable.create();
        abilityTable.setPrimaryKey("id");

        ERXMigrationTable superHeroTable = database.newTableNamed("super_hero");
        superHeroTable.newIntegerColumn("id", NOT_NULL);
        superHeroTable.newStringColumn("name", 100, NOT_NULL);
        superHeroTable.newIntegerColumn("abilityID", NOT_NULL);
        superHeroTable.create();
        superHeroTable.setPrimaryKey("id");

        superHeroTable.addForeignKey("abilityID", "ability", "id");
    }
}