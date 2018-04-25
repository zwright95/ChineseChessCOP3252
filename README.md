# ChineseChessCOP3252
# ChineseChessCOP3252
Project by Zachary Wright and Hao Chai

How to use the game:

To start the game select whether you want to play a new game or resume the last saved game. Only the most recent saved game can be played.

Black is player 1 and red is player 2. Some popular variations of this game have this listed as the other way around but it made more sense logically for the top pieces to go first. The screen is not resizable because the size of the window is important for the proportions of the pieces and the board.

To move pieces press the center of the piece and then the center of the point you are moving it to. The program will alert you if you did something wrong. When a correct piece is clicked a button sound will be heard. When a piece is moved to a correct spot, a swoosh sound will be heard. A player can win by putting their opponent in checkmate or by their opponent not having any possible moves. With some features being extremely difficult to implement in code, like perpetual checks and chases, there is a button that can be pressed to forfeit the game in the case of players only being able to chase each other. When a game is won, the program will present a pop up saying which player won and the application will close when the user closes the pop up.

There are no additional flags that need to be used to run the program. 


Extra features implemented:

There is a save game feature. When the game is started, there is a pop up alert that lets the user select whether they would like to start a new game or load the last saved game. If no saved game exists, a new game will be started. When a user wishes to save their game, they simply press the save button at the top of the screen, the last saved game will be overwritten with the new saved game and the program will exit. 

A button click sound was implemented when a player presses a correct piece and when a player presses either the save or forfeit button. 

A swoosh sound was implemented when a player moves a piece to a correct spot on the board.


Separation of work:

Hao worked on the logic of the game. This includes creating the different types of pieces and the piece movements. This also includes creating the checkmate and check functions. He also created a Pair class for use in the coordinates of the board. He created different functions that return the available moves for each different type of piece on the board. He also created helper functions like one for getting a deep copy of a the Pieces class, one for get the pieces for a specific player, getting the general for a specific player, and finding a specific piece on the board. He made it so a "flying general" situation cannot happen.

Zachary worked on the GUI portion of the project. This includes the drawing of the board and the pieces and finding the proper way to fit the pieces on the board so they are easy to read and understand. He also did the clicking and moving of pieces and detecting if the move and piece were allowed to be moved. He created the gameplay loop where a player can make their move, checking for game scenarios like checkmate, check and stalemate, and changing players. He also created the saving game feature and the playing sound feature. He made the GUI work correctly with the logic of the game. He also wrote a simple stalemate function. 
