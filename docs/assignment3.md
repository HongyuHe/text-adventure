# Assignment 3

### Summary of changes of Assignment 2
Author(s): `Anthony Wilkes`

- *"Object diagrams should not have arrows"*, Ferch42 (TA)  
   **These arrows were removed form the UML.**

- *"Wait for user input does not correspond to an event, so the wait for input transition is redundant. It is an activity the object performs"*, Ferch42 (TA)  
   **Wait for input transitions were removed from the UML.**

- *"'p' 's' and 'ge' are vague names."*, anhphi99 (reviewer)  
    **Short variable names were confined to very short scopes only, and limited to have only one single letter variable in scope at a time.**

- *"Dictionary package holds one class, which creates GameEntities, -and- has the functionality 'findEntityOrElse'. (I don't know if this is typical naming that I'm not familiar with yet, but I'd say its unclear what happens when 'else'.)"*, Ece-Doganer (reviewer)  
   - Made null object names consistent (team choice, linked to the above)  
   
   **All getXOrElse functions were all renamed to getXOrDefault, and all null object classes were renamed to Default\<ClassName\> to make the intention clearer.**

- *"EmptyEntity and GameOverItem are missing in class diagram and description. The save and load features, including deserialiser package contents, is missing in class diagram and description as well"*, Ece-Doganer (reviewer)  
   **These classes and features were added where relevant.**

### Application of design patterns
Author(s): `Anthony Wilkes`

![Design Patterns Class Diagram](assets/A03/design_patterns.png "Design patterns class diagram")

| ID  | DP1 |
|---|---|
| **Design pattern**  | State Machine |
| **Problem**  | The Engine class needed to control the state of the system as a whole, with particular actions required in each state. At the end of assignment 2, the code inside the Engine class was beginning to show signs that it was unmaintainable (switch statement to change state), and lacked robustness (state could technically be changed to anything from anywhere). |
| **Solution**  | Application of the pattern moved the responsibility for changing state into separate classes - this allowed the state changing code in Engine to be localised to a single point, and ensured that more states could be easily added in the future. |
| **Intended use**  | The Engine object now holds a reference to the EngineStateMachine object which holds a reference to the current state object. When Engine is ready it asks the state machine is execute the current state, which asks the current state object to call the appropriate function in Engine, after which it changes to the next state. |
| **Constraints**  | Since the state classes now call functions in Engine, the increase in robustness has lead to an increase in the cognitive load required to understand the Engine class. However, the engine_state package is very small, and almost every class acts identically, so this increase should only be minor. |
| **Additional remarks**  | None. |

| ID  | DP2 |
|---|---|
| **Design pattern**  | Singleton |
| **Problem**  | To maintain consistent state, the EngineStateMachine was made into a singleton. If this were not the case, the Engine class could end up with multiple states in different stages doing different things. |
| **Solution**  | Turning the EngineStateMachine into a singleton means that, if someone tries to make a new EngineStateMachine object in the Engine, they will instead end up with multiple references to the same object. This means that there will be one, and only one, state for the Engine at all times. |
| **Intended use**  | A single instance of the EngineStateMachine is made at initialisation, with the creation of more being prevented through a private constructor. Any class wanting to access this machine must do so through a static method that returns a reference to this single object. |
| **Constraints**  | A singleton reduces the re-usability of the code as it makes it impossible to use this class in multiple scenarios. However, the EngineStateMachine is already very specific to the Engine class, and there is no other part of the system that requires a similar state machine, so this is an acceptable cost. |
| **Additional remarks**  | None. |

| ID  | DP3 |
|---|---|
| **Design pattern**  | Null Object |
| **Problem**  | Since Strings are an integral part of the project (the user will be typing instructions), it was immensely important to handle errors efficiently. Since the user is part of a tight feedback loop, it was considered fine for the system to fail if the user made a mistake, and to then immediately ask for a correction. |
| **Solution**  | When searching for an object with a name matching the user's input, if the object does not exist, a null object can be returned instead. This null object can be operated upon without any special cases needing to be handled, simplifying the code. |
| **Intended use**  | Null objects (DefaultCommand and DefaultEntity) can be returned from any findXOrDefault method in the GameEntities class. These objects implement all expected functionality, but instead of printing some successful result, they will return an indication of what went wrong, thus making error handling an inherent part of the system's operation. |
| **Constraints**  | The cognitive load to use GameEntities is increased, however the resulting code is simpler and can be understood without requiring to understand this functionality. |
| **Additional remarks**  | None. |

| ID  | DP4 |
|---|---|
| **Design pattern**  | Singleton |
| **Problem**  | The DefaultEntity class is a null object which causes no side-effects when interacted with, creating multiple would be a waste of resources. |
| **Solution**  | The DefaultEntity was made into a singleton so that any reference to it would interact with the same object instead of creating a new one. |
| **Intended use**  | Whenever a DefaultEntity is required (e.g. GameEntities cannot find an entity with the given name), a reference to the singleton is returned instead. |
| **Constraints**  | DefaultEntity can no longer contain any unique state, however, since this would violate the principle of a null object, this should cause no issues. |
| **Additional remarks**  | None. |

| ID  | DP5 |
|---|---|
| **Design pattern**  | Facade |
| **Problem**  | Reading the JSON game files is a relatively complex process with many different methods needing to be called in the correct order and several different object types being involved. |
| **Solution**  | The **Initializer** provides a simple front-end to this complexity that allows the **Engine** to call a single method which then handles all of the complexity internally. |
| **Intended use**  | To simplify the creation of different **Game** objects when reading the associated JSON files. |
| **Constraints**  | The **Engine** can no longer ask for specific files to be read, or specify any details about how the files are read. |
| **Additional remarks**  | None. |

| ID  | DP6 |
|---|---|
| **Design pattern**  | Factory Method |
| **Problem**  | The construction of command objects cannot be done directly from the JSON, and so an intermediate **CommandBlueprint** is created first. This **CommandBlueprint** needs to then be converted into the correct command object type. |
| **Solution**  | The **CommandFactory** provides a single method that the **Initializer** can call to create the correct command objects from the given **CommandBlueprint**. |
| **Intended use**  | Instead of having to handle the sub-typing itself, the **Initializer** can have the **CommandFactory** abstract this complexity away. |
| **Constraints**  | Changes to the structure of the JSON will now ripple out across several classes. |
| **Additional remarks**  | None. |


## Class diagram									
Author(s): `Anthony Wilkes`

![Class Diagram](assets/A03/class_detail.png "Class diagram")

### Engine

The **Engine** is the entry point of the system and acts as a central 'heart' that controls the overall state. It is responsible for helping each macro-component communicate with the others by routing data where it needs to go as well as triggering certain meta-level game commands.

##### Operations

- *run(): void* - A continuous loop that executes functions according to the current state until something causes the *engine* to exit.

- *runGame(): void* - A continuous loop that maintains the feedback loop with the user. This function will run until the game ends, and will handle each user command as needed. Meta-level commands (loading, saving, quitting) will be handled by the *engine*, whilst other commands are sent directly to the *game* to handle as appropriate.  

- *saveGame(): void* - Asks the current *game* for a list of all previously entered commands and saves them to  a file, overwriting any previous save.

- *loadGame(): void* - Opens a save file (if it exists), and sends all the contained commands to the *game* to return the world to the same state as when it was saved.

- *loadGameList(): List\<String\>* - Looks into the local directory for a folder called 'games' (which must exist) and gathers the names of all contained folders - these folders are the names of the games that are available to play.

##### Associations

- **UIHandler** composition - The **Engine** uses the **UIHandler** to present data to the user and to collect their requests. The **UIHandler** should not exist without an **Engine** to drive it - it has no internal understanding of its own state (although it has a state, it is controlled by the **Engine**) and would has no purpose aside from handling the user interface interaction for the **Engine**.

- **Game** composition - As with the **UIHandler**, the **Game** class requires the **Engine** to exist as it is unable to control its own behaviour. No other class in the system should be able to create a **Game** - it belongs solely to the **Engine**, and cannot exist without an **Engine** to run it.

- **Initializer** composition - The **Engine** uses the **Initializer** to load all of the required game files from JSON to Java objects. Since only the **Engine** can create a **Game** there is no other class in the system that would require the **Initializer** and so the **Initializer** is bound to the **Engine** and should not exist without it.

- **EngineStateMachine** composition - Since state is an integral part of the **Engine** it requires a robust method for handling it. The **EngineStateMachine** fills this roll - naturally, it cannot exist without an **Engine** to be applied to.


### UIHandler

Acts as a bridge between the system and the user. The **UIHandler** deals with all input/output operations including setting up and running a terminal - it prints information passed to it by the **Engine** and sends back any commands entered by the user.

##### Attributes

- *textIO: final TextIO"* - Part of the **TextIO** library. This object handles getting input from the user.

- *terminal: final TextTerminal<?>* - Part of the **TextIO** library. This is the terminal that will be presented to the user.

##### Operations

- *displaySplashScreen(): String* - Prints the initial splash screen to the user and waits for them to make a choice (continue or quit) and sends the result to the **Engine**.

- *displayMainMenu(games: List<String>): String* - Prints the main menu to the user. This consists of a list of games that are available to be played. The user's choice is returned to the **Engine**.

- *displayGameMenu(game: final String): String* - Prints the games menu to the user. This allows the user to start a new game, load a game, or quit. The choice is returned to the **Engine**.

- *displayError(error: final String): void* - Allows the **Engine** to print an error. This may happen if there are no games available to be played, or if certain things go wrong during loading.

- *exit(): void* - Ensures that the **TextIO** terminal is closed correctly.

- *clearScreen(): void* - Clears the screen of content.

- *getInput(): String* - Asks for input from the user.

- *print(string: String): void* - Prints a string to the terminal.

- *promptUser(prompt: final String): boolean* - Prompts the user to answer a yes/no question and returns the result.


### EngineStateMachine

Since state is an integral part of **Engine** it is important that the method for handling it is robust. The **EngineStateMachine** handles all actions and transitions for **Engine** whilst also improving maintainability and extensibility.

##### Attributes

- *fsm: EngineStateMachine* - Since the state of the **Engine** should always be globally consistent, the **EngineStateMachine** is a singleton. This prevents any accidental creation of a new state handler. The *fsm* is the only instance of the **EngineStateMachine** that should exists in the system.

- *state: IState* - Holds the current state object.

##### Operations

- *instance(): EngineStateMachine* - Returns the singleton instance of the state machine.

- *execute(Engine e): void* - Called by **Engine**. The state machine passes the current state object a reference to the *engine* which calls the appropriate functions, and then the state is switched with the next state in the order. This means that the state objects control the actions of **Engine**, ensuring no deviation from intended behaviour.

##### Associations

- **IState** composition - State objects have no purpose outside of the **EngineStateMachine** and so should not exist without it.


### IState

Provides an API for the **EngineStateMachine** to interact with the state objects.

##### Operations

- *next(s: EngineStateMachine) void* - Changes the current state object in **EngineStateMachine** for the next in line after this one.

- *execute(e: final Engine): void* - Calls functions in **Engine** based on the state.

##### Associations

- **GameMenu**, **HomeScreen**, **MainMenu**, and **GameRunning** interface realizations - Each implements the operations defined by **IState**.


### HomeScreen, MainMenu, GameMenu, GameRunning

Provide functionality to the operations defined by **IState**. Each class decides which functions it calls in **Engine** and which state comes next in the order (HomeScreen -> MainMenu -> GameMenu -> GameRunning -> HomeScreen).


### Game

Turns user commands into instructions that can be carried out by the game, then performs the requested actions and returns the result.

##### Attributes

- *previousCommands: List<String>* - A list of all commands typed in by the user. This history allows commands to be saved into a file and replayed when a game is loaded.

##### Operations

- *handleCommand(input: final String): String* - Takes user input and executes the requested actions (if possible) before returning the result.

- *isGameOver(): boolean* - Returns whether or not the game is over.

- *playerVictory(): boolean* - Returns whether or not the player won the game.

##### Associations

- **GameEntities** shared aggregation - The **Game** requires a collection of entities to act upon, so must know about **GameEntities**, however, since the **Initializer** must also know about **GameEntities** during the deserialization of the JSON files, this relationship cannot be exclusive in the sense that composition is (as if **GameEntities** were a composite, then it could not exist without **Game** meaning it could not be used by the **Initializer** without creating a **Game** which would violate the composition between **Game** and **Engine**).

### GameEntities

Acts as a dictionary for converting the strings entered by the user into objects that exist in the game world. This allows every object in the game to access other objects through unique names. As a result this means that no objects need to hold references to any other objects, and so the **GameEntities** class can keep track of everything in a single, localised place. 

##### Attributes

- *itemEntities: Map\<String, Item\>* - A collection of all **Items** in name:object pairs.

- *areaEntities: Map\<String, Area\>* - A collection of all **Areas** in name:object pairs.

- *obstacleEntities: Map\<String, Obstacle\>* - A collection of all **Obstacles** in name:object pairs.

- *npcEntities: Map\<String, Npc\>* - A collection of all **Npcs** in name:object pairs.

- *gameOverItem: Item* - A reference to the *GameOverItem* for the current game.

- *player: Player* - A reference to the game's *Player*.

- *defaultEntity: DefaultEntity* - A reference to the *DefaultEntity*.

##### Operations

- *getEntityOrDefault(final String entity): Entity* - Searches all game objects and collections of game objects and returns the object which matches the given name. If such an object cannot be found the *DefaultEntity* is returned.


- getXOrDefault:
    - *getAreaOrDefault(final String area): Area*
    - *getItemOrDefault(final String item): Item*
    - *getObstacleOrDefault(final String obstacle): Obstacle*
  - All search for the specified object in the relevant collection, and if no matching name can be found the *DefaultEntity* is returned. 

##### Associations

- **Initializer** directed association - The **Initializer** builds the **GameEntities** class by reading a game's JSON files, creating the respective objects, and passing them into the **GameEntities'** constructor. The **Initializer** must therefore know about the **GameEntities**, however it is not a 'part' of the **Initializer**, so a shared or composite relationship would be inaccurate.

- **Entity** shared aggregation - The **GameEntities** class is made up of **Entity** objects and  - although in the code the **Entity** objects only exist inside the **GameEntities** collections - the model still allows **Entity** objects to exist without a **GameEntities** object owning them. 


### Entity

The abstract description of a generic **Entity** with the methods and attributes that **Entity** objects are required to provide.

##### Attributes

- *commands: Map<String, ICommand>* - A collection of all commands that can be performed on the given **Entity**, held in name:object pairs.

##### Operations

- *findCommandOrDefault(final String cmd): ICommand* - Searches for a command with the given name and returns it, or returns the **DefaultCommand** if no appropriate command could be found.

##### Associations

- **ICommand** directed association - All **Entities** contain a collection of **ICommand** objects, however the model does not require **ICommand** objects to *only* exist within **Entity** objects.

- **Locatable**, **Area**, **Item**, and **Npc** generalizations - Each is a kind of **Entity**.


### Area

Represents a location in the game world where a player can stand.

##### Attributes

- *obstacles: Set\<String\>* - Names of all the **Obstacle** objects within the given area.

- *npcs: Set\<String\>* - Names of all the **Npc** objects within the given area.

- *connections: Set\<String\>* - Names of all the **Area** objects that can be reached from the given area.


### Item

Represents an object in the game world that can be interacted with.

##### Attributes

- *consumable: boolean* - A flag to indicate whether this object can be used multiple times, or just once.


### Npc

Represents a Non-player character in the game world.


### Locatable

An abstract description of an **Entity** that also needs to know which **Area** it is currently at.

##### Attributes

- *currentLocation: String* - The name of the location the **Locatable** is at.

##### Associations

- **DefaultEntity**, **Obstacle**, and **Player** generalisation - Each is a type of **Locatable**.


### Obstacle

An **Obstacle** is an entity in the game world that can impede the player's progress - this could be something literal like a door, or something more abstract like darkness. **Obstacle** objects require particular **Item** objects to be used with them to allow passage (e.g. a key).

##### Attributes

- *state: boolean* - Indicates whether or not the *Obstacle* is active (i.e. it 'true' it is blocking the player's movement, if 'false' the player can pass). 

- *blocks: String* - The name of the *Area* that the *Obstacle* is blocking. 

- *requiredObject: String* - The name of the *Item* that can change the state of the *Obstacle*.

- *message: String* - The message that is printed when the player tried to move past an **Obstacle** that is still active.


### Player

Represents the player character in the game world.

##### Operations

- *isDead(): boolean* - Indicates whether the player is alive or dead (i.e. is their health <= 0). 


### DefaultEntity

A singleton, null object representation of any **Entity** - all operations that can be performed on an **Entity** or a **Locatable** can be performed on the **DefaultEntity** to no effect.

##### Operations

- *instance(): DefaultEntity* - A static method that returns a reference to the **DefaultEntity** singleton.


### Initializer

Sets up all the objects required to build a **Game** by being a central point that owns the deserializers and instructs their actions.

##### Operations

- *loadGameFiles(game: String): GameEntities* - Entry point for **Engine** to call when it requires a game's files to be loaded.

- *load(jsonLocation: String, deserializer: JsonDeserializer<?>, c: Class<T>): ArrayList<T>* - Reads the appropriate JSON files for a certain collection of game objects.

- *loadSingleEntity(jsonLocation: String, deserializer: JsonDeserializer<?>, c: Class<T>): T* - Same as *load*, but works when the JSON files contains only a single game object. 

- *populateCommands(entities: GameEntities): void* - Goes through all the objects in the **GameEntities** collections and ensures that they have valid **Command** objects (this is not automatically done when reading the JSON files).

- *createCommandMap(e: Entity): Map<String, ICommand>* - Converts the **CommandBlueprint** objects into **Command** objects.

##### Associations

- **Deserializer** composition - The **Initializer** contains and commands a set of **Deserializers**. These **Deserializers** should not exist outside of the **Initializer** as it acts as the wrapper to the deserializers for the rest of the system. 

- **CommandFactory** directed association - The **Initializer** uses the **CommandFactory** to create objects that implement the **ICommand** interface, however the **CommandFactory** is not a component of the **Initializer** and nor is it unable to exist outside of it.


### Deserializer

An abstract class that provides a base set of shared methods for the various deserializer subclasses. The deserializers are responsible for reading the JSON game files into java objects.

##### Operations

- *createSet(jArray: JsonArray): Set\<String\>* - Creates a set of Strings from the given JSON array.

- *createArray(jArray: JsonArray): ArrayList\<String\>* - Creates an array of Strings from the given JSON array.

- *createMap(jObject: JsonObject): HashMap\<String, T\>* - Creates a HashMap of String:T pairs for the given type, T, from a JSON object.

- *createCommandSet(jArray: JsonArray): Set\<CommandBlueprint\>* - Creates a Set of **CommandBlueprint** objects from the given JSON array.

- *createObject(jObject: JsonObject): CommandBlueprint* - Creates a **CommandBlueprint** from the given JSON object.

##### Associations

- **CommandBlueprint** directed association - The **Deserializer** objects cannot create **ICommand** objects directly, and so must use an intermediary **CommandBlueprint** to convert from the JSON data to an actual command.

- **AreaDeserializer** ... **PlayerDeserializer** generalization - Each of the type-specific deserializers is a subclass of the **Deserializer** and provide a non-abstract implementation.


### CommandBlueprint

An intermediary object used by the deserializers that allows **ICommand** objects to be easily described in the JSON as **CommandBlueprint** objects which are then converted into the correct command when needed.

##### Attributes

- *name: String* - The name that the user must type to activate this command.

- *function: String* - The name of the command class to create.


### AreaDeserializer, ItemDeserializer, NpcDeserializer, ObstacleDeserializer, and PlayerDeserializer

##### Operations

- *deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): \[Area, Item, Npc, Obstacle, or Player\]* - Reads the JSON object and uses the contained data to create the respective class.


### CommandFactory

Helps convert a **CommandBlueprint** into a command object.

##### Operations

- *createCommand(cmd: final CommandBlueprint, parent: final Entity): ICommand* - Creates a new command object based on the **CommandBlueprint** given. The name of the *CommandBlueprint* will indicate which command type to create.

##### Associations

- **ICommand** directed association - The **CommandFactory** creates objects that implement the **ICommand** interface.


### ICommand

The interface used by command objects.

##### Operations

- *apply(object: final String): String* - Performs the respective command's action with the given object's name.

##### Associations

- **DefaultCommand** ... **TakeItem** - Each implements the **ICommand** interface.


### TakeItem

Allows a user to pick up an *Item*.


### ListStats

Allows the stats of an **Entity** to be listed.


### ChangeState

Changes the state of **Obstacle** objects so they either allow or prevent movement.


### ChangeLocation

Allows moving a **Locatable** **Entity** from one **Area** to another.


### Describe

Returns the full description of an **Entity**.


### ListContents

Returns a list of all **Item** objects contained in an **Entity**.


### Fight

Allows the player to fight with an **Entity**.


### DropItem

Allows the removal of **Item** objects from the inventory of an **Entity** into their current **Area**.


### ChangeStat

Changes the specified stat of an **Entity**.


### DefaultCommand

Provides a method to call for all expected command functionality, but the methods will have no effect - the return value will always be a message that indicates to the command they have tried to use is invalid for whatever reason.


## Object diagrams								
Author(s): `Jim Cuijpers`

![Object Diagram](assets/A03/object_diagram.png "Object diagram")

The colours in the above diagram indicate which parts of the object diagram has been changed since the previous version of the object diagram which has been submitted
in the previous assignment. 

- **Yellow colored objects:**   Objects that have been modified
- **Green colored objects:**    Objects that have been added  
- **White colored objects:**    Objects that have not been modified or added
    
The system is in a state wherein the game "YAZG" is loaded and running and the user just inputted the command `eat apple` into the system. 

- **engine:** The engine object is the main controlling object of the system itself. It is responsible from running a game and holds a state which is referenced by the `Engine state machine`. 
                The list of games found on the system is saved as a list of strings called `games`, the selected game is stored as an object called `currentGame` and the name of the selected game is stored as a string called `gameName`, 
                the current running state is stored as a boolean `running`. The engine handles the input given by the user through the uiHandler class and the engine builds the `currentGame` with the help of the `Initializer` class.
                
- **uiHandler:** The uiHandler is responsible for displaying the game to the user.  It holds the colour theme and the logo and is responsible for taking in the input of the user
                 The uiHandles does so by using TextIO.

- **game:** The game object holds the state of the game that has been initialized. It keeps track of the moves a player make throughout the game as a list of command strings in the variable `previousCommands`, in the         
            current snapshot it is visible that the user has made only one move so far and that is: "eat apple", which was inputted into the console. The last variable is an object of the type `GameEntities`.  
            This is the main dictionary object holding all the game objects and is initialized using the `Initializer` class.
            
- **Initializer:** The Initializer is a static class that reads and deserialize the JSON files for the game and returns the `GameEntities` object called `gInit`. The deserialization is done by referencing to different deserialization classes holding an overloaded method specifically catered to the format of the JSON that is about to be read. 
                    It is used as a one-off class during initialization of the game and does not hold any valuable data.
                    
- **gameEntities:** The `GameEntities` object is the main dictionary object that hold all the entities present, meaning that this object is used to store the entire game.
                    There can only be one object of the type `GameEntities` and it is persistent in memory till the system is exited.
                    It also holds the states of the various objects. It stores these in the form of maps of all the entities supported by the engine such as the areas, obstacles, npcs and items. 
                    It also holds the main player object, the defaultEntity and the gameOverItem object. 
                    The maps are shown with a 1-to-many relation in the UML and the player, emptyEntity, and gameOverItem as a 1-to-1 relation.
                    
- **gameOverItem:** The main item the player has to acquire to win and causes the game to finish. In our case, the object is called `diamond`. The gameOverItem will be stored in the inventory of one of the areas in the game.

- **area:** A game needs to have at least one area as it needs an area to hold the gameOverItem and as initial spawn location of the player. In our snapshot the area is a `forest`, holding a `diamond` in its inventory, has a `troll` in it as npc and is connected with a `castle` that lies north. However this connection is blocked by an obstacle `door`.

- **obstacle:** A game can have zero or more obstacles. In our case, we have an obstacle called the door that exists inside the forest and blocks the 
                user's path to the castle. The door just like the player has a commandBlueprint object called `use` of the type ChangeState. 
                This checks for a requiredObject called `key` when the command is executed by the player. 
                On a success, the state of the obstacle is set to true and the area is no longer blocked.

- **npc:** A game can have zero or multiple NPCs. In our case we are focusing on a NPC called the troll who does not have any items in his inventory, 
            does not have any commands we can use on it and is active in the game. This means that the user can interact with him.

- **item:** A game can have zero or more items. In our case, we will focus on the item apple as this is the subject of the user's previous command. 
            The item's name is apple, it is consumable meaning that it can only be used once and its active flag is set to false when used. 
            As eating an apple is only possible once, it is considered to be a consumable. This is different from an object such as a key that does not get consumed. 
            As the user has already eaten the apple, the active flag has been set to false to show that the object is not a part of the game anymore. The `stats` variable
            shows the effect of the apple when consumed, in our case it regenerates 10 health. The apple has a commandBlueprint object called `enchant` of the type ChangeStat.
            Upon using this command the original values of stat can be modified.

Maximum number of words for this section: 1000

## State machine diagrams									
Author(s): `Anthony Wilkes`

### Engine

![Engine State Machine Diagram](assets/A03/engine_state.png "Engine state machine diagram")

The **Engine** is the heart of the system, and is the central point from which all other components determine their meta-level state. The state of the **UIHandler** is intimately linked with the **Engine** state, and the **Game** is reliant on the **Engine** to feed it with the input it requires to run.

Initially, the **Engine** starts up in the *Home Screen* state - the **Engine** instructs the **UIHandler** to display the splash screen to the user and waits for a response. If the user chooses to quit, the **Engine** performs any necessary clean-up and does so. If the user chooses to continue, the *Main Menu* state is entered.

On entering the *Main Menu* state, the **Engine** checks to see which games are currently available to the user. If the games directory cannot be found, the **Engine** instructs the **UIHandler** to print an error, then exits. If games can be found, these are presented to the user, and the user is allowed to make a choice.

Once the user has made a choice, the *Game Menu* state is entered. On doing so, the game files are loaded, and if an error is encountered, the **Engine** displays the error and exits. If the game files load successfully, however, the user is presented with the choice to load an old game, start a new game, or exit.

If the user chooses to load a game, the *load game* state is entered and the save file (if any) is loaded (this involves converting the file from base64 encoding back to normal encoding) and the commands in the file are replayed. Once all the commands have been replayed, the *Game Running* state is entered. If there are any issues loading the save file (e.g. the file doesn't exist), then the user is informed that the file could not be loaded, and the *Game Running* state is entered just as if the user had started a new game (likewise, if the user chooses to start a new game from the previous *Game Menu* state, then the *Game Running* state is entered directly).

Once in the *Game Running* state, the **Engine** simply waits for input from the user. If this input is a meta-level command like loading or saving, then the **Engine** will handle these requests directly, otherwise the commands are relayed to the **Game** which deals with them as required.

If the user chooses to quit the game during the *Game Running* state, then they will be prompted to save, and the game will be either be saved, and the **Engine** will exit, or the **Engine** will exit directly without saving.

If the game ends whilst in the *Game Running* state (e.g. the player wins the game or dies), then the user is returned back to the initial *Home Screen* state, and the process can repeat from the beginning as before.  

---

This chapter contains the specification of at least 2 UML state machines of your system, together with a textual description of all their elements. Also, remember that classes the describe only data structures (e.g., Coordinate, Position) do not need to have an associated state machine since they can be seen as simple "data containers" without behaviour (they have only stateless objects).

For each state machine you have to provide:
- the name of the class for which you are representing the internal behavior;
- a figure representing the part of state machine;
- a textual description of all its states, transitions, activities, etc. in a narrative manner (you do not need to structure your description into tables in this case). We expect 3-4 lines of text for describing trivial or very simple state machines (e.g., those with one to three states), whereas you will provide longer descriptions (e.g., ~500 words) when describing more complex state machines.

The goal of your state machine diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 4000

## Sequence diagrams									
Author(s): `Anthony Wilkes, Luca Taglialatela`

### User Input

![User Input Sequence Diagram](assets/A03/input_sequence.png "User input sequence diagram")

The diagram above follows the processes involved when the user types in "use key door" whilst the game is running.

Initially the **Engine** is inside the *runGame* loop, and has deferred getting user input to the UIHandler's *getInput* function. *getInput* blocks until the user has entered some text and hit the `enter` key, at which point the line of entered text is returned as a String to **Engine**. If this input matches a meta-level command ("save", "load", "quit"), then **Engine** handles the resolution of this input itself, without sending the request to the current *game*. If the user input is not a meta-level command, however, then it is sent directly to the current *game* to handle as needed through the *handleCommand* function.

When the **Game** class receives input it first tries to find a corresponding target **Entity**. This will always be the **Entity** with a name that matches the third word in the user input (inputs must be between 1 and 3 words inclusive, if the command is 1 or 2 words in length then the target entity defaults to the player). To do this, the **Game** class calls the **GameEntities'** *getEntityOrDefault* function, which will find any existing **Entity** with the given name, or returns a **DefaultEntity**. A **DefaultEntity** provides all functionality that any **Entity** has, but interaction with it will never have any side-effects and results will always return a message that indicates to the user what they did wrong (as opposed to a real **Entity** which might return an indication of success or failure). This type of interaction will be discussed in detail further on, as this description will first deal with the assumption that a matching **Entity** has been found.

Assuming **GameEntities** has returned a reference to the "door" **Entity**, the **Game** now calls *getCommandOrDefault* on the door. As with *getEntityOrDefault*, this function will return a command with a matching name or a **DefaultCommand**. Interaction with a **DefaultCommand** will always return a message indicating to the user what went wrong, and will never have side-effects (the same as for the **DefaultEntity**).

Again assuming that *getCommandOrDefault* has returned a real command object, the **Game** calls the command's *apply* function. In the case of the input "use key door", the "door" object will contain a **ChangeState** command tied to the name "use", and the **ChangeState's** *apply* function will take "key" as an argument.

**ChangeState** will first query if the **Player** has the "key" object in their inventory. If this is not the case, the function exits early and returns a String result with a message that indicates the problem to the user. In this case "You do not have 'key' in your inventory.". The **Engine** receives this result and passes it directly to the **UIHandler** which prints it, and the *runGame* loop continues. If the **Player** does have the "key" in their inventory then the state of the "door" object is queried. If the "door" is currently inactive, then *apply* terminates early and a result is returned as before, this time the String "You don't need to do that." (as unlocking an unlocked door will have no effect). If the "door" object is currently active, however, then the *apply* function next checks to see if the "key" passed as an argument to the function matches the required object for the "door". If this is not the case, then the String "That doesn't work here." is returned to the user. If the "key" is the required object, however, then the *deactivate* function is called on the "door" object, and the **Obstacle** will now allow the passage of the **Player**. This completes the *apply* function, which then returns "The path to the castle is now clear!".

The above explanation largely assumes that the given input leads to a successful command, however, it is also possible that the player writes input that doesn't work, for example, they might make spelling mistakes like "youse keey doro". In this instance, the system has been designed so that errors in the user input do not need to be handled explicitly, allowing the code written inside of **Game** to be free of exceptions and other error handling methods.

At the first stage of command completion, it's possible for the target **Entity** to not exist - in this case, there is no **Entity** called "doro". Instead of returning a null value that would require explicit checking, the **GameEntities** object returns a **DefaultEntity** as described above.

Next, it is possible that the command specified doesn't exist - in this case, "youse" will not be found in the specified **Entity**. Note that if the player had spelled "door" correctly, the *door* object would return a **DefaultCommand** in the same way that the **DefaultEntity** was returned previously (the **DefaultEntity** will always return a **DefaultCommand** when queried). This **DefaultCommand** can be used with the *apply* function just like any other, however, the result returned will always signify that something went wrong (but no other action will be taken).

Through this process it should be clear to see that the results the player can receive will be one of the following:

- The player tried something successfully, and gets a message like "The path to the castle is now clear!".
- The player misspelled or used the wrong target entity's name, upon which a **DefaultEntity** provides a **DefaultCommand** that returns "You cannot see that here.".
- The player misspelled or used the wrong name for the command, meaning a real **Entity** returns a **DefaultCommand** which returns "You cannot do that.".
- The player used the wrong item name, meaning a real **Entity** returns a real command that returns "That doesn't work here." (assuming the apple is in their inventory)
- The player misspelled the item name (or tried to use an item they are not holding) meaning the *handleCommand* exits early with a message like "You do not have 'keye' in your inventory.".

### JSON Initialization

![Initialization Sequence Diagram](assets/A03/initialization_sequence_diagram.png "Initialization sequence diagram")

This chapter contains the specification of at least 2 UML sequence diagrams of your system, together with a textual description of all its elements. Here you have to focus on specific situations you want to describe. For example, you can describe the interaction of player when performing a key part of the videogame, during a typical execution scenario, in a special case that may happen (e.g., an error situation), when finalizing a fantasy soccer game, etc.

For each sequence diagram you have to provide:
- a title representing the specific situation you want to describe;
- a figure representing the sequence diagram;
- a textual description of all its elements in a narrative manner (you do not need to structure your description into tables in this case). We expect a detailed description of all the interaction partners, their exchanged messages, and the fragments of interaction where they are involved. For each sequence diagram we expect a description of about 300-500 words.

The goal of your sequence diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 4000


## Implementation									
Author(s): `Anthony Wilkes`

#### Implementation Strategy

TODO: general overview of workflow

#### Key Solutions

##### Functional Features
| ID  | Short name  | Description  |
|---|---|---|
| F1  | Commands  |  |
| F1B  | Customizable Commands  |  |
| F2  | Movements  |  |
| F3  | Areas |  |
| F4  | Obstacles |  |
| F5  | Items |  |
| F6  | NPCs |  |
| F7  | Stats |  |
| F8  | Combat |  |
| F9  | Save Game |  |
| F10  | Load Game |  |

##### Quality requirements

| ID  | Short name  | Quality attribute |
|---|---|---|
| QR1 | Pre-Game Validation |  |
| QR2  | Customizable Scenarios |  |
| QR3 | Event Timing |  |
| QR4 | Speed of Action Execution |  |
| QR5 | Speed of Initialization |  |
| QR6  | Command validation |  |
| QR7 | Input Reception |  |
| QR8 | Save Files Encryption |  |
| QR9 | Save Prompting |  |
| QR10 | Determinism Guarantee |  |

#### Main Class Location
The main function can be found in `src/main/java/cork/Engine.java`

#### Jar Location
`out/artifacts/cork_jar/software-design-vu-2020.main.jar`

#### Execution Video

TODO: replace with up-to-date video

[![Execution Video](https://img.youtube.com/vi/qOCYWU9dryA/0.jpg)](https://www.youtube.com/watch?v=qOCYWU9dryA&feature=youtu.be)
