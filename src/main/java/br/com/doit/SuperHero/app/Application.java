package br.com.doit.SuperHero.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.webobjects.appserver.WOSession;
import com.webobjects.foundation.NSLog;
import er.extensions.appserver.ERXApplication;
import er.extensions.foundation.ERXPatcher;
import er.extensions.foundation.ERXProperties;
import br.com.doit.SuperHero.components.Main;

public class Application extends ERXApplication {
	public static void main(String[] argv) {
		ERXApplication.main(argv, Application.class);
	}

	public Application() {
		super();
		NSLog.out.appendln("Welcome to " + name() + " !");
		/* ** put your initialization code in here ** */
	}

	
	
	
	
	/**
	 * Determines the WOSession class to instantiate.
	 * @see com.webobjects.appserver.WOApplication#_sessionClass()
	 */
	@Override
	protected Class< ? extends WOSession > _sessionClass() {
		return Session.class;
	}

	/**
	 * Install patches including ensuring that Main is correctly resolved at runtime.
	 * @see er.extensions.appserver.ERXApplication#installPatches()
	 */
	@Override
	public void installPatches() {
		super.installPatches();
		ERXPatcher.setClassForName(Main.class, "Main");
	}
}
