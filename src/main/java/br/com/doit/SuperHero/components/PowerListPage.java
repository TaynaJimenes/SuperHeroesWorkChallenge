 //public final EOEditingContext ecPoderes = ERXEC.newEditingContext();
 //public final NSMutableArray<Ability> poderes = new NSMutableArray<Ability>();


package br.com.doit.SuperHero.components;

import com.webobjects.appserver.WOActionResults;

import com.webobjects.foundation.NSValidation;
import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

import br.com.doit.SuperHero.app.Session;
import br.com.doit.SuperHero.model.Ability;
import br.com.doit.SuperHero.model.Strenth;
import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXS;

public class PowerListPage extends ERXComponent {

    private static final long serialVersionUID = 1L;

    
    public NSArray<Ability> powers;
    public Ability powerNovo;
    public Ability powerAtual;
    

    
    public boolean mostrandoForm = false;
    public String novaDescricao;
    public Strenth novaForca;
    public Strenth forcaItem;

    
    public String erro;
    public String sucesso;

    public PowerListPage(WOContext context) {
        super(context);
    }

    private EOEditingContext ec() {
        return session().defaultEditingContext();
    }

    public NSArray<Ability> powers() {
    	if (powers == null || powers.isEmpty()) {
    		powers = Ability.fetchAllAbilities(ec(), ERXS.ascs(Ability.DESCRIPTION_KEY));
    	}
        return powers;
    }
    public boolean listaVazia() {
        return powers().isEmpty();
    }

    public NSArray<Strenth> forcas() {
        return new NSArray<Strenth>(Strenth.values());
    }

    public boolean temErro() {
        return erro != null;
    }

    public boolean temSucesso() {
        return sucesso != null;
    }

    public WOActionResults abrirForm() {
    	EOEditingContext ecNovo = ERXEC.newEditingContext();
    	powerNovo = ERXEOControlUtilities.createAndInsertObject(ecNovo, Ability.class); 
        mostrandoForm = true;
        novaDescricao = null;
        novaForca = null;
        erro = null;
        sucesso = null;
        return null;
    }
    
    public WOActionResults delete() {
    	try {
    		ec().deleteObject(powerAtual);
            System.out.println("1");
            powerAtual.editingContext().saveChanges();
    		powers = null;
			
		} catch (Exception e) {
			erro = "Unable to delete" + e.getMessage();
			ec().revert();
			powers = null;
		}
    	
    	return null;
    }
    
    public WOActionResults edit() {
    	EOEditingContext ecEdicao = ERXEC.newEditingContext();
    	powerNovo = powerAtual.localInstanceIn(ecEdicao);
    	erro = null;
    	mostrandoForm = true;
    	return null;
    }
    
    
    
    public String teste(){
    	System.out.println("Foi!!!!!!");
    	return null;
    }
    
    
    
    
    
    
    

    public WOActionResults salvar() {
        sucesso = null;



        // 2. Agora é seguro fazer o trim e toUpperCase
        powerNovo.setDescription(powerNovo.description().trim().toUpperCase());

        try {
            powerNovo.validateForSave();
            powerNovo.editingContext().saveChanges();
        } catch (NSValidation.ValidationException e) {
            erro = "Duplicated: there is already a power with this description.";
            return null;
        }
        

        erro = null;
        mostrandoForm = false;
        sucesso = "New power added successfully!";
        powerAtual = null;
        powers = null;
        return null;
    }

    public WOActionResults cancelar() {
        mostrandoForm = false;
        ec().revert();
        powerAtual = null;
        powers = null;
        erro = null;
        return null;
    }
}