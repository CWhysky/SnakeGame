# SnakeGame

This is the start of the SnakeGame code. This is the master branch. There are other branches. Please keep this pristine. 

The main classes to look at are Snakey.java and Sprite.java for the basic application. There are several other classes which were created as stubs and are anticipated to be needed. 

# Currently Used
* Snakey.java - Primary main() method and primary source for game, currently.
* Sprite.java - This is a utility class that wraps JavaFX objects and provides some handy methods such as SetPosition.
* IntValue.java - This is used by Sprite.java.

# Anticipated
* Game.java - EMPTY - This is anticipated and may be needed for the whole game management. (Mediator thread.)
* SnakeGame.java - EMPTY - This could be an interface to the game, but could also use Game.java.
* GameBoard.java - EMPTY - This is anticipated to needed for the whole game board when ready.
* GameGrid.java - EMPTY - Probably same thing as GameBoard -- choose one.
* GameViewPort.java - EMPTY - This is anticipated to be the View the user has that is moving around. Not used yet.
* MessageQueue.java - EMPTY - This will be needed for Players to communicate with each other. It should maintaint the connection to the server and "publishing" should just a matter of writing or saving to an object.  
* Snake.java - EMPTY - This may be needed for general Snake object.
* SnakeBody.java - EMPTY - Probably not needed.
* SnakeHead.java - EMPTY - Probably not needed.
* SnakeTail.java - EMPTY  - Probably not needed.

# Players
* Player.java - EMPTY - This is the base class for a player. It should have a Snake.java and GameGrid.java, GameViewPort.java and MessageQueue.java.
* HumanPlayer.java - EMPTY - This is a class for a Human user. It should extend Player.java.
* AIPlayer.java - EMPTY - This a a class for the AI Players. It should extend Player.java.

# Maybe needed for controls
* Keyboard.java - EMPTY - Do we need a keyboard class to handle user input?
* Mouse.java - EMPTY - Do we need a mouse class?

# Milestone 3 (Copy and Paste into our M3 doc):

Menu

-MenuScreenshot1.jpg- 

The menu has a splash screen followed by 3 buttons. The “Start” button will start the game. the “Options” button will direct to the options menu, and “Exit” button will exit the program.

-MenuScreenshot2.jpg-

The Options menu will decide the window’s resolution. Due to the nature of the game, those who play with large screens and high resolution would have an unfair advantage (since they could see the entirety of the game board). We’ve restricted access by limiting the window to 2 resolution sizes, each with their own button that indicates it’s size. Finally there is a “Back” button that redirects back to the Menu screen.


Specifications
There will be two menus which are imported from the javafx library, namely the scene class. The game will initially start with the menuScene layout. The details of the menu elements and attributes (such as buttons, colors, etc) are located separately in a fxml file named SnakeMenuLayout.fxml and SnakeOptionsLayout.xfml respectively. Each menu and the game itself will have it’s own scene.

Use Cases
Use Case: Player 1 decides to start the game, the they see from startin the program is the Menu screen. Player 1 decides to play the game so he clicks start.

Use Case: Player 2 wants to play with a different resolution. After starting the program, she clicks Options which will take her to an options menu with 2 resolution. She clicks on the resolution she prefers then clicks “Back”, which redirects her back to the main Menu.
