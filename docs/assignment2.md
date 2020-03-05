# Assignment 2

Maximum number of words for this document: 12000

**IMPORTANT**: In this assignment you will model the whole system. Within each of your models, you will have a *prescriptive intent* when representing the elements related to the feature you are implementing in this assignment, whereas the rest of the elements are used with a *descriptive intent*. In all your diagrams it is strongly suggested to used different colors for the prescriptive and descriptive parts of your models (this helps you in better reasoning on the level of detail needed in each part of the models and the instructors in knowing how to assess your models).   

**Format**: establish formatting conventions when describing your models in this document. For example, you style the name of each class in bold, whereas the attributes, operations, and associations as underlined text, objects are in italic, etc.

### Implemented feature

| ID  | Short name  | Description  |
|---|---|---|
| F1  | Tags | Code snippets can be tagged via freely-defined labels called tags  |

### Used modelling tool
[StarUML](http://staruml.io/)

## Class diagram									
Author(s): `Anthony Wilkes, Ajay Hitendra Mota`

![Class Diagram](./assets/class_detail.png "Class diagram")

**Engine**
The engine is the heart of the system and can be considered the (grand) parent of all the other components. It will contain the entry point of the entire project and acts as a bridge between the game itself, the UI, and the JSON handling code, whilst also controlling the state of the overall system and handling certain meta-commandBlueprints meant for the engine itself (e.g. saving the game).

Attributes
- *games: List\<Game\>* - Initially the player will be presented with a list of different games they are able to load and play, this attribute will hold the names of the different options.
- *currentGame: Game* - Once the player has selected a certain game to play, the game data will be loaded from the respective JSON files and a new Game object will be created. This attribute will hold a reference to this currently running game.

Operations
- *handleGameOver()* - When the player either wins or loses the game this function will handle reloading from a prior save, starting a new game, changing the engine state back to the initial menu, or quitting.
- *loadGameList()* - Gets the names of all available games that can be loaded and played. Available games will be folders of JSON game data files where the name of the folder will be the name given in the menu and presented to the player.
- *initializeGame(game: Game)* - Loads the game files selected by the player. Instructs the **Initializer** to load the appropriate JSON files then passes the required parameters to the **Game** constructor and sets *currentGame* to hold the reference to this new game. *initializeGame* will be called when the player selects an option from the initial menu.
- *quit()* - If a game is currently running, this function will prompt the user to save before exiting, otherwise will simply close the engine and exit.
- *startNewGame()* - Once the player has selected a game to play from the initial menu, they can start the game from the initial state without loading a prior save.
- *loadSavedGame()* - If the player wishes to load a prior save, this function will allow them to do so. Save game files hold a list of all prior successful actions (with meta-commandBlueprints stripped out - this includes things like "save", "load", and "quit"), and these actions are replayed to get the current game into the prior state.
- *saveGame()* - Saves the list of prior successful, non-meta-commandBlueprints to a file with the game data JSON files of the current game.
- *runGame()* - A loop that runs whilst the current game is not over. This loop will ask the UIHandler to block for user input, then determines if this input needs to be passed to the game or not (e.g. a "save" command is handled by the engine itself, and is not passed to the game). If required, the user input is passed to the game through the game's *handleCommand* function.
- *main()* - The entry point for the application.

Associations
- **_Initializer_** - Composite. The engine must contain an **Initializer** to handle loading games from JSON files. The **Initializer** should not exist outside of the **Engine**.
- **_Game_** - Composite. **Games** should not exist apart from an **Engine**, however an **Engine** is not required to have a current game initialized at all times.
- **_UIHandler_** - Composite. The **UIHandler** should not exist apart from the **Engine**.


**Initializer**

Attributes
- *parser: Gson* - The class for Google's JSON parsing library.

Operations
- *loadGameFiles: GameEntities* - Finds the JSON game data files associated with the given file path. When the player selects a game from the initial menu the name of this game will be sent to the **Initializer** and the **Initializer** will then open the appropriate folder containing the selected game's game data files. The data from these files will be used to construct a **GameEntities** object which will be sent back to the **Engine** so that it can construct an appropriate game.
- *loadEntities: Map\<String, Entity\>* - A sub-function that will load **Entity** objects from the appropriate JSON files into a map of name:entity pairs.
- *loadPreviousInputs: List\<String\>* - Opens and loads into memory the list of commands the player entered when playing previously. These commands will be sent to **Engine** which will replay these commands when loading a saved game.

Associations
- **_GameEntities_** - Shared. The **Initializer** will use JSON files to create game entity objects which will be stored in a **GameEntities** class. This class will be passed to a **Game** object on construction and will act as the base upon which the game is built.
- **_CommandFactory_** - Shared. Some entities described in the JSON files can optionally contain commands that describe how the player can interact with them. Commands will be described in the JSON as a name, e.g. "use" for "use key door", followed by a function name that related to a **Command** class such as "changeLocation" or "takeItem". This data will be sent to the **CommandFactory** which builds and returns the correct **Command** type which can then be placed inside the appropriate object (e.g. a door might contain a changeState **Command** that allows a use to type "use key door" to unlock the door).


**CommandFactory**

Operations
- *createCommand* - Take input from the **Initializer** that has stripped data from the JSON game data files and uses this to create the required **Command** object, preventing the **Initializer** class from having the determine the correct **Command** implementation itself.

Associations
- **_Command_** - Shared. The **CommandFactory** will create and return **Command** objects.


**Command**

Operations
- *apply(target: String): String* - **Command** objects will be used to check the validity of input instructions given by the user, to do this an object specified by the user will be queried for an appropriate **Command** object (e.g. "use key door" will query the "door" object to see if it contains a command called "use"). If the object contains a matching command then the parameter specified by the user will be passed to this **Command** object and if the parameter matches some internal specification (different implementations for different commands), then the instruction succeeds and a result is performed. The success or failure message will be returned as a String which will, eventually, be passed up the **Engine** object that can then send it to the **UIHandler** to print to the terminal.

Associations
- **_Entity_** - Shared. **Command** objects must know about the entities they relate to to allow them to change the attributes of those entities, e.g. a **ChangeState** **Command** would need to inform a related **Obstacle** object to change its state from active to inactive (for example, when unlocking a door).


**ChangeStat**

An implementation of the **Command** interface that acts to change stats associated with a game **Character**, e.g. picking up a sword may increase the player's strength, eating an apple may increase their health, fighting an NPC may reduce health.


**ChangeState**

An implementation of the **Command** interface that operates on **Obstacle** objects. When **Obstacles** are active, they prevent the movement of the player from one particular **Area** to another, when inactive they allow movement. The **ChangeState** **Command** allows **Items** to be used by the player to change an **Obstacle's** state from active to inactive and vice versa.


**ChangeLocation**



**ListContents**

An implementation of the **Command** interface that gets a list of **Item** names held by any **Entity** class that has an inventory. This is likely to be used frequently when listing the player's current inventory or when printing descriptions of the current **Area**.


**Describe**

An implementation of the **Command** interface that gets the String associated with the *describe* attribute in the **Entity** class. This will likely be used frequently as the player moves from **Area** to **Area**, when they look at **Items**, meets **NPCs** etc.


**TakeItem**

An implementation of the **Command** interface that allows a **Character** to add an **Item** in the current **Area** to their inventory.


**DropItem**

An implementation of the **Command** interface that allows a **Character** to remove an **Item** from their **Inventory** and to drop in the current **Area**.


**UIHandler**

Operations
- *displayGamesMenu(games: List\<String\>): void* - Will print the list of available games in a styled menu for the player to choose from.
- *displayMainMenu(): void* - The initial menu presented to the player, serves as a kind of splash screen for Cork.
- *displayGameSubMenu(game: String): void* - Shown once the player has selected the game they would like to play, presents the options to start a new game, load a game, or quit.
- *getInput(): String* - Blocks waiting on user input and passes the raw result back to the **Engine** to handle as needed.
- *clearScreen(): void* - Clears the screen to a blank state.
- *printResult(result: String): void* - Prints the String provided. This will be used frequently to provide the user feedback, for example a description will be printed when the player enters a new **Area**, the success of an action may cause some change in game state (e.g. unlocking a door will notify the player that the door is now unlocked), or the failure of an action to complete (e.g. the input "eat grue" might return a failure message like "You cannot do that", unless a *modder* user makes it possible).


**Game**

Attributes
- *playerWon: Bool* - When the game is over the player has either won the game or lost it, the game is won when the player is holding a specific item, or it may be lost when the player has died.
- *previousCommands: List\<String\>* - The **Game** class should keep track of all previously played moves so that, if the player wishes, they can save their game and reload it later. Loads will replay the list of saved commands, so keeping track of this properly is important. Note that since the **Engine** handles meta-level commands like *save* or *load*, these commands will never get sent to the **Game** object, and so they will never be added to the save game file - this should prevent problematic behaviour like encountering a *load* command during the loading of a save game file.

Operations
- *isGameOver(): Bool* - Returns whether or not the game is over.
- *getPreviousCommands(): List\<String\>* - Returns the list of previous inputs by the player, this will be used when 
- *handleCommand(input: String): String* - The **Engine** will send the **Game** class any relevant input it needs to deal with, this will mainly be input that deals with the interaction between **Entities** such as unlocking doors, interacting with **NPCs**, or moving between **Areas**.

Associations
- **_GameEntities_** - Shared. Since all player input will be in the form of Strings, the **Game** class will need a way to associate String names with specific game **Entities**. The **GameEntities** class will act like a sort of Dictionary to allow this, whilst also providing an interface for the **Game** to interact with **Entities** with.


**GameEntities**

Attributes
- *areas: Map\<String, Area\>* - Allows access of **Area** objects by their name.
- *obstacles: Map\<String, Obstacle\>* - Allows access of **Obstacle** objects by their name.
- *npcs: Map\<String, NPC\>* - Allows access of **NPC** objects by their name.
- *items: Map\<String, Item\>* - Allows access of **Item** objects by their name.
- *player: Player* - Holds a reference to the **Player** object.
- *emptyEntity: Entity* - Holds a reference to an unimportant, generic **Entity** to make carrying out the commands associated with user input more intuitive and less bound to error handling. For example, when an appropriate **Entity** cannot be found for an input (e.g. the user might type "talk merchant" when no merchant is in the current location), then the **Game** will be given this blank **Entity** that can be acted upon without changing anything in the game world, or requiring the **Game** object to handle the missing reference. Instead, commands attempted to be carried out on this empty **Entity** will return error messages like "you cannot do that" to let the player know they did something wrong, as part of an error handling system invisible to the **Game** class.
- *gameOverItem: Entity* - Holds a reference to the **Item** the player needs to be holding for them to win the game.

Operations
- *findEntityOrElse(name: String): Entity* - Returns a reference to the **Entity** object matching the given name, or a reference to a blank, generic **Entity** as described above.

Associations
- *Entity* - Shared. The **GameEntities** class organises, and provides an access point for, all the **Entities** in the game world.


**Entity**

Attributes
- *name: String* - All **Entities** should have a unique name that they can be referenced by.
- *description: String* - All **Entities** should have an accompanying description to allow interaction with the **Describe** **Command**.
- *active: Boolean* - Indicates whether the **Entity** object is active or not. Inactive objects should not be present in the game world, and this attribute makes it possible for objects to be destroyed (with a garbage collector - not currently planned or implemented - able to remove inactive objects from **GameEntities** collection).


**Area**

Attributes
- *inventory: Set\<String\>* - Holds the names of all **Items** in the current area. **Entities** do not hold references to other **Entities**, but instead all interaction happens through the **GameEntities** class, in a data-oriented manner.
- *npcs: Set\<String\>* - Holds the names of all **NPCs** that are in the given **Area**.
- *connections: Map\<String, String\>* - Holds the names of all possible directions for the given **Area**, mapped to the name of the respective **Area**. For example, the *garden* **Area** might connect to the *palace* **Area**, and so *garden's* *connections* Map might contain the key:value pairs: "north":"palace" and "palace":"palace", allowing the player to type either "move north" or "move palace" and get the desired result.
- *obstacles: Set\<String\>* - Holds the names of all **Obstacles** in the given **Area**, allowing their descriptions to be printed when the player enters a room.

Operations
- *connectsTo(name: String): Boolean* - Verifies whether or not the given **Area** connects to some other **Area**.
- *getConnection(name: String): String* - Converts the name of a direction to the name of an **Area**.


**Item**

Attributes
- *consumable: Boolean* - Indicates whether or not the **Item** should be destroyed on usage.


**Stat**

Attributes
- *value: Integer* - Holds the value of a given stat, e.g. the player may have *10* strength or *30* health.
- *name: String* - The name of the given stat, e.g. "health" or "strength".


**Character**

Attributes
- *inventory: Set\<String\>* - Holds the names of the **Items** held by the given **Character**.
- *stats: Set\<String, Stat\>* - Holds the **stats** applicable to the given **Character**


**NPC**

Attributes
- *isFriendly: Boolean* - Indicates whether interaction with the given **NPC** will initiate combat or conversation.


**Interactable**

Attributes
- *emptyCommand: Command* - Provides a generic, blank **Command** object that simplifies error handling when the **Game** class is trying to carry out actions.
- *findCommandOrElse(name: String): Command* - Returns the **Command** associated with the given name, or else the *emptyCommand* described above.


**Player**

Attributes
- *currentLocation: String* - Indicates the name of the **Area** the player is currently in.
- *commands: Set\<Command\>* - Indicates which **Command** objects relate to the player.


**Obstacle**

Attributes
- *state: Boolean* - Indicates whether or not the given **Obstacle** is currently active and thus preventing movement to a certain location.
- *requiredObject: String* - Holds the name of the **Item** that can change the state of the given **Obstacle**, e.g. a "key" is required by a door, or a "lantern" by darkness.
- *commands: Set\<Command\>* - Holds the list of **Commands** that relate to the **Obstacle**.
- *currentLocation: String* - The name of the location the **Obstacle** is found in.
- *blocks: String* - The name of the **Area** that the **Obstacle** prevents movement into.


## Object diagrams								
Author(s): `name of the team member(s) responsible for this section`

This chapter contains the description of a "snapshot" of the status of your system during its execution. 
This chapter is composed of a UML object diagram of your system, together with a textual description of its key elements.

`Figure representing the UML class diagram`
  
`Textual description`

Maximum number of words for this section: 1000

## State machine diagrams									
Author(s): `Anthony Wilkes, Ajay Hitendra Mota`

In this section we will describe two components of the system that hold different states. There are the **Engine** class and the **UIHandler** class.

### Engine
![Engine State Machine](./assets/engine_state.png "State machine diagram for the Engine class")

The **Engine** class will be the main, top-level control point for the system as a whole. On system start the **Engine** will load the list of available games that the user can play so as to present them in a menu. This list will be determined by the **Initializer** as it is the **Initializer** that should be responsible for interactions with game files. If the **Initializer** fails at any point during this process, it should return a reason why and this error response should be printed to the console before the system exits.

Once the game list has been loaded the user will be shown the main menu which waits either for the user to exit or for a game to be selected. If a game is selected, the **Engine** will notify the **Initializer** which game files to load, and will construct an associated **Game** class (this process is not shown here as it does not relate to the **Engine's** state or the transitions between them). Once the **Game** class is ready, the **Engine** will display the game's menu, which presents the user with the options: *start new game*, *load game*, and *quit*.

If the user chooses to start a new game, the **Engine** will enter into the *game running* state, however if the user chooses to load a game, the **Engine** will notify the **Initializer** that it requires the list of previously played moves (if any), and will start the game without entering the *game running* state. This allows the **Engine** to feed previous player input into the game without requiring the user to type anything in. Since the game world is deterministic, this allows games to be loaded by replaying every action the player had previously typed and end up in the same state as when they saved the game originally.

Once this load process has completed (or if the user chose to start a new game), the **Engine** enters the *Game Running* state, where it will act as a bridge between the **UIHandler** and the **Game** class. Commands typed by the user will first be sent to the **Engine** which will only send them to the **Game** class if appropriate. For example, if the player chooses to save their game, this should be handled by the **Engine** as it does not relate to knowledge the **Game** should have - the **Game** knows about **NPCs** and **Areas**, but not about files and folders.

If the user chooses to quit the game whilst in the *Game Running* state, then the **Engine** should intercept this request and prompt the user to save their game. If the user chooses to do so, this is done just before exiting, otherwise the **Engine** just exits directly.

Finally, if the game ends in the *Game Running* loop (i.e. the player obtains the item which ends the game, or the player dies), then the **Engine** will handle this appropriately - printing some message to let the user know what happened, and then returning back to the main menu to allow the user to choose to play a new game or exit.

### UIHandler
The state machine diagram for the UIHandler is as follows :-
![State machine diagrams](./assets/state_ui.png "State machine diagram 1")
The UI as mentioned earlier will make use of a terminal window that is called using the native terminal application provided by the Operating System and otherwise open a window using the *javax.swing* package. This functionality of opening an application window will be provided by the *Text-IO* package.

The general states can be briefly explained prior to diving into their details as follows :-
- On running the JAR file an application window is opened up as explained earlier
- The UI then changes the state to a **Game Menu Display** that allows to user to select the game or quit the application
- On selecting the *Select Game* option, a list of different games will be displayed to the user that are available on disk.
- On selecting a particular game the user could then start a new game or load a previously saved game or even quit the game.
- If the user decides to start a new game or loads a game, the UI enters a new state that can be called the **In Game Display**.
- Here the menu is cleared from the terminal and the appropriate game files are loaded in the background.
- The player's starting location is displayed and the UI waits for user input.
- The success or failure message of each command is displayed to the user and the state returns to waiting for user input.
- The application keeps alternating between the two internal states till the user does not enter the `quit` command.
- On `quit` the application window is closed and the application exits. 

The UIHandler will exist in two major states with multiple internal sub-states. The description of the major states are as follows:-
- Game Menu Display: 
- In Game Display:

## Sequence diagrams									
Author(s): `name of the team member(s) responsible for this section`

This chapter contains the specification of at least 2 UML sequence diagrams of your system, together with a textual description of all its elements. Here you have to focus on specific situations you want to describe. For example, you can describe the interaction of player when performing a key part of the videogame, during a typical execution scenario, in a special case that may happen (e.g., an error situation), when finalizing a fantasy soccer game, etc.

For each sequence diagram you have to provide:
- a title representing the specific situation you want to describe;
- a figure representing the sequence diagram;
- a textual description of all its elements in a narrative manner (you do not need to structure your description into tables in this case). We expect a detailed description of all the interaction partners, their exchanged messages, and the fragments of interaction where they are involved. For each sequence diagram we expect a description of about 300-500 words.

The goal of your sequence diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 3000

## Implementation									
Author(s): `name of the team member(s) responsible for this section`

In this chapter you will describe the following aspects of your project:
- the strategy that you followed when moving from the UML models to the implementation code;
- the key solutions that you applied when implementing your system (for example, how you implemented the syntax highlighting feature of your code snippet manager, how you manage fantasy soccer matches, etc.);
- the location of the main Java class needed for executing your system in your source code;
- the location of the Jar file for directly executing your system;
- the 30-seconds video showing the execution of your system (you can embed the video directly in your md file on GitHub).

IMPORTANT: remember that your implementation must be consistent with your UML models. Also, your implementation must run without the need from any other external software or tool. Failing to meet this requirement means 0 points for the implementation part of your project.

Maximum number of words for this section: 2000

## References

References, if needed.
