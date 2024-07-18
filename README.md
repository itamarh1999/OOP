This is a text-based adventure game played on a grid-like board.
The player navigates through various levels, fighting different types of enemies
, avoiding traps, and overcoming obstacles. Each level is loaded from an indexed file that defines the layout and contents of the board.

Game Board
The game board is a 2-dimensional array of characters, each representing different elements:

@ (Green): Player
B, s, k, M (Red): Different types of enemies
. : Free area
# : Wall
How to Play:
Choose a Player Character: Upon starting the game, select your character from the available classes: Warrior, Mage, or Rogue.

Understand the Game Tick:

Each level consists of several rounds or game ticks.
In each tick, the player performs a single action followed by actions from each enemy.
Complete Levels:

Defeat all enemies on the board to complete a level.
Progress to the next level after defeating all enemies.
The game ends when you complete all levels or if your character dies.
Player Actions
Move: Navigate through the free areas (represented by .) on the board.
Fight: Engage in combat with enemies by moving into their tile.
Use Special Ability: Depending on your character class, use your special ability to gain an advantage in combat.
Combat System
Combat occurs when you move into a tile occupied by an enemy or vice versa.
The attacker rolls an amount between 0 and its attack damage.
The defender rolls an amount between 0 and its defense points.
If attack roll - defense roll > 0, the defender loses health equal to the difference.
Defeat enemies to gain experience and level up.
Character Classes
Warrior:

Special Ability: Avenger’s Shield
Ability Effect: Hits a random enemy within range < 3 for 10% of max health and heals for (10 × defense).
Cooldown: Ability can be used once every specified game ticks.
Mage:

Special Ability: Blizzard
Ability Effect: Hits random enemies within range for spell power.
Mana Management: Ability costs mana.
Rogue:

Special Ability: Fan of Knives
Ability Effect: Hits all enemies within range < 2 for attack points.
Energy Management: Uses energy, starting with 100 energy.
Enemies
Enemies are of two types: Monsters and Traps.

Monster:
Moves randomly or chases the player if within vision range.
Trap:
Alternates between visible and invisible states.
Can attack if the player is within range < 2.
Leveling Up
Gain experience by defeating enemies.
Upon leveling up:
Increase health pool, attack, and defense points.
Reset special ability cooldown or resource (mana/energy).
