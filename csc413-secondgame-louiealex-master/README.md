# csc413-SecondGame

## Student Name : Alexander Louie
## Student ID : 916124851


# Repo Link:
https://github.com/csc413-01-fa18/csc413-secondgame-louiealex


# Project Build Info: 
IDE: **IntelliJ Idea 2018.2.3 (Ultimate Edition) IDE** 

Java Version: **Java JDK 10.0.2**.

# **Importing Instructions:**
Open the project in IntelliJ.
1. In IntelliJ, go to "Project Structure -> Modules", and change the following settings.
     - set the Github repo as the Content Root
     - set the src folder as a Source
     - set the src/Resources folder as a Resource

# **To run the Pyramid Panic Game:**
1. Note the Controls, Objectives, and Notes listed below.
2. Choose 1 of the 2 options.
    - Right Click on the "GameWorld" class and select Run "GameWorld.main()". The game starts immediately once launched.
    - The Pyramid Game can also be by launching the included .jar file via the command line located in the JAR folder.
3. Once either player is out of lives, the game will display a message identifying the winning explorer. The game window will close automatically after approximately 10 seconds.

Note: The screen resolution is 1280x704. This can be modified by changing the screen width and screen height in the GameWorld class file. Make sure the SCREEN_WIDTH is greater than the SCREEN_HEIGHT.

# Controls
Controls for Explorer (left half of the screen):

1. Space-bar is for activating your light once if you desire once you collect the sword.

2. Return/Enter key (above the right shift key) is for activating your scarabs.

2. up-arrow key is for moving the explorer forward

3. left-arrow key is for rotating the explorer left

4. down-arrow key is for moving the explorer backward

5. right-arrow key is for rotating the explorer right


# Objectives
1. Collect the Sword.
2. Return to the original Starting Position to win the Game.

# Notes
1. You can collect Amulets and Anubis to earn points toward your score.
2. If you get caught by a Beetle, Scorpion, or Mummy, then you will loose 1 life. You will loose the game when you have 0 lives remaining.
3. Collect Scarabs. Activating a Scarab will make Mummies temporarily vulnerable. They will flee your player and can be collected/eaten for points toward your score.
4. Once you have collected the Sword/Wand, the screen will become darker.
5. You can activate the Wand on the Sword to better light up the screen for navigating back to the starting position. This will however decrease your score (rather quickly).
6. In addition, if the Wand is activated then, Mummies are vulnerable. They will flee your player and can be collected/eaten for points toward your score.
