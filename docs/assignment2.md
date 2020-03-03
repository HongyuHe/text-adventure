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
The engine is the heart of the system and can be considered the (grand) parent of all the other components. It will contain the entry point of the entire project.

Attributes
- *games: List\<Game\>* - Initially the player will be presented with a list of different games they are able to load and play, this attribute will hold the names of the different options.
- *currentGame: Game* - Once the player has selected a certain game to play, the game data will be loaded from the respective JSON files and a new Game object will be created. This attribute will hold a reference to this currently running game.

Operations
- *handleGameOver()* - When the player either wins or loses the game this function will handle reloading from a prior save, starting a new game, changing the engine state back to the initial menu, or quitting.
- *loadGameList()* - Gets the names of all available games that can be loaded and played. Available games will be folders of JSON game data files where the name of the folder will be the name given in the menu and presented to the player.
- *initializeGame(game: Game)* - Loads the game files selected by the player. Instructs the **Initializer** to load the appropriate JSON files then passes the required parameters to the **Game** constructor and sets *currentGame* to hold the reference to this new game. *initializeGame* will be called when the player selects an option from the initial menu.
- *quit()* - If a game is currently running, this function will prompt the user to save before exiting, otherwise will simply close the engine and exit.
- *startNewGame()* - Once the player has selected a game to play from the initial menu, they can start the game from the initial state without loading a prior save.
- *loadSavedGame()* - If the player wishes to load a prior save, this function will allow them to do so. Save game files hold a list of all prior successful actions (with meta-commands stripped out - this includes things like "save", "load", and "quit"), and these actions are replayed to get the current game into the prior state.
- *saveGame()* - Saves the list of prior successful, non-meta-commands to a file with the game data JSON files of the current game.
- *runGame()* - A loop that runs whilst the current game is not over. This loop will ask the UIHandler to block for user input, then determines if this input needs to be passed to the game or not (e.g. a "save" command is handled by the engine itself, and is not passed to the game). If required, the user input is passed to the game through the game's *handleCommand* function.
- *main()* - The entry point for the application.

Associations

**Initializer**


**CommandFactory**


**Command**


**ChangeStat**


**ChangeState**


**ChangeLocation**


**ListContents**


**Describe**


**TakeItem**


**DropItem**


**UIHandler**


**Game**


**GameEntities**


**Entity**


**Area**


**Item**


**State**


**Character**


**NPC**


**Interactable**


**Player**


**Obstacle**


For each class (and data type) in the class diagram you have to provide a paragraph providing the following information:
- Brief description about what it represents
- Brief description of the meaning of each attribute
- Brief description of the meaning of each operation
- Brief description of the meaning of each association involving it (each association can be described only once in this deliverable)

Also, you can briefly discuss fragments of previous versions of the class diagram (with figures) in order to show how you evolved from initial versions of the class diagram to the final one.

In this document you have to adhere to the following formatting conventions:
- the name of each **class** is in bold
- the *attributes*, *operations*, *associations*, and *objects* are in italic.

Maximum number of words for this section: 3000

## Object diagrams								
Author(s): `name of the team member(s) responsible for this section`

This chapter contains the description of a "snapshot" of the status of your system during its execution. 
This chapter is composed of a UML object diagram of your system, together with a textual description of its key elements.

`Figure representing the UML class diagram`
  
`Textual description`

Maximum number of words for this section: 1000

## State machine diagrams									
Author(s): `name of the team member(s) responsible for this section`

This chapter contains the specification of at least 2 UML state machines of your system, together with a textual description of all their elements. Also, remember that classes the describe only data structures (e.g., Coordinate, Position) do not need to have an associated state machine since they can be seen as simple "data containers" without behaviour (they have only stateless objects).

For each state machine you have to provide:
- the name of the class for which you are representing the internal behavior;
- a figure representing the part of state machine;
- a textual description of all its states, transitions, activities, etc. in a narrative manner (you do not need to structure your description into tables in this case). We expect 3-4 lines of text for describing trivial or very simple state machines (e.g., those with one to three states), whereas you will provide longer descriptions (e.g., ~500 words) when describing more complex state machines.

The goal of your state machine diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 3000

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
