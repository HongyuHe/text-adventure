# Assignment 3

Maximum number of words for this document: 18000

**IMPORTANT**: In this assignment you will fully model and impement your system. The idea is that you improve your UML models and Java implementation by (i) applying (a subset of) the studied design patterns and (ii) adding any relevant implementation-specific details (e.g., classes with “technical purposes” which are not part of the domain of the system). The goal here is to improve the system in terms of maintainability, readability, evolvability, etc.    

**Format**: establish formatting conventions when describing your models in this document. For example, you style the name of each class in bold, whereas the attributes, operations, and associations as underlined text, objects are in italic, etc.

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
Author(s): `name of the team member(s) responsible for this section`

This chapter contains the specification of at least 2 UML sequence diagrams of your system, together with a textual description of all its elements. Here you have to focus on specific situations you want to describe. For example, you can describe the interaction of player when performing a key part of the videogame, during a typical execution scenario, in a special case that may happen (e.g., an error situation), when finalizing a fantasy soccer game, etc.

For each sequence diagram you have to provide:
- a title representing the specific situation you want to describe;
- a figure representing the sequence diagram;
- a textual description of all its elements in a narrative manner (you do not need to structure your description into tables in this case). We expect a detailed description of all the interaction partners, their exchanged messages, and the fragments of interaction where they are involved. For each sequence diagram we expect a description of about 300-500 words.

The goal of your sequence diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 4000

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
