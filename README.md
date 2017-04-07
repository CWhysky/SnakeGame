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

