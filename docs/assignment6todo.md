# Todo

- Read only model
- Model setup:
    - The ability to create a game using the configuration files for the grid and the cards
    - The ability to create a copy of the grid
- Observations:
    - How big is the grid?
    - What are the contents of a cell at a given coordinate?
    - What are the contents of a player’s hand?
    - Which player owns the card in a cell at a given coordinate?
    - Is it legal for the current player to play at a given coordinate?
    - **Given a card and a coordinate, how many cards can a player flip by playing at a given
      coordinate?**
    - What is a player’s score in the game?
    - Is the game over?
- Operations:
    - The current player plays a card from their hand at a given cell
- Make game board
- a constructor that takes in a ReadonlyThreeTriosModel
- a user should be able to click on a card in the hand
- our view should (temporarily) print a message (using System.out) containing the index of the card
  that was clicked on as well as which player owns that hand.
- a user should be able to click on a cell in the grid and the view should (temporarily) print a
  message (using System.out) containing the coordinates of the cell that was clicked on, in whatever
  coordinate system you used in your model.
- Your view should let the user deselect a selected card in the hand by
    - clicking on it again OR
    - clicking on another card (in which case you should select that new card)