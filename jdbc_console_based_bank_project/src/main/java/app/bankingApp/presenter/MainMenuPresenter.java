package app.bankingApp.presenter;

//import java.util.ArrayList;
//import java.util.List;

import app.bankingApp.model.User;

public interface MainMenuPresenter 
{
	public void startMenu(); 
	
	public void createNewUser();

	public User loginUser();
}
