# Assignment 1
Maximum number of words for this document: 2500


## Introduction									
Author(s): `name of the team member(s) responsible for this section`

Write a short description of your version of the system you are going to design and implement in this course. 
Clearly specify which are the key aspects of your system, such as:
- customizations/extensions w.r.t. the project track, 
- main type of user(s)
- overall idea about how it works
- ...

Be creative here!

Don’t forget to use links to external URLs (e.g., the direct link to the Fantasy soccer league you are getting inspiration from, the link to the original video game you are getting inspiration from, etc.), if applicable. 

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
