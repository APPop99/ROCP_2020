package app.bankingApp.mainApp;

import app.bankingApp.presenter.MainMenuPresenter;
import app.bankingApp.presenter.implementation.MainMenuPresenterImpl;

public class ConsoleBasedBankMain 
{	
	public static void main(String[] args) 
	{	
		//instantiate an object that will facilitate the liaison with Presenter layer 
		MainMenuPresenter mainMenu = new MainMenuPresenterImpl();
		mainMenu.startMenu();
	}
}
