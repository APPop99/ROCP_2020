package app.bankingApp.mainApp;

//import java.util.Scanner;

//import org.apache.log4j.Logger;

import app.bankingApp.presenter.MainMenuPresenter;
import app.bankingApp.presenter.implementation.MainMenuPresenterImpl;

public class ConsoleBasedBankMain 
{	
//	private static Logger log = Logger.getLogger(ConsoleBasedBankMain.class);

	public static void main(String[] args) 
	{	
		//instantiate an object that will facilitate the liaison with Presenter layer 
		MainMenuPresenter mainMenu = new MainMenuPresenterImpl();
		mainMenu.startMenu();
	}
}
