# EC327 Final Project - Group [12] - [SimpleChess]
---
## Summary
### Category
Chess

### TL;DR
SimpleChess is an application that allows one or two players to compete in a chess game. Some features of the app include being able to play a chess CPU and additionally, you can battle a more aggressive CPU. A working leaderboard to track wins when you play and also the game allows you to see what moves are available the game has a check system and game-ending conditions. Clean and understandable UI also makes this app simple.

### Description
The proposed app is a chess game that allows users to play locally with another user or against a bot player. The app will follow traditional chess rules and provide proper timing for moves. Users will have the option to customize the app's UI with various themes, sound effects, and animations. The app will also feature a predictive move feature, different bot player difficulties, and a leaderboard ranking system. It will be developed using Java or Kotlin programming languages and require careful planning, testing, and UX design to ensure an engaging and user-friendly experience for players.
The app aims to provide chess players with a convenient and accessible platform for playing the game of chess. Its emphasis on traditional chess rules and proper timing for moves will offer users an opportunity to improve their skills. The customizable UI options will allow users to personalize their gaming experience, while the leaderboard ranking system will encourage healthy competition among players. The project's success will depend on the effective implementation of its features, rigorous testing, and an intuitive and visually appealing UX design.


## Authors
### Group members
Rohan Kumar, RohanKumar, roku@bu.edu
Michael Terekhov, MichaelTerekhov, terekhov@bu.edu
Roger Finnerty, JohnFinnerty, jrfinn@bu.edu
Harlan Jones, HarlanJones, hljones@bu.edu
Josh Caban, JoshuaCaban, jcaban@bu.edu

### Roles
Leaders: Rohan Kumar [50%], Harlan Jones [50%]
Front-End: Michael Terekhov [75%], Harlan Jones [25%]
Back-End: Harlan Jones [33.3%], Rohan Kumar [33.3%], Roger Finnerty [33.3%]
Documenter: Roger Finnery [50%], Joshua Caban [50%]
Tester: Joshua Caban [100%]


---
## Accomplishments

### Minimum requirements
Fully Completed:
Local device user vs user gameplay
Local device user vs computer gameplay
Chess pieces follow traditional movement rules
Game recognizes whose turn it is
Game recognizes when a checkmate or stalemate is reached
Game prompts user to keep playing, quit playing, or change modes upon game ending

### Possible features
Fully Completed:
Options to change board theme, piece theme, sounds, background [10%]
Different levels of computer difficulty that have a different play style [10%]
Have a predictive move feature (showing what spaces you can move to with your piece) [10%]
Record play history of each user and develop a "ranking" based on wins and losses [10%]
Add sound effects and animation to player captures [10%]


---
## Execution

### Project source
No external links required, just this repo.

### Installation
Download Android Studio Flamingo 2022.1
Download this repository
Open Android Studio, go to Open, and navigate to the top file of the project (should be called group12project)
To test on the PC, go to Device Manager (along right side) -> Create Device
Select Category: Phone, Pixel 6 Pro, then press Next
Select Tiramisu (API Level 33) for the System Image
Press next, and go through steps to download this Device
Allow time for download and Studio to automatically detect build
Press run when ready, should popup the Virtual Device (which can also be found under "Running Devices" tab along the bottom right)


### Usage

The app launches onto the main menu, which is the basic navigation hub of the app. There are 4 buttons
The bottom button is the Rules button, which takes the user to a page that explains the basic idea of chess. On that page, there is only a back button to go back to the Home page
The leaderboard button on the main page takes the user to a page that holds all leaderboard entries on a table. This will be updated as games are played
The new game button brings you to a page where 2 names are prompted for the two people that are going to play against each other, and on that screen is a back button as well as a start game button
The new game vs bot button sends you to a page where you enter your name and then there is a toggle present that gives the user the option to turn on the aggressive bot mode
Once the game detects an end game state, a popup appears that signifies the end of the game and gives options to navigate to home, to play again, or to go and view the leaderboard

---
## Miscellaneous

### Extra features
We did not implement any other features.



### Challenges
Our main challenges arose from implementing the chess logic into our game. Obscure moves such as castling, en passant, and pinning pieces to the king took a lot of time and many attempts to successfully implement. Similarly, differentiating between a check and checkmate proved to be difficult as we had to look to see if a user could intercept the check with one of their pieces, capture the opponent’s piece that put the king in check, or if the king could move to safety.
A design challenge we faced was discerning between activities and fragments within AndroidStudio and how they work together. We initially tried to use only fragments to navigate between our home/game/leaderboard screens but quickly found that it was not feasible to implement such logic that would navigate in the style that we wanted to. After doing some research about fragments and activities, we decided to implement each of our pages as separate activities that utilized go_to_respective_activity() functions to navigate between pages quickly and efficiently.
Another source of our challenges was implementing the leaderboard and win/loss/draw tracks. The main difficulty was saving data across different instances of the app being run. We initially attempted using regular vectors to store player names and their total number of wins but quickly came to realize that this data would reset every time the app was closed out. Our potential options for solving this issue included reading/writing to a text file, using SQLite, or user preferences. We attempted implementing SQLite commands but found it quite difficult to fit our exact table template. We ended up having success using preferences after learning how they work. We figured out how the data we wanted (names & wins) were being saved to the file and then we were able to extract all the contents of that file and store the data into a map that utilized the string name as the key and an integer value corresponding to the wins. We then rebuilt our table every time the leaderboard was accessed based on the updated values that we pulled from the preferences file so that all data was up to date. Our main logic was to check whether or not the player name already existed in the table so that if they did, we change their wins accordingly, but if they are a new player we would add them to the leaderboard and add a 1 or 0 in their wins column depending on the outcome of the game.

### Supporting material
Documentation of the code is available in the repository labeled under Documentation and is a PDF. Also, this repository holds the slides used for the project demo, called SimpleChess
### Release
Rohan Kumar agrees to make the project publicly available.
Michael Terekhov agrees to make the project publicly available.
Harlan Jones agrees to make the project publicly available.
Josh Caban agrees to make the project publicly available
Roger Finnery agrees to make the project publicly available


### 

