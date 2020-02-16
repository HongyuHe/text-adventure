# Assignment 1

## Introduction									
Author(s): `Ajay Hitendra Mota, Hongyu He`

**Cork** is a customizable version of the text-based adventure game Zork. The system we are going to implement will allow a user to create interesting scenarios for the original Zork game by modifying JSON files which define the interactable entities in the game. A user will also be able to play the default scenario designed by us or play a customized scenario. The main types of users will be :
- *Gamer*: A user who plays the game making use of the features provided by the game engine.
- *Modder*: A user who can modify the default scenario to build modified versions of the original game.

The main entities in the game that the *gamer* can interact with will be as follows:
- Player: This will be the main player character who interacts with all other elements in the game. The actions available to the gamer, mentioned later in the features, would be enacted by this player entity. The player would have stats like health & strength and have an inventory. 
- Areas: These will be the locations on the game map where a player can stand, visualize his/her surroundings and move to other linked areas. For example, a forrest with a castle to the north.
- Obstacles: These will be game objects that block the path of a player while going from one area to another. One obstacle shall exist inside areas. For example, a locked door or darkness.
- Items: These will be game objects that the player can use to influence stats of the player character, add to inventory, overcome an obstacle or affect an NPC. For example, an apple or a key.
- NPCs: These will be entities in the game that the player can interact with opening up either conversations or combat. As a bonus we could implement merchants or bosses. For example, the grue.

The customizations available to the *modder* will be as follows:
- Edit JSON files to create new NPCs, items, obstacles and areas but with fixed structure that follows the guidelines of the default enitites.
- Edit JSON files to change the descriptions and names of default entities, modifying the starting inventory of the player and increasing or decreasing the stats of the player.

The main modules of the system will be as follows:
- Scenario: The scenario will be written in structured JSONs and describe all entities in the game which have been mentioned above.
- Parser: This will be the middleware that parses the JSONs and maps these to pre-built java classes.
- Game Engine: This will be the main module that parses the user input into pre-built functions and executes these interactions.
- UI: This will be the game window where the main user I/O operations will take place. The gamer commands will be inputed here and the output of the game engine will be logged here.

The system will be implemented as a form of a terminal/text-based game in a game window where the user will interact with the game engine by means of text based inputs typed by means of a keyboard only. The way the game will generally work will be as follows:
- User runs the game and a window is loaded.
- A description of the starting area and the player's starting inventory will be displayed.
- The system will wait for the user to provide an input in the form of supported commands.
- Each input will be processed on a new line and the outcome of the action will be displayed to the user.
- The game ends if the player's health reaches zero, or the player enters the quit command or the player wins the game by attaining a specific item. For example, a diamond. 

## Features
Author(s): `Luca Taglialatela`

### Functional features

As a preamble to the table, you can discuss the main line of reasoning you applied for defining the functional features provided in the table.

| ID  | Short name  | Description  |
|---|---|---|
| F1  | Commands  | The player can control the main character by issuing command-line commands following this syntax: `command-verb + [target-noun]`, where (\*) indicates a noun is optional. Available `command-verbs` are: <br/> - `move` + [north, east, south, west, area-name] <br/> - `use` + [item] <br/> - `look` + [item]\* <br/> - `interact` + [npc, item] <br/> - `inventory` <br/> - `quit` <br/> - `load` <br/> - `save` <br/> |
| F2  | Movements  | The player may move freely between different areas in four directions as described by the `move` command. Some areas may be blocked by obstacles and therefore inaccessible. Obstacles may be removed by using some item or action.  |
| F3  | Areas | All areas will be represented as nodes in a graph, where edges between nodes indicate two areas are reachable from one another.  |
| F4  | Obstacles |   |
| F5  | Items | The player may acquire and use items throughout the game. Items will be stored in an inventory of infinite size, which may be accessed using the `inventory` command. Several commands may be used in combination with an item: </br> - `interact` acquires the item and places it in the player's inventory. </br> - `look` briefly describes the item. </br> - `use` queries the item, asking it what it may be used for, and if appropriate for the situation, uses it (e.g. `use key door` will consume the key but `use key npc` will not).  |
| F6  | NPCs | Throughout the game, several NPCs will appear which can be interacted with using the `interact` command. NPCs may be friendly or hostile and hold valuable items for the user to acquire.  |
| F7  | Stats | The player has two main stats, `health` and `strength`, which they will use for combat.  |
| F8  | Combat | Whenever the player engages in combat they will be presented with a set of choices to make. Depending on the player's choices, combat will have either a positive or negative result for the player's health stat.  |
| F9  | Gamestate | The player may save or load the game at any moment using the `save` and `load` commands respectively. The gamestate will be represented using a JSON file, containing all appropriate information.  |

### Quality requirements
Author(s): `Jim Cuijpers, Ajay Hitendra Mota, Anthony Wilkes`

| ID  | Short name  | Quality attribute | Description  | Rationale |
|---|---|---|---|---|
| QR1 | JSON Validation | Reliability | The JSON files that describe the game world will be read by the engine as the game is started, and the first missing - but required - attribute shall be indicated to the user, and the game will exit. | This ensures that people who wish to modify the game files will be able to identify their mistakes easily. |
| QR2  | Customizable Scenarios | Maintainability  | The JSON files which define the game world shall be easy to customize and modify with only a limited understanding of JSON, and documentation will provide the expected format for each kind of JSON file. | This will allow non-technical users to create their own stories without needing to learn any programming languages. |
| QR3 | Event Timing | Availability | A command can only be issued by the user once the events of the previous command have been completed. | This ensures that the execution of commands will always be deterministic and helps avoid any potential confusion that the player may face (e.g. receiving responses from commands out of order). |
| QR4 | Speed of Action Execution | Responsiveness  | Any command that the user gives as part of gameplay, shall be completed and provide a response within 2 seconds. | The user should never be left waiting on the system for an extended period of time, or be lead to believe that that system has become unresponsive. It will also ensure that QR3 is fulfilled correctly. |
| QR5 | Speed of Initialization | Responsiveness | Any command involved in the set-up of the system or game, including saving and loading, shall be completed within 10 seconds. | This ensures that the game and system set-up takes a reasonable length of time. |
| QR6  | Command validation | Usability  | Any action typed by the user will be compared to the formats specified in F1, and if it does not match, the user will be presented with a messaging indicating that the command was not understood along with a suggestion to use the help command. | This will ensure that user interacts with the game engine correctly and provides guidance. |
| QR7 | Input Reception | Usability | For any input the user types, the game will always provide some response to indicate that the input was received. | This will ensure that the user will never be lead to believe the system is unresponsive when in fact it is not. |
| QR8 | Save Files Encryption | Security | Whenever the user saves their game, the engine should encrypt the contents to ensure the user cannot edit them. The encryption does not have to be cryptographically secure. | This will prevent players from cheating easily. |

### Java libraries
Author(s): `Anthony Wilkes, Jim Cuijpers`

| Name (with link) | Description  |
|---|---|
| [Erdos](https://github.com/Erdos-Graph-Framework/Erdos)  | Graphs will be used to represent how the different areas in the game connect to each other. Erdos has a very simple and easy to use API with thorough documentation, some alternative that were looked at were JGraphT, which was too heavy-weight with nine separate dependencies, or Apache Commons Graph, which was excessively complex for our needs. |
| [Gson](https://github.com/google/gson) | Gson will be used to read the JSON files created by the game designer. Since this is an integral part of the game we are writing, we wanted a robust library that was still easy to use. Most alternatives offered performance as their core advantage (Jackson, HikariJSON, LoganSquare, FastJSON), however, as loading JSON will only be used during initialization, speed increases are unlikely to make much difference during the game playing session and therefore we would prefer simplicity over speed. |
| [SonarLint](https://www.sonarlint.org/) | Used to ensure that all team members adhere to the same coding style and practices. |
| [TinyLog2](https://tinylog.org/v2/) | Using a logger will provide structure to the code the team is writing, and will allow much cleaner testing and debugging, whilst ensuring that  the requirements of SonarLint are adhered to during production. TinyLog has the advantage of being a very small, lightweight logger that requires almost no boilerplate code. TinyLog also allows configuration through a properties file, something that the alternative, MinLog, did not offer. |
| [Text-IO](https://github.com/beryx/text-io) | Text-IO is a highly recommended library for reading input from a user. It allows things like selecting values from a list which will be helpful if we implement conversation and combat as part of our bonus features. It has a very clean and easy to use API - since text input and output is a core part of the game, keeping these sections tidy is of the utmost importance. Text-IO also allows colour input and output which can help provide a more interesting and higher quality experience for the player. The team considered using Zircon to build a terminal-GUI, however it was decided that this would be outside the requirements as this would not be in line with providing an "authentic" Zork experience. |
