# Assignment 1
Maximum number of words for this document: 2500


## Introduction									
Author(s): `Ajay Hitendra Mota`

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

The system will be implemented as a form of a terminal/text-based game in a game window where the user will interact with the game engine by means of text based inputs typed by means of a keyboard only. The way the *gamer* experiences the system will be as follows:

Maximum number of words for this section: 1000

## Features
Author(s): Tolucawana Taglialabempong

<When defining both functional features and quality requirements, remember that you will need to come back to them in Assignments 2 and 3 and explicitly state how specific parts of models/implementation satisfy them.>

### Functional features

As a preamble to the table, you can discuss the main line of reasoning you applied for defining the functional features provided in the table.

| ID  | Short name  | Description  |
|---|---|---|
| F1  | Commands  | The player can control the main character by issuing command-line commands following this syntax: `command-verb + [target-noun]`, where (\*) indicates a noun is optional. Available `command-verbs` are: <br/> - `move` + [north, east, south, west, area-name] <br/> - `use` + [item] <br/> - `look` + [item]\* <br/> - `interact` + [npc, item] <br/> |
| F2  | Movements  | The player may move freely between different areas in four directions as described by the `move` command. Some areas may be blocked by obstacles and therefore inaccessible. Obstacles may be removed by using some item or action.  |
| F3  | Areas | All areas will be represented as nodes in a graph, where edges between nodes indicate two areas are reachable from one another.  |
| F4  | Obstacles |   |
| F5  | Items | The player may acquire and use items throughout the game. Items will be stored in an inventory of infinite size. Several commands may be used in combination with an item: </br> - `interact` acquires the item and places it in the player's inventory. </br> - `look` briefly describes the item. </br> - `use` queries the item, asking it what it may be used for, and if appropriate for the situation, uses it (e.g. `use key door` will consume the key but `use key npc` will not).  |
| F6  | NPCs | Throughout the game, several NPCs will appear which can be interacted with using the `interact` command. NPCs may be friendly or hostile and hold valuable items for the user to acquire.  |

### Quality requirements
Author(s): `name of the team member(s) responsible for this section`

As a preamble to the table, you can discuss the main line of reasoning you followed for coming up with the quality requirements listed below.

| ID  | Short name  | Quality attribute | Description  |
|---|---|---|---|
| QR1  | Commands sanity checks | Reliability  | When the player issues a command, the syntax of the command shall always get validated against the format specified in F2 |
| QR2  | Extensible world | Maintainability  | The video game shall be easilty extendable in terms of levels, worlds, interaction points  |
| QR3  | Instantaneous results | Responsiveness  | Once the scores of all soccer players are provided by the user, the results of the virtual matches shall be available within 1 second |
| QR4  | ... | ... | ... |

Each quality requirement must be tagged with the corresponding quality attribute (see corresponding slides of the first lecture for knowing them).

Maximum number of words for this section: 1000

### Java libraries
Author(s): `name of the team member(s) responsible for this section`

| Name (with link) | Description  |
|---|---|
| [JFoenix](http://www.jfoenix.com/)  | Used for styling the user interface of the system. We chose it among others because ... | 
| [fastjson](https://github.com/alibaba/fastjson) | We will use it for reading and writing JSON configuration files containing the description of the levels of the videogame. We chose it because it is easy to configure and use from Java code and preliminary experiments makes us confident about its correct functioning... |
| ...  | ... |

Maximum number of words for this section: 500
