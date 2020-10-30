package app.player.main;

import java.util.List;
import java.util.Scanner;

import app.player.exception.BusinessException;
import app.player.model.Player;
import app.player.service.IPlayerSearchService;
import app.player.service.implementation.PlayerSearchService;

public class PlayerMain {

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to Vinay's Player Search APP V1.0");
		System.out.println("--------------------------------------------------");
		
		//instantiate an object that will be used () to transfer data to and from Service layer 
		IPlayerSearchService playerSearchService=new PlayerSearchService();
		
		int choice = 0;
		do 
		{
			System.out.println("Player Search MENU");
			System.out.println("======================");
			System.out.println("1)By ID");
			System.out.println("2)By Name");
			System.out.println("3)By Age");
			System.out.println("4)By Gender");
			System.out.println("5)By Contact");
			System.out.println("6)By TeamName");
			System.out.println("7)All Players");
			System.out.println("8)EXIT");
			System.out.println("Please enter appropriate choice(1-8) :) ");
			
			try 
			{
				choice = Integer.parseInt(scanner.nextLine());
			} 
			catch (NumberFormatException e) 
			{
				System.out.println("Please enter a number between 1 and 8!");
			}
			
			switch (choice) {
			
			case 1:
				System.out.println("Please Enter Player ID to get Player Details");
				try {
					int id = Integer.parseInt(scanner.nextLine());

					//Code Here for SERVICE LAYER
					Player player=playerSearchService.getPlayerById(id);
					
					if (player!=null)
					{
						System.out.println("Player found with id "+id+" details are : ");
						System.out.println(player);
					}
				} 
				catch (BusinessException e) 
				{
					System.out.println(e.getMessage());
				}
				break;
			
			case 2:
				System.out.println("Please enter a name to get the Players with that name ");
				String name=scanner.nextLine();
				
				try 
				{
					List<Player> playersListName;
					playersListName = playerSearchService.getPlayersByName(name);
					if(playersListName!=null && playersListName.size()>0)
					{
						System.out.println("We found "+playersListName.size()+" player/s in the DB with name "+name+"... Detailed list is:");
						for(Player p:playersListName)
						{
							System.out.println(p);
						}
					}

				} catch (BusinessException e) 
				{	
					System.out.println(e.getMessage());
				}
				break;
			
			case 3:
				System.out.println("Please enter an Age to get the Players with that age ");
				int age=Integer.parseInt(scanner.nextLine());
				
				try 
				{
					List<Player> playersListByAge;
					playersListByAge = playerSearchService.getPlayersByAge(age);
					if(playersListByAge!=null && playersListByAge.size()>0)
					{
						System.out.println("We found "+playersListByAge.size()+" player/s in the DB with age "+age+"... Detailed list is:");
						for(Player p:playersListByAge)
						{
							System.out.println(p);
						}
					}

				} catch (BusinessException e) 
				{	
					System.out.println(e.getMessage());
				}				break;
			
			case 4:
				System.out.println("Please enter gender(m/f/o) to get the Players with that gender ");
				String gender=scanner.nextLine();
				
				try 
				{
					List<Player> playersListGender;
					playersListGender = playerSearchService.getPlayersByGender(gender);
					if(playersListGender!=null && playersListGender.size()>0)
					{
						System.out.println("We found "+playersListGender.size()+" player/s in the DB with gender "+gender+"... Detailed list is:");
						for(Player p:playersListGender)
						{
							System.out.println(p);
						}
					}

				} catch (BusinessException e) 
				{	
					System.out.println(e.getMessage());
				}
				break;
			
			case 5:
				System.out.println("Please Enter Player's Contact Number to get Player Details");
				try {
					long contact = Long.parseLong(scanner.nextLine());

					//Code Here for SERVICE LAYER
					Player player=playerSearchService.getPlayerByContactNumber(contact);
					
					if (player!=null)
					{
						System.out.println("Player found with contact number "+contact+" details are : ");
						System.out.println(player);
					}
				} 
				catch (BusinessException e) 
				{
					System.out.println(e.getMessage());
				}
				break;
			
			case 6:
				System.out.println("Please enter a Team Name to get the Players playing fot that team ");
				String teamName=scanner.nextLine();
				
				try 
				{
					List<Player> playersListTeamName;
					playersListTeamName = playerSearchService.getPlayersByTeamName(teamName);
					if(playersListTeamName!=null && playersListTeamName.size()>0)
					{
						System.out.println("We found "+playersListTeamName.size()+" player/s in the DB playing for the team "+teamName+"... Detailed list is:");
						for(Player p:playersListTeamName)
						{
							System.out.println(p);
						}
					}

				} catch (BusinessException e) 
				{	
					System.out.println(e.getMessage());
				}				break;
			
			case 7:
				System.out.println("Fetching All The Players From the DB");
				
				try 
				{
					List<Player> playersList;
					playersList = playerSearchService.getAllPlayers();
					if(playersList!=null && playersList.size()>0)
					{
						System.out.println("We found "+playersList.size()+" player/s in the DB... Detailed list is:");
						for(Player p:playersList)
						{
							System.out.println(p);
						}
					}

				} catch (BusinessException e) 
				{	
					System.out.println(e.getMessage());
				}
					
				break;
			
			case 8:
				System.out.println(
						"Thank you for using Player Search App V1.0... We expect to see you back very soon!");
				break;
			
			default:
				System.out.println("Invalid Choice!!!! Please enter choice between 1-8 only");
				break;
			}
		} while (choice != 8);
	}
}
