package com.mycompany.a3;

import java.util.Observable;

/**
 * Author: Nicholas Dubble
 * File: GameWorld.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class GameWorld extends Observable implements IGameWorld
{
	//private java.util.Vector<GameObject> gameObjects = new java.util.Vector<GameObject>();
	private GameObjectCollection gameObjects;
	private int playerLives;
	private final int TURNAMOUNT = 5;							// a constant used to determine how much a playership can turn (keep it a multiple of 360)
	private int playerScore;
	private int gameTime;										// elapsed game time								// used to confirm whether or not a user wants to quit
	private final int SPEEDINCREMENT = 1;						// constant used for increasing/decreasing player ship speed
	protected int gameWorldHeight;
	protected int gameWorldWidth;
	private boolean sound;
	
	public void init(int h, int w)
	{
		playerLives = 3;
		playerScore = 0;
		gameTime = 0;  
		sound = true;
		gameWorldHeight = h;
		gameWorldWidth = w;
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this)); //notify observers with initial GameWorld values
	}
	
	public GameWorld()
	{
		gameObjects = new GameObjectCollection();

	}
	
	public IIterator getIterator()
	{
		return gameObjects.getIterator();
	}
	
	public int getElapsedTime()
	{
		return gameTime;
	}
	
	public boolean getSound()
	{
		return sound;
	}
	
	public boolean setSound()
	{
		if (sound == true)
			sound = false;
		else
			sound = true;
		
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		return true;
	}
	
	public boolean incrementGameTime()
	{
		gameTime++;
		return true;
	}
	
	public Boolean isPlayerShip(int index)	// returns whether or not the game object at the specified index is a playership object
	{
		
		Boolean isAPlayerShip = false;
		IIterator iterator = getIterator();
		if(iterator.elementAt(index) instanceof PlayerShip)
			isAPlayerShip = true;
		return isAPlayerShip;
		
		
	}
	
	public Boolean isNonPlayerShip(int index)	// returns whether or not the game object at the specified index is a NonPlayerShip object
	{
		
		Boolean isANonPlayerShip = false;
		IIterator iterator = getIterator();
		if(iterator.elementAt(index) instanceof NonPlayerShip)
			isANonPlayerShip = true;
		return isANonPlayerShip;
	}
	
	public void printNoPlayerShip()
	{
		System.out.println("Error: PLAYERSHIP doesn't exist!");
	}
	
	public void printNoNonPlayerShip()
	{
		System.out.println("Error: NONPLAYERSHIP doesn't exist!");
	}
	
	public int getPlayerLives()
	{
		return playerLives;
	}
	
	public int getPlayerScore()
	{
		return playerScore;
	}
	
	public int getElapsedTme()
	{
		return gameTime;
	}
	
	public boolean setPlayerScore(int x)
	{
		playerScore = x;
		return true;
	}
	
	public int getMissileCount()
	{
		IIterator iterator = getIterator();
		int missileCount = -1;
		
		for (int i = 0; i < iterator.size(); i++)
		{
			if (iterator.elementAt(i) instanceof PlayerShip)
			{
				PlayerShip ps = (PlayerShip) iterator.elementAt(i);
				missileCount = ps.getMissicleCount();
			}
		}		
		return missileCount;
	}
	
	
	public boolean setPlayerLives(int x)
	{
		playerLives = x;
		return true;
	}
	
	public boolean addAsteroid() // 'a' - add a new asteroid to the world
	{
		
		Asteroid asteroid = new Asteroid(gameWorldHeight, gameWorldWidth);
		gameObjects.add(asteroid);
		System.out.println("A new ASTEROID has been created.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		return true;
	}
	
	public boolean addNPS() // 'y' - add a NPS to the world
	{
		
		NonPlayerShip nps = new NonPlayerShip(gameWorldHeight, gameWorldWidth);
		gameObjects.add(nps);
		System.out.println("A new NONPLAYERSHIP has been created.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		return true;
	}
	
	public boolean addStation() // 'b' - add SpaceStation to the world
	{
		
		SpaceStation ss = new SpaceStation(gameWorldHeight, gameWorldWidth);
		gameObjects.add(ss);
		System.out.println("A new STATION has been created.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		return true;
	}
	
	public boolean addPS() // 's' - add PS to the world
	{
		
		Boolean playerShipExists = false;
		IIterator iterator = getIterator();
		
		for (int i = 0; i < iterator.size() && !playerShipExists ;i++) // checking to see if a PlayerShip already exists
		{
			if (iterator.elementAt(i) instanceof PlayerShip) // is this object a PlayerShip?
				playerShipExists = true; // if it is , make note of it
		}
		
		if (!playerShipExists) // if PlayerShip doesn't already exist
		{
			PlayerShip ps = new PlayerShip(gameWorldHeight, gameWorldWidth); // create a PlayerShip
			gameObjects.add(ps); // add it to the gameObjects collection
			System.out.println("A new PLAYERSHIP has been created.");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else
			System.out.println("Error: PLAYERSHIP already exists.");
		return true;
		
	}
	
	/* increase player ship speed */
	public boolean increaseSpeed() // 'i'
	{
		
		Boolean playerShip = false; // used for every iteration
		Boolean playerShipDoesExist = false; // stays set to true if a playership exists
		IIterator iterator = getIterator();
		for(int i =0; i< iterator.size();i++) // check all game objects
		{
			playerShip = isPlayerShip(i);
			if(playerShip)
			{
				playerShipDoesExist = true;
				PlayerShip ps = (PlayerShip) iterator.elementAt(i);
				if (ps.getSpeed()< ps.getMaxSpeed()) // if player ship speed isn't at max speed
				{
					ps.setSpeed(ps.getSpeed()+SPEEDINCREMENT);  // update playership speed
					ps.getMissileLauncher().setSpeed(ps.getSpeed()); // update missile launcher speed to match ship speed
					System.out.println("PLAYERSHIP speed increased by 1");
					this.setChanged();
					this.notifyObservers(new GameWorldProxy(this));
				}
				else
					System.out.println("PLAYERSHIP already at max speed.");
			}
		}
		if(!playerShipDoesExist)
			printNoPlayerShip();		
		return true;
	}
	
	/* decrease playership speed */
	public boolean decreaseSpeed() // 'd'
	{
		
		Boolean playerShip = false; // used for every iteration
		Boolean playerShipDoesExist = false; // stays set to true if a playership exists
		IIterator iterator = getIterator();
		for(int i =0; i< iterator.size();i++)
		{
			playerShip = isPlayerShip(i);
			if(playerShip)
			{
				playerShipDoesExist = true;
				PlayerShip ps = (PlayerShip) iterator.elementAt(i);
				if (ps.getSpeed()> 0)	// if playership speed is greater than 0
				{
					ps.setSpeed(ps.getSpeed()-SPEEDINCREMENT);	// set playership speed
					ps.getMissileLauncher().setSpeed(ps.getSpeed()); // update missile launcher speed to match ship speed
					System.out.println("PLAYERSHIP speed decreased by 1");
					this.setChanged();
					this.notifyObservers(new GameWorldProxy(this));
				}
				else
					System.out.println("PLAYERSHIP speed can't be decreased, it's at 0.");
			}
		}
		if(!playerShipDoesExist)
			printNoPlayerShip();
		return true;

	}
	 /*turn PS left by a small amount (change the heading of the ship by a small amount in the counter-clockwise direction).
	 *  Note that determination of the actual new heading is not the responsibility of the game class; it simply instructs 
	 *  the game world that a change is to be made.
	 */
	public boolean turnLeft()  // 'l'
	{
		
		Boolean playerShip = false; // used for every iteration
		IIterator iterator = getIterator();
		
		for(int i = 0; i <iterator.size() && !playerShip;i++) // keep looking until playership has been found
		{
			if (iterator.elementAt(i) instanceof ISteerable)
			{
				playerShip = isPlayerShip(i);
				if(playerShip)
				{
					PlayerShip ps = (PlayerShip) iterator.elementAt(i);
					if (ps.getDirection() == 0) // if playership direction is currently at 0
						ps.setDirection(360 - TURNAMOUNT);
					else
						ps.setDirection(ps.getDirection() - TURNAMOUNT);
					System.out.println("PLAYERSHIP turned " + TURNAMOUNT + " degrees counter-clockwise. dir = " + ps.getDirection() + ".");
					this.setChanged();
					this.notifyObservers(new GameWorldProxy(this));
				}
			}
		}
		if(!playerShip)
			printNoPlayerShip();
		return true;
	}
	/* turn PS right (change the ship heading by a small amount in the clockwise direction).*/
	public boolean turnRight() // - 'r'
	{
		
		Boolean playerShip = false;
		IIterator iterator = getIterator();
		
		for(int i = 0; i <iterator.size() && !playerShip;i++) // keep looking until playership has been found
		{
			if(iterator.elementAt(i) instanceof ISteerable)
			{
				playerShip = isPlayerShip(i);
				if(playerShip)
				{
					PlayerShip ps = (PlayerShip) iterator.elementAt(i);
					if ((ps.getDirection() + TURNAMOUNT) == 360)
						ps.setDirection(0);
					else
						ps.setDirection(ps.getDirection() + TURNAMOUNT);
					System.out.println("PLAYERSHIP turned " + TURNAMOUNT + " degrees clockwise. dir = " + ps.getDirection() + ".");
					this.setChanged();
					this.notifyObservers(new GameWorldProxy(this));
				}
			}
		}
		if(!playerShip)
			printNoPlayerShip();
		return true;
	}
	
	public boolean turnPSMLLeft() // - '<' - turn playership missile launcher left
	{
		
		Boolean playerShip = false;
		IIterator iterator = getIterator();
		
		for(int i = 0; i <iterator.size() && !playerShip;i++) // keep looking until playership has been found
		{
			if(iterator.elementAt(i) instanceof ISteerable)
			{
				playerShip = isPlayerShip(i);
				if(playerShip)
				{
					PlayerShip ps = (PlayerShip) iterator.elementAt(i);
					if (ps.getMissileLauncher().getDirection() == 0)
						ps.getMissileLauncher().setDirection(360 - TURNAMOUNT);
					else
						ps.getMissileLauncher().setDirection(ps.getMissileLauncher().getDirection() - TURNAMOUNT);
					System.out.println("MISSILELAUNCHER turned " + TURNAMOUNT + " degrees counter-clockwise. dir = " + ps.getMissileLauncher().getDirection() + ".");
					this.setChanged();
					this.notifyObservers(new GameWorldProxy(this));
				}
			}
		}
		if(!playerShip)
			printNoPlayerShip();
		return true;
	}
	
	public boolean turnPSMLRight() // - '>' - turn playership missile launcher
	{
		
		Boolean playerShip = false;
		IIterator iterator = getIterator();
		
		for(int i = 0; i <iterator.size() && !playerShip;i++) // keep looking until playership has been found
		{
			if(iterator.elementAt(i) instanceof ISteerable)
			{
				playerShip = isPlayerShip(i);
				if(playerShip)
				{
					PlayerShip ps = (PlayerShip) iterator.elementAt(i);
					if ((ps.getMissileLauncher().getDirection() + TURNAMOUNT) == 360)
						ps.getMissileLauncher().setDirection(0);
					else
						ps.getMissileLauncher().setDirection(ps.getMissileLauncher().getDirection() + TURNAMOUNT);
					System.out.println("MISSILELAUNCHER turned " + TURNAMOUNT + " degrees clockwise. dir = " + ps.getMissileLauncher().getDirection() + ".");
					this.setChanged();
					this.notifyObservers(new GameWorldProxy(this));
				}
			}
		}
		if(!playerShip)
			printNoPlayerShip();
		return true;
	}
	
	/* fire a missile out the front of the PS. If the ship has no more missiles, an error message should be printed; 
	 * otherwise, add to the world a new missile with a location, speed, and launchers heading determined by the ship.
	 */
	public boolean firePSMissile() // 'f'
	{
		
		Boolean playerShip = false; // used for every iteration
		IIterator iterator = getIterator();
		
		for(int i = 0; i <iterator.size() && !playerShip;i++) // keep looking until playership has been found
		{
			playerShip = isPlayerShip(i);
			if(playerShip)
			{
				PlayerShip ps = (PlayerShip) iterator.elementAt(i); 
				if (ps.getMissicleCount() > 0)
				{
					ps.setMissileCount(ps.getMissicleCount() - 1);
					Missile msl = new Missile(ps.getLocation(),ps.getMissileLauncher().getDirection(), ps.getSpeed(), true, gameWorldHeight, gameWorldWidth);
					gameObjects.add(msl);
					System.out.println("PLAYERSHIP has fired a missile!");
					this.setChanged();
					this.notifyObservers(new GameWorldProxy(this));
				}
				else
					System.out.println("PLAYERSHIP has no more missiles!");
			}
		}
		if (!playerShip)
			printNoPlayerShip();
		return true;
	}
	
	/* Launch a missile out the front of the NPS. If the ship has no more missiles, an error message should be printed; 
	 * otherwise, add to the world a new missile with a location, speed, and launchers heading determined by the ship.
	 */
	
	public boolean launchNPSMissile() // - 'L'
	{
		
		Boolean nonPlayerShip = false; // used for every iteration
		Boolean nonPlayerShipDoesExist = false; // stays set to true if atleast 1 nonplayership exists
		IIterator iterator = getIterator();
		
		for(int i = 0; i <iterator.size();i++) // search for all NPSs
		{
			nonPlayerShip = isNonPlayerShip(i);
			if(nonPlayerShip)
			{
				nonPlayerShipDoesExist = true; // atleast 1 NonPlayerShip exists
				NonPlayerShip nps = (NonPlayerShip) iterator.elementAt(i);
				if (nps.getMissileCount() > 0)
				{
					nps.setMissileCount(nps.getMissileCount() - 1);
					Missile msl = new Missile(nps.getLocation(),nps.getMissileLauncher().getDirection(), nps.getSpeed(), false, gameWorldHeight, gameWorldWidth);
					gameObjects.add(msl);
					System.out.println("NONPLAYERSHIP has fired a missile!");
					this.setChanged();
					this.notifyObservers(new GameWorldProxy(this));
				}
				else
					System.out.println("NONPLAYERSHIP has no more missiles!");
			}
		}
		if (!nonPlayerShipDoesExist) // if NonPlayerShip doesn't exist
			printNoNonPlayerShip();
		return true;
	}
	
	/*jump through hyperspace. This command causes the ship to instantly jump to the initial default position in the middle of the 
	 * screen, regardless of its current position. (This makes it easy to regain control of the ship after it has left visible space; 
	 * later we will see a different mechanism for viewing the ship when it is outside screen space.)
	 */
	public boolean jump() // 'j'
	{
		
		Boolean playerShip = false; // used for every iteration
		Boolean playerShipDoesExist = false; // stays set to true if a playership exists
		IIterator iterator = getIterator();
		
		for(int i = 0; i <iterator.size();i++)
		{
			playerShip = isPlayerShip(i);
			if(playerShip)
			{
				playerShipDoesExist = true;
				PlayerShip ps = (PlayerShip) iterator.elementAt(i);
				ps.setLocation(ps.getINITIALSPAWN());	// reset playership to initial spawn location
				System.out.println("PLAYERSHIP jumped through hyperspace!");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
			}
		}
		if (!playerShipDoesExist)
			printNoPlayerShip();
		return true;
	}
	
	/*load a new supply of missiles into the PS. This increases the ships missile 
	 * supply to the maximum. It is permissible to reload missiles before all missiles have 
	 * been fired, but it is not allowed for a ship to carry more than the maximum number of missiles. 
	 * Note that normally the ship would actually have to fly to a space station to reload; for this 
	 * version of the game we will assume the station has transporters that can transfer new missiles 
	 * to the ship regardless of its location.
	 */
	
	public boolean newSupply() // - 'n'
	{
		
		Boolean playerShip = false; // used for every iteration
		Boolean playerShipDoesExist = false; // stays set to true if a playership exists
		IIterator iterator = getIterator();
		
		for(int i = 0; i <iterator.size();i++)
		{
			playerShip = isPlayerShip(i);
			if(playerShip)
			{
				playerShipDoesExist = true;
				PlayerShip ps = (PlayerShip) iterator.elementAt(i);
				ps.setMissileCount(ps.getMAXMISSILES());
				System.out.println("PLAYERSHIP recieved missile supply and now has maximum missile amount");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
			}
		}
		if (!playerShipDoesExist)
			printNoPlayerShip();
		return true;
	}
	
	/* a PSs missile has struck and killed an asteroid; tell the game world to remove a missile and an asteroid 
	 * and to increment the players score by some amount of your choosing. You may choose any missile and asteroid 
	 * to be removed; later well worry about missiles needing to be close to their victims.
	 */
	
	public boolean removeMissileAsteroid() // 'k'
	{
		
		Boolean asteroidExists = false;
		Boolean missileExists = false;
		Missile ms = null;
		Asteroid ast = null;
		IIterator iterator = getIterator();
		
		for(int i =0; i < iterator.size() && (!asteroidExists || !missileExists); i++) // only search until both moveable objects are found
		{
			if ((iterator.elementAt(i) instanceof Asteroid) && !asteroidExists) // if asteroid and haven't found one yet
			{
				ast = (Asteroid) iterator.elementAt(i);
				asteroidExists = true;	
			}
			if ((iterator.elementAt(i) instanceof Missile) && !missileExists) // if missile and haven't found one yet
			{
				ms = (Missile) iterator.elementAt(i);
				if (ms.getIsPlayerMissile() == true)
				{
					missileExists = true;
				}
			}			
		}
		
		if(asteroidExists && missileExists)
		{
			gameObjects.remove(ast); // doesn't necessarily remove the exact object found, which isn't not necessary for A1 yet
			gameObjects.remove(ms);
			this.setPlayerScore(this.getPlayerScore() + 1);
			System.out.println("Player missile destroyed an asteroid! Player score increased by 1.");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else
			System.out.println("Error: Either asteroid or player missile doesn't exist");
		return true;
		
	}
	
	/*a PSs missile has struck and eliminated a NPS; tell the game world to remove a missile and a NPS and to increment 
	 * the players score by some amount of your choosing. You may choose any missile and any NPS to be removed; later well 
	 * worry about missiles needing to be close to their victims.
	 */
	
	public boolean removeMissileNPS() // 'e'
	{
		
		Boolean npsExists = false;
		Boolean missileExists = false;
		Missile ms = null;
		NonPlayerShip nps = null;
		IIterator iterator = getIterator();
		
		for(int i =0; i < iterator.size() && (!npsExists || !missileExists); i++)
		{
			if ((iterator.elementAt(i) instanceof NonPlayerShip) && !npsExists) // if NPS and haven't found one yet
			{
				nps = (NonPlayerShip) iterator.elementAt(i);
				npsExists = true;	
			}
			if ((iterator.elementAt(i) instanceof Missile) && !missileExists) // if missile and haven't found one yet
			{
				ms = (Missile) iterator.elementAt(i);
				if (ms.getIsPlayerMissile() == true)
				{
					missileExists = true;
				}
			}			
		}
		
		if(npsExists && missileExists)
		{
			gameObjects.remove(nps);
			gameObjects.remove(ms);
			this.setPlayerScore(this.getPlayerScore() + 2);
			System.out.println("Player missile destroyed a NonPlayerShip! Player score increased by 2.");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else
			System.out.println("Error: Either NonPlayerShip or player missile doesn't exist");
		return true;
	}
	
	/* a NPSs missile has struck and Exploded a PS; tell the game world to remove a missile and a PS. 
	 * You may choose any missile and to decrement the count of lives left; later well worry about missiles 
	 * needing to be close to their victims.
	 */
	
	public boolean removeMissilePS() // - 'E'
	{
		
		Boolean psExists = false;
		Boolean missileExists = false;
		Missile ms = null;
		PlayerShip ps = null;
		IIterator iterator = getIterator();
		
		for(int i =0; i < iterator.size() && (!psExists || !missileExists); i++)
		{
			if ((iterator.elementAt(i) instanceof PlayerShip) && !psExists) // if PS and haven't found one yet
			{
				ps = (PlayerShip) iterator.elementAt(i);
				psExists = true;	
			}
			if ((iterator.elementAt(i) instanceof Missile) && !missileExists) // if missile and haven't found one yet
			{
				ms = (Missile) iterator.elementAt(i);
				if (ms.getIsPlayerMissile() == false)  // is a NPS missile
				{
					missileExists = true;
				}
			}			
		}
		
		if(psExists && missileExists)
		{
			gameObjects.remove(ps);
			gameObjects.remove(ms);
			if (this.getPlayerLives() != 0) // if player lives not already at 0
			{
				this.setPlayerLives(this.getPlayerLives() - 1); // decrement player lives by 1
				System.out.println("NonPlayerShip missile destroyed a PlayerShip! Player lives decreased by 1.");
				
			}
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			
		}
		else
			System.out.println("Error: Either PlayerShip or NonPlayerShip missile doesn't exist");
		return true;
	}
	
	/* the PS has crashed into an asteroid; tell the game world to remove the ship and an asteroid and to decrement 
	 * the count of lives left; if no lives are left then the game is over. You may choose any asteroid to be removed; 
	 * later well worry about asteroids needing to be close to the ship.
	 */
	
	public boolean removePSAsteroid() // - 'c'
	{
		
		Boolean psExists = false;
		Boolean asteroidExists = false;
		PlayerShip ps = null;
		Asteroid ast = null;
		IIterator iterator = getIterator();
		
		for(int i =0; i < iterator.size() && (!psExists || !asteroidExists); i++)
		{
			if ((iterator.elementAt(i) instanceof PlayerShip) && !psExists) // if PS and haven't found one yet
			{
				ps = (PlayerShip) iterator.elementAt(i);
				psExists = true;	
			}
			if ((iterator.elementAt(i) instanceof Asteroid) && !asteroidExists) // if asteroid and haven't found one yet
			{
				ast = (Asteroid) iterator.elementAt(i);
				asteroidExists = true;
			}			
		}
		
		if(psExists && asteroidExists)
		{
			gameObjects.remove(ps);
			gameObjects.remove(ast);
			if (this.getPlayerLives() != 0) // if player lives not already at 0
			{
				this.setPlayerLives(this.getPlayerLives() - 1); // decrement player lives by 1
				System.out.println("PlayerShip crashed into an asteroid! Player lives decreased by 1.");
			}
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			
		}
		else
			System.out.println("Error: Either PlayerShip or asteroid doesn't exist");
		return true;
		
	}
	
	/* the PS has hit a NPS; tell the game world to remove the NPS and to decrement the 
	 * count of lives left; if no lives are left then the game is over. You may choose any 
	 * NPS to be removed; later well worry about NPS needing to be close to the PS.
	 */
	
	public boolean removeNpsPs() // - 'h'
	{
		
		Boolean psExists = false;
		Boolean npsExists = false;
		PlayerShip ps = null;
		NonPlayerShip nps = null;
		IIterator iterator = getIterator();
		
		for(int i =0; i < iterator.size() && (!psExists || !npsExists); i++)
		{
			if ((iterator.elementAt(i) instanceof PlayerShip) && !psExists) // if PS and haven't found one yet
			{
				ps = (PlayerShip) iterator.elementAt(i);
				psExists = true;	
			}
			if ((iterator.elementAt(i) instanceof NonPlayerShip) && !npsExists) // if NPS and haven't found one yet
			{
				nps = (NonPlayerShip) iterator.elementAt(i);
				npsExists = true;
			}			
		}
		
		if(psExists && npsExists)
		{
			gameObjects.remove(ps);
			gameObjects.remove(nps);
			if (this.getPlayerLives() != 0) // if player lives not already at 0
			{
				this.setPlayerLives(this.getPlayerLives() - 1); // decrement player lives by 1
				System.out.println("PlayerShip crashed into a NonPlayerShip! Player lives decreased by 1.");
				if (this.getPlayerLives() == 0)
					System.out.println("Game over, man!");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
			}	
		}
		else
			System.out.println("Error: Either PlayerShip or NonPlayerShip doesn't exist");
		return true;
		
	}
	
	/* two asteroids have collided with and exterminated each other; tell the game world to 
	 * remove two asteroids from the game. You may choose any two asteroids to be removed; 
	 * later well worry about the asteroids needing to be close to each other. You may ignore 
	 * collisions between NPSs.
	 */
	
	public boolean removeTwoAsteroids() // - 'x'
	{
		
		Boolean asteroid1Exists = false;
		Boolean asteroid2Exists = false;
		Asteroid ast1 = null;
		Asteroid ast2 = null;
		int ast1index = -1; // used to make sure atleast 2 asteroids exist, when first ast is found, this becomes index of that ast in gameobjects
		IIterator iterator = getIterator();
		
		for(int i =0; i < iterator.size() && (!asteroid1Exists || !asteroid2Exists); i++)
		{
			if ((iterator.elementAt(i) instanceof Asteroid) && !asteroid1Exists) // if asteroid and haven't found one yet
			{
				ast1 = (Asteroid) iterator.elementAt(i);
				ast1index = i;	// keep track of this index
				asteroid1Exists= true;	
			}
			// if asteroid and haven't found one yet for this object, and this isn't asteroid1 (based on ast1index == i)
			if ((iterator.elementAt(i) instanceof Asteroid) && !asteroid2Exists && !(ast1index == i )) 
			{
				ast2 = (Asteroid) iterator.elementAt(i);
				asteroid2Exists = true;
			}			
		}
		
		if(asteroid1Exists && asteroid2Exists)
		{
			gameObjects.remove(ast1);
			gameObjects.remove(ast2);
			System.out.println("Two asteroids collided with eachother and were destroyed!");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
			
		}
		else
			System.out.println("Error: Atleast 2 asteroids don't exist");
		return true;
	}
	
	/* one asteroid have collided and impacted the NPS; tell the game world to 
	 * remove them from the game. You may choose one asteroid and one NPS to be 
	 * removed; later well worry about the asteroid needing to be close to NPS.
	 */
	
	public boolean removeAsteroidNPS() // - 'i'
	{
		
		Boolean astExists = false;
		Boolean npsExists = false;
		Asteroid ast = null;
		NonPlayerShip nps = null;
		IIterator iterator = getIterator();
		
		for(int i =0; i < iterator.size() && (!astExists || !npsExists); i++)
		{
			if ((iterator.elementAt(i) instanceof Asteroid) && !astExists) // if asteroid and haven't found one yet
			{
				ast = (Asteroid) iterator.elementAt(i);
				astExists = true;	
			}
			if ((iterator.elementAt(i) instanceof NonPlayerShip) && !npsExists) // if NPS and haven't found one yet
			{
				nps = (NonPlayerShip) iterator.elementAt(i);
				npsExists = true;
			}			
		}
		
		if(astExists && npsExists)
		{
			gameObjects.remove(ast);
			gameObjects.remove(nps);
			System.out.println("An asteroid collided with a NonPlayerShip! Both were destroyed.");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
		else
			System.out.println("Error: Either asteroid or NonPlayerShip doesn't exist");
		return true;
	}
	
	/* tell the game world that the game clock has ticked. Each tick of the game clock has the 
	 * following effects: (1) all moveable objects are told to update their positions according to 
	 * their current direction and speed; (2) every missiles fuel level is reduced by one and any 
	 * missiles which are now out of fuel are removed from the game; (3) each space station toggles 
	 * its blinking light if the tick count modulo the stations blink rate is zero; and (4) the 
	 * elapsed game time is incremented by one.
	 */
	
	public boolean tick() // - 't'
	{
		
		incrementGameTime(); // (4)
		System.out.println("Game time has incremented by one. Time is currently: " + gameTime);
		IIterator iterator = getIterator();
		
		for(int i =0; i < iterator.size(); i++) // check all game objects
		{
			if ((iterator.elementAt(i) instanceof IMoveable)) // (1)
			{
				IMoveable mObj = (IMoveable) iterator.elementAt(i);
				mObj.move();
				
				double shift;
				MoveableObject mv = (MoveableObject) mObj;
				/* the following if/else statements are used to check whether or not current the moveableobject has went out of bounds went it moved*/
				if (mv.getX() > gameWorldWidth) // if moveableobject moved too far east (out of bounds)
				{
					shift =  mv.getX() - gameWorldWidth;
					mv.setX(shift); // wraps around to the left side of screen
				}
				else if (mv.getX() < 0) // if moveableobject moved too far west (out of bounds)
				{
					shift = gameWorldWidth - Math.abs(mv.getX()); // wraps around to right side of screen
					mv.setX(shift);
				}
				if (mv.getY() > gameWorldHeight) // if moveableobject moved too far north (out of bounds)
				{
					shift = mv.getY() - gameWorldHeight;
					mv.setY(shift); // wraps around to the bottom of the screen
				}
				else if (mv.getY() < 0) // if moveableobject moved too far south (out of bounds)
				{
					shift = gameWorldHeight - Math.abs(mv.getY());
					mv.setY(shift);
				}
				
				if (iterator.elementAt(i) instanceof PlayerShip) // need to update missile launcher speed/location for a playership
				{
					PlayerShip ps = (PlayerShip) iterator.elementAt(i);
					ps.getMissileLauncher().setLocation(ps.getLocation()); // update missile launcher location to match PS location
					ps.getMissileLauncher().setSpeed(ps.getSpeed()); // update missile launcher speed to match PS speed
				}
				if (iterator.elementAt(i) instanceof NonPlayerShip) // need to update missile launcher speed/location for a NPS
				{
					NonPlayerShip nps = (NonPlayerShip) iterator.elementAt(i);
					nps.getMissileLauncher().setLocation(nps.getLocation()); // update missile launcher location to match NPS location
					nps.getMissileLauncher().setSpeed(nps.getSpeed()); // update missile launcher speed to match NPS speed
				}
			}			
			else if ((iterator.elementAt(i) instanceof SpaceStation)) // (3) if it's not a IMovable object, check if it's a space station
			{
				SpaceStation ss = (SpaceStation) iterator.elementAt(i);
				if (ss.getBlinkRate() != 0)	// if blinkrate isn't set to 0
				{
					if (this.getElapsedTime() % (ss.getBlinkRate()) == 0) // if the spacestation light should be turned on yet
						ss.setLightOn(true); // turn it on
					else
						ss.setLightOn(false);	// turn it off ( or keep it off if it was already off)
				}
			}
		}
		
		for (int i =0; i < iterator.size(); i++) // (2) check for missiles and reduce their fuel level (FL), and remove them if their FL reduces to 0
		{
			if (iterator.elementAt(i) instanceof Missile) 
			{
				Missile ms = (Missile) iterator.elementAt(i);
				ms.setFuelLevel(ms.getFuelLevel() - 1);
				if (ms.getFuelLevel() == 0)
				{
					gameObjects.remove(i);
					i--; // because an object is removed, the current index either will be point to a new object, or it will be out of bounds.
						 // so this index should be checked again, just in case.
				}
			}
		}
		
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
		return true;
	}

	public void printMap() // 'm' - print a "map" showing the current world state
	{
		
		IIterator iterator = getIterator();
		
		if (iterator.size() > 0)
			for (int i =0; i<iterator.size(); i++)
			{
				System.out.println((iterator.elementAt(i)));
			}
		else
			System.out.println("No GameObjects exist currently.");
	}
	
	// additional methods here to
	// manipulate world objects and 
	// related game state data
}