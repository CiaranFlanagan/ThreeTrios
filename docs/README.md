# Table Of Contents

<!-- TOC -->
* [Table Of Contents](#table-of-contents)
* [Introduction](#introduction)
    * [Info](#info)
    * [Rules](#rules)
* [Quick Start](#quick-start)
* [Getting Around the Project](#getting-around-the-project)
* [Key Components](#key-components)
    * [Model](#model)
    * [View](#view)
    * [Controller](#controller)
    * [Player](#player)
* [Diving Into Components](#diving-into-components)
  * [In the model...](#in-the-model)
    * [In the view...](#in-the-view)
* [Notes on Extension](#notes-on-extension)
    * [Extension without exposed mutation](#extension-without-exposed-mutation)
<!-- TOC -->

Introduction
=

This is the ThreeTrios project. ThreeTrios is a two player card game played on a grid.

### Info

- The grid has cells. The cells are capable of holding a card (card cells), or are not (referred to
  as a hole)
- The two players are associated with red and blue.
- The cards have attack values with values 1-10, represented 1-A, and can be associated with red
  or blue.
- Cards are placed onto empty card cells in the grid. Cards battle adjacent cards of different
  colors.

### Rules

1. The grid must have an odd number of card cells.
2. Both players must have an even number of cards, which add up to one plus the number of card
   cells.
3. Red always goes first.
4. The winner of a card battle is the one with higher attack value in the direction of battle.
   Losers of battle flip colors when the current player is associated with a different color.
5. The winner is the one who is associated with the most cards of their color, including the
   cards in their hand.
    - Ties are possible if the second player has one less card on the grid, because their hand
      has one more card.

Quick Start
=
_None as there is no way to play the game interactively YET_

Getting Around the Project
=

- **main/**
    - _future entry point for playing the game_
- **src/cs3500.threetrios/**
    - _entry to all source code_
- **test/cs3500.threetrios**
    - _entry to all tests, mocks, test-setup files_
        - **controller/**
            - _contains test files and a helper class_
        - **model/**
            - _contains tests for ONLY package private_
            - **player/**
              - _contains interfaces and classes that define the player strategies for three trios_
            - **playerstats/**
              - _contains a concrete class with an implementation of possible stats a player 
                might have_
        - **test/**
            - _contains tests for ONLY public behavior_
                - _contains unit and sequenced tests_
            - _tests integration between components_
        - **utils**
            - _contains utils classes_

Key Components
=

### Model

- holds all the logic for playing the game and extending the rules/implementation.

### View

- displays the game, currently in text

### Controller

- controls the IO with player, currently not implemented
- handles IO to facilitate configuring model via files

### Player

- represents a real, computer player, or AI player.
- holds statistical data about players' wins and losses.
- holds game strategies for which a computer player can use 

# Diving Into Components

## In the model...

This section will talk about concrete implementations and dependencies between subcomponents.
Dependencies will be italicized.

- ### Concrete Model
    - The concrete interface provides enough methods to play the start the game, play the game,
      finish the game, and render the game through a view.
        - This has strong dependencies on the following classes and interfaces. Most classes are
          just value classes, and the interesting subcomponent, game rules, is built to be
          extensible.
        - The concrete class of the model does not itself enforce the game rules, and instead
          delegates to a _Referee_.
- #### Cards
    - Cards are a value class that hold name and _attack values_ in each _cardinal direction_.
      They are also coached by _Coaches_, but their _Coach_ can change mid-game.
    - They can be
      mutated via _Referees_
- #### Attack Values
    - Attack values are just an enumeration of 1-A symbolizing 1-10 in hexadecimal.
- #### Cardinal Directions
    - Represents North, South, East, and West
    - Each direction has its opposite, N<->S, W<->E
- #### Coaches
    - Coaches represent the Red and Blue in the game. A coach is the model's perception of a player.
    - They each have a hand of _cards_ that changes as cards are played, and a color that will not
      change. A
      coach is the winner of
      the game in the model's eyes.
- #### Grids
    - Grids represent a collection of _Cells_. They are responsible for linking _Cells_ to their
      neighbors in respective _Cardinal Directions_. They are also the model's medium between
      _Cards_ and where they go.
- #### Cells
    - Cells are the subcomponents of a Grid. They are responsible for having or not having a _card_,
      maintaining the shape of the grid. They allow _Referees_ to use them as a way to navigate
      recursively to find out which _cards_ need to battle and flip. The _Grid_ has access to a
      mutable version of a cell, and needs to protect the cell's read-only-to-outsiders quality.
- #### Referees
    - Referees keep the rules of the game and enforce battle between _cards_. They rely on _Cards_
      and _Cells_ to give them the information and access they need to update state accordingly.

    - Any RefereeAbstract has ways to mutate Cards. This
      power is given so they can enforce rules how future implementors see fit.

## In the view...
Below are the following classes and interfaces that we have implemented

- #### View Interface
  - This generic interface defines a rendering contract for different types of views, allowing them to output to various destinations. The primary method, renderTo(OD outputDestination), abstracts rendering details across multiple formats, setting the foundation for both text and graphical representations.
  
- #### ViewTextBase Class
    - It shows in the following format:
     Player: (RED/BLUE)

    BB   _

    _B   _

    _ R  _

    Hand:

    cardName1 1 2 3 4

    cardName2 5 6 7 8

- #### ViewGUI class
  
  - ViewGUI provides a graphical interface (GUI) implementation of the View interface for the Three Trios game. It visually renders the game state in a window using Java Swing components. 
  - Key components and methods:
    - Constructors: Accepts a ModelReadOnly instance and initializes GUI components, such as player hands and the grid, using dimensions derived from the model.
    - renderTo(JFrame outputFrame): Configures and displays a JFrame with all components, 
      including a listener to handle window resizing.
    - HandGUI Class: A JPanel subclass that visually represents a player's hand. It adjusts the 
      layout and renders each card based on the current coach, resizing dynamically.
     - GridGUI Class: A JPanel subclass responsible for rendering the game grid, with cells 
      displayed according to their state (e.g., holes, empty cells, or cards). It dynamically resizes to fit the window and visually distinguishes player cards and empty cells using color coding.



## In the player ...
To support various computer player strategies and difficulty levels for the Three Trios game, we introduced several new classes and an enum in the cs3500.threetrios.model.player package. These additions enable flexible AI behaviors and difficulty customization, enhancing the game’s strategic depth. Key components include:

- #### Player Interface
  - Defines the structure for a player in the game and includes methods to:
  - Retrieve the player’s name (getName()).
  - Assess player skill level through the Difficulty enumeration (difficulty()).
  - Generate the player’s next move as a Consumer<Model> (nextMove()), allowing integration with 
    different strategies.
  
- #### Difficulty Enum
  - Represents the difficulty level of a player, offering five distinct values: 
  - EASY, MEDIUM, HARD, 
    IMPOSSIBLE, and UNKNOWN.
  - This enum enables the game to adjust strategies or player skill levels based on selected 
    difficulty, allowing for varied gameplay experiences.
- #### Move Class
  - Represents a single move, defined by a row and column position on the grid and an index 
    pointing to a card in the player’s hand.
  - Implements Consumer<Model>, enabling moves to be directly applied to the game model.
  - ##### Key methods:
    - of(int row, int col, int handIdx): Static factory method to create a new move.
    - accept(Model model): Executes the move on the model by placing a card at the specified cell,
      facilitating dynamic strategy implementation.
  - StrategyAbstract Class
    - Abstract base class for strategies, providing core functionality to evaluate and determine the 
        effectiveness of moves based on the model state:
    - allConsideredMoves(): Generates a list of all possible valid moves by placing each card in 
      the player’s hand at various grid positions.
    - effectiveness(Move move): Abstract method allowing subclasses to define specific criteria 
      for evaluating move effectiveness.
    - bestMove(): Returns the most effective move based on the strategy’s criteria, using 
      effectiveness as the evaluation metric.
- This base class is designed for extension, enabling subclasses to implement specific strategies 
      while reusing common move evaluation functions.
- #### CornerStrategy Class
  - A defensive strategy focusing on placing cards in the corners of the grid to minimize exposure 
    to opponent moves.
  - Implements allConsideredMoves() by generating moves for each card in the player’s hand, 
    positioning them in the four corners of the grid.
- #### MostFlips Class
  - A strategy focused on maximizing the number of opponent’s cards flipped with each move.
  - Implements effectiveness(Move move) by calculating the change in opponent card count before 
    and after a move, selecting moves that yield the highest flips.
- These classes, strategies, and difficulty levels provide a flexible framework for AI players in 
    the Three Trios game. By incorporating the Difficulty enum and various strategies, the game can feature AI players with diverse skill levels and strategic behaviors, creating more challenging and varied gameplay experiences.

## In the utils ...

To support flexible event handling in the GUI, we introduced utility classes and enums in the 
cs3500.threetrios.utils.extensions package:

- #### ComponentHandler Class
  - ComponentHandler is a general-purpose event handler for ComponentEvent events, allowing dynamic 
    response mapping:
    - The handle(Predicate<ComponentEvent> question, Runnable response) method enables the 
    registration of custom responses to specific component events, such as resize or visibility changes.
    - The register(Component c) method attaches the ComponentHandler to a specified component, 
      allowing it to listen for component-related events.
  - This handler is used to manage layout adjustments in the GUI, such as resizing elements when 
      the main window size changes.
- #### MouseHandler Class
  - MouseHandler provides customizable event handling for MouseEvent and MouseWheelEvent events, 
  allowing flexible response mappings for mouse interactions:
    - The handle(Predicate<MouseEvent> question, Runnable response) method registers custom 
      responses to various mouse events.
    - The register(Component c) method attaches MouseHandler to a component, enabling it to 
      handle clicks, drags, scrolls, and other mouse interactions.
  - This handler is essential for handling user interactions with the game, such as 
      selecting and placing cards on the grid.
    
- #### WasComponent Enum

  - The WasComponent enum provides predicates for different ComponentEvent types, such as MOVED, 
  RESIZED, SHOWN, and HIDDEN.
  - Each enum constant implements Predicate<ComponentEvent>, enabling easy and readable event 
    handling checks within the ComponentHandler.
- #### WasMouse Enum
  - WasMouse offers a similar approach for MouseEvent types, such as CLICKED, PRESSED, RELEASED, 
      MOVED, and DRAGGED.
  - Each constant implements Predicate<MouseEvent>, allowing MouseHandler to efficiently 
    determine the event type and trigger the appropriate response.
  

- These utility classes and enums simplify event handling within the ViewGUI class, making it 
    more modular and allowing for precise responses to user interactions and component changes. By centralizing event handling logic, they enhance the GUI's flexibility, maintainability, and responsiveness.

# Notes on Extension

This section is primarily a way for us, the developers, to convey abstract _design decisions_
regarding our view for extension of this code The thorough explanation is to help provide a
_why_ for the subtle decisions we made.

First, consider the following table of the concrete classes in the model

- Note: mutable meaning there is a method that an object exposes that has side effect in the scope.

| Class              | Visibility | Extensibility | mutable in package | mutable outside package? |
|--------------------|------------|---------------|--------------------|--------------------------|
| Attack Value       | public     | final         | no                 | no                       |
| Card               | public     | final         | yes                | no                       |
| Cardinal Direction | public     | final         | no                 | no                       |
| Coach              | public     | final         | yes                | no                       |
| Grid               | public     | final         | yes                | no                       |
| GridCellCard       | public     | final         | yes                | no                       |
| GridCellHole       | public     | final         | yes                | no                       |
| Model Base         | public     | final         | yes                | yes                      |
| RefereeDefault     | public     | final         | no                 | no                       |

Note how every single class is final. Let's analyze each type of class and why we prohibit
extension. We have four types of classes. Enumerations, structures, wrappers, and
complex implementation

- Enumerations (Attack Value, Cardinal Direction)
    - implicitly not extensible
- Structure classes (Card, Coach, GridCellCard, GridCellHole)
    - All the behavior they have is reading fields and updating fields, and comparing them with
      each other.
    - These behaviors are not special enough to warrant different implementations.
    - We will depend on these as a base to build our model on.
- Wrapper classes (Grid)
    - All Grid represents is a 2d array that we can read and write from.
        - There is a caveat, which is that there grid sets up its cells' adjacency maps on
          construction. However, because this class is only unique on construction, there is nothing
          else to extend.
- Complex Implementations (Model, Referee)
    - Consider the model. It exposes two dependencies, Grid and Coach. These are in the abstract
      class. Any inheritor has the freedom to work with these classes. A reason for wanting a flat
      structure instead of a tree is that we don't need to up-cast, and instead just use super.
      Also, we don't really want to pollute the project with variations of tiny little rules. This
      version of the game is strict on these rules, like the odd-number of card cells, red goes
      first, etc... If a game extended ModelBase and changed it to allow even numbers of cards, we
      don't consider it a ModelBase anymore. Compose, so the is-a relationship is not compromised
      of its integrity.
    - Similarly, referees impose a strict set of rules, and are visitors. They are made to have a
      flat structure versus a tree, and especially with only three methods to implement, two that
      you basically get for free, it is quite easy to just make new children of RefereeAbstract
      than go and pollute the RefereeDefault semantics.

### Extension without exposed mutation
- Above, the restrictions are focused on access modifiers. However, extensibility is about more
than that. What does a model outside the package or a referee outside the package get to know
about some of its dependencies, like Coach or AGridCell respectively. Doesn't it need to be
able to mutate? 
- The table above suggests that they are effectively immutable outside the package.
However that's not the entire story, as the abstract classes solve these issues with specific 
  protected methods that export important side effect on the respective dependencies. 
- That way, only models or referees can 
mutate their dependencies, and nothing can be done in main, by the controller, or by the view. 
The model has dependencies that it exposes only for other components to see. It handles 
extension and mutation in a protected manner.
- TLDR: The model component is made specifically to close off extension and mutation in every 
  single avenue, except that which preservers the integrity of a ThreeTrios model.

### Assignment 2 Extra Credit   

___
## Changes for Part 2
In this iteration, we refined the model design to strengthen immutability and protect key elements from unauthorized modifications:

- Read-Only Model Interface

  - Introduced a ReadonlyThreeTriosModel interface to separate mutator and observer methods. This 
  ensures that any observation via a read-only model cannot alter the model state, even if accessed by an unauthorized component.
  Visibility of Both Players' Hands

- Enhanced the model to allow observation of both players’ hands at any time. 
  - This adjustment 
  supports visibility needs for the game’s visual representation without exposing mutable references.
  Controlled Card Access

- Identified a potential vulnerability where cards could be accessed and potentially modified 
outside the package through read-only methods.
  - To address this, only referees within the model have access to original card references, while any external observer receives a copy.
  
- Grid and Cell Protection Enhancements
  - Redesigned the Grid and GridCell classes to further protect against external mutation:
    - GridCell was made an abstract, package-protected class, with CardCell and HoleCell as final 
      subclasses.
    - Implemented a visitable interface for GridCell that restricts mutation access only within 
      the visitor pattern’s boundaries.
    - Read-only grid observations return cells that cannot be altered, maintaining immutability 
      for external components.
  
- Removal of Coach Class
  - Simplified the Coach class, reducing it to a mapping of color to a list of cards, as it had no 
   additional behaviors. This eliminated the need for a standalone Coach class.

- Rationale for Mutation Control
  - The model exposes minimal points of mutation, focusing specifically on necessary extensions:
    - ModelAbstract includes setGridCardAt, which allows cards to be set on the grid; however, only 
      copies of the grid are exposed externally.
    - RefereeAbstract provides methods like setCardAttackValue and setCardCoach for internal 
      mutation of card properties. Any public-facing read-only methods ensure these references are copies, maintaining the original’s integrity.
    - To support immutability, maps and lists returned by the model are wrapped in immutable 
      decorators, and structural classes like Grid and Cell are exposed only as copies, avoiding unintended side effects in external contexts.
    
- New Observation Methods
  - Added observation methods to ReadonlyThreeTriosModel to meet assignment requirements:

    - Grid Size
      - numRows() and numCols() provide the dimensions of the grid, helping the view align with the 
      grid’s layout.
    - Cell Content
      - cardAt(int row, int col) returns the card in a specific cell, if present, using Optional<Card> 
      to safely indicate absence.
      - ownerAt(int row, int col) returns the owner (coach) of the card in a cell, if any, using 
          Optional<Coach> to handle null cases safely.
    - Player Hands
      - curCoachesHands() returns each coach’s hand as an unmodifiable map. Each card is provided as a 
            copy to prevent modification, allowing the view to safely display players’ hands.
    - Move Legality
      - canPlayAt(int row, int col) determines whether the current player can place a card at a 
            specific cell, based on cell occupancy and whether it can hold a card.
    - Card Flip Potential
      - numFlippedIfPlaced(Card card, int row, int col) calculates the number of opponent cards that 
            would be flipped if the player places a specified card at given coordinates, supporting decision-making without altering the actual grid.
      - Player Score
        - score(Coach coach) calculates the score for a given coach, based on the number of cards 
            controlled on the grid.

These adjustments maintain model integrity against external modification, refining the MVC structure by clearly defining responsibilities and protecting the model from unintended state changes.


