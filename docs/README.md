# Table Of Contents


<!-- TOC -->


* [Table Of Contents](#table-of-contents)
* [Introduction](#introduction)
  * [Info](#info)
  * [Rules](#rules)
* [Quick Start](#quick-start)
* [Command Line args for Assignment 8](#command-line-args-for-assignment-8)
* [Getting Around the Project](#getting-around-the-project)
* [Key Components](#key-components)
  * [Model](#model)
  * [View](#view)
  * [Controller](#controller)
  * [Player](#player)
* [Diving Into Components](#diving-into-components)
  * [In the model...](#in-the-model)
  * [In the view...](#in-the-view)
  * [In the player ...](#in-the-player-)
  * [In the utils ...](#in-the-utils-)
* [Notes on Extension](#notes-on-extension)
  * [Extension without exposed mutation](#extension-without-exposed-mutation)
* [Part 2](#part-2)
  * [Assignment 6 Extra Credit](#assignment-6-extra-credit)
  * [Changes for Part 2](#changes-for-part-2)
* [Part 3](#part-3)
  * [New Classes](#new-classes)
    * [In the model...](#in-the-model)
    * [In the controller...](#in-controller)
    * [In the view ...](#changes-in-the-view)
* [Part 4 / Adapting to provider's code](#adapting-to-providers-code-)
  * [Features we were able to get working ...](#features-we-were-able-to-get-working)
  * [Features we were not able to get working ...](#features-we-were-not-able-to-get-working)
  * [Why we removed our testing from homework 5-7](#why-we-removed-our-testing-from-homework-5-to-7)


<!-- TOC -->

Introduction
=

This is the ThreeTrios project. ThreeTrios is a two player card game played on a grid.


### Info


- The grid has cells. The cells are capable of holding a card (card cells), or are not (
  referred to as a hole)
- The two players are associated with red and blue.
- The cards have attack values with values 1-10, represented 1-A, and can be associated
  with red or blue.
- Cards are placed onto empty card cells in the grid. Cards battle adjacent cards of
  different colors.


### Rules


1. The grid must have an odd number of card cells.
2. Both players must have an even number of cards, which add up to one plus the number of
   card cells.
3. Red always goes first.
4. The winner of a card battle is the one with higher attack value in the direction of
   battle. Losers of battle flip colors when the current player is associated with a
   different color.
5. The winner is the one who is associated with the most cards of their color, including
   the cards in their hand.


- Ties are possible if the second player has one less card on the grid, because their hand
  has one more card.


Quick Start
=
Go to main and press run! Select and deselect cards.

Command Line Args for Assignment 8
=
Main 0 4
or 
0 4

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
        - _contains interfaces and classes that define the player strategies for three
          trios_
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


This section will talk about concrete implementations and dependencies between
subcomponents. Dependencies will be italicized.


- ### Concrete Model
  - The concrete interface provides enough methods to play the start the game, play the
    game, finish the game, and render the game through a view.
    - This has strong dependencies on the following classes and interfaces. Most classes
      are just value classes, and the interesting subcomponent, game rules, is built to be
      extensible.
    - The concrete class of the model does not itself enforce the game rules, and instead
      delegates to a _Referee_.
- #### Cards
  - Cards are a value class that hold name and _attack values_ in each _cardinal
    direction_. They are also coached by _Coaches_, but their _Coach_ can change mid-game.
  - They can be mutated via _Referees_
- #### Attack Values
  - Attack values are just an enumeration of 1-A symbolizing 1-10 in hexadecimal.
- #### Cardinal Directions
  - Represents North, South, East, and West
  - Each direction has its opposite, N<->S, W<->E
- #### Coaches
  - Coaches represent the Red and Blue in the game. A coachColor is the model's perception
    of a player.
  - They each have a hand of _cards_ that changes as cards are played, and a color that
    will not change. A coachColor is the winner of the game in the model's eyes.
- #### Grids
  - Grids represent a collection of _Cells_. They are responsible for linking _Cells_ to
    their neighbors in respective _Cardinal Directions_. They are also the model's medium
    between
    _Cards_ and where they go.
- #### Cells
  - Cells are the subcomponents of a Grid. They are responsible for having or not having a
    _card_, maintaining the shape of the grid. They allow _Referees_ to use them as a way
    to navigate recursively to find out which _cards_ need to battle and flip. The _Grid_
    has access to a mutable version of a cell, and needs to protect the cell's
    read-only-to-outsiders quality.
- #### Referees
  - Referees keep the rules of the game and enforce battle between _cards_. They rely on
    _Cards_
    and _Cells_ to give them the information and access they need to update state
    accordingly.
  
  - Any RefereeAbstract has ways to mutate Cards. This power is given so they can enforce
    rules how future implementors see fit.


## In the view...


Below are the following classes and interfaces that we have implemented


- #### View Interface
  - This generic interface defines a rendering contract for different types of views,
    allowing them to output to various destinations. The primary method, renderTo(OD
    outputDestination), abstracts rendering details across multiple formats, setting the
    foundation for both text and graphical representations.

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
  
  - ViewGUI provides a graphical interface (GUI) implementation of the View interface for
    the Three Trios game. It visually renders the game state in a window using Java Swing
    components.
  - Key components and methods:
    - Constructors: Accepts a ModelReadOnly instance and initializes GUI components, such
      as player hands and the grid, using dimensions derived from the model.
    - renderTo(JFrame outputFrame): Configures and displays a JFrame with all components,
      including a listener to handle window resizing.
    - HandGUI Class: A JPanel subclass that visually represents a player's hand. It
      adjusts the layout and renders each card based on the current coachColor, resizing
      dynamically.
    - GridGUI Class: A JPanel subclass responsible for rendering the game grid, with cells
      displayed according to their state (e.g., holes, empty cells, or cards). It
      dynamically resizes to fit the window and visually distinguishes player cards and
      empty cells using color coding.


## In the player ...


To support various computer player strategies and difficulty levels for the Three Trios
game, we introduced several new classes and an enum in the model.player package. These
additions enable flexible AI behaviors and difficulty customization, enhancing the game’s
strategic depth. Key components include:


- #### Player Interface
  - Defines the structure for a player in the game and includes methods to:
  - Retrieve the player’s name (getName()).
  - Assess player skill level through the Difficulty enumeration (difficulty()).
  - Generate the player’s next move as a Consumer<Model> (nextMove()), allowing
    integration with different strategies.

- #### Difficulty Enum
  - Represents the difficulty level of a player, offering five distinct values:
  - EASY, MEDIUM, HARD, IMPOSSIBLE, and UNKNOWN.
  - This enum enables the game to adjust strategies or player skill levels based on
    selected difficulty, allowing for varied gameplay experiences.
- #### Move Class
  - Represents a single move, defined by a row and column position on the grid and an
    index pointing to a card in the player’s hand.
  - Implements Consumer<Model>, enabling moves to be directly applied to the game model.
  - ##### Key methods:
    - of(int row, int col, int handIdx): Static factory method to create a new move.
    - accept(Model model): Executes the move on the model by placing a card at the
      specified cell, facilitating dynamic strategy implementation.
  - StrategyAbstract Class
    - Abstract base class for strategies, providing core functionality to evaluate and
      determine the effectiveness of moves based on the model state:
    - allConsideredMoves(): Generates a list of all possible valid moves by placing each
      card in the player’s hand at various grid positions.
    - effectiveness(Move move): Abstract method allowing subclasses to define specific
      criteria for evaluating move effectiveness.
    - bestMove(): Returns the most effective move based on the strategy’s criteria, using
      effectiveness as the evaluation metric.
- This base class is designed for extension, enabling subclasses to implement specific
  strategies while reusing common move evaluation functions.
- #### CornerStrategy Class
  - A defensive strategy focusing on placing cards in the corners of the grid to minimize
    exposure to opponent moves.
  - Implements allConsideredMoves() by generating moves for each card in the player’s
    hand, positioning them in the four corners of the grid.
- #### MostFlips Class
  - A strategy focused on maximizing the number of opponent’s cards flipped with each
    move.
  - Implements effectiveness(Move move) by calculating the change in opponent card count
    before and after a move, selecting moves that yield the highest flips.
- These classes, strategies, and difficulty levels provide a flexible framework for AI
  players in the Three Trios game. By incorporating the Difficulty enum and various
  strategies, the game can feature AI players with diverse skill levels and strategic
  behaviors, creating more challenging and varied gameplay experiences.


## In the utils ...


To support flexible event handling in the GUI, we introduced utility classes and enums in
the utils.extensions package:


- #### ComponentHandler Class
  - ComponentHandler is a general-purpose event handler for ComponentEvent events,
    allowing dynamic response mapping:
    - The handle(Predicate<ComponentEvent> question, Runnable response) method enables the
      registration of custom responses to specific component events, such as resize or
      visibility changes.
    - The register(Component c) method attaches the ComponentHandler to a specified
      component, allowing it to listen for component-related events.
  - This handler is used to manage layout adjustments in the GUI, such as resizing
    elements when the main window size changes.
- #### MouseHandler Class
  - MouseHandler provides customizable event handling for MouseEvent and MouseWheelEvent
    events, allowing flexible response mappings for mouse interactions:
    - The handle(Predicate<MouseEvent> question, Runnable response) method registers
      custom responses to various mouse events.
    - The register(Component c) method attaches MouseHandler to a component, enabling it
      to handle clicks, drags, scrolls, and other mouse interactions.
  - This handler is essential for handling user interactions with the game, such as
    selecting and placing cards on the grid.

- #### WasComponent Enum
  
  - The WasComponent enum provides predicates for different ComponentEvent types, such as
    MOVED, RESIZED, SHOWN, and HIDDEN.
  - Each enum constant implements Predicate<ComponentEvent>, enabling easy and readable
    event handling checks within the ComponentHandler.
- #### WasMouse Enum
  - WasMouse offers a similar approach for MouseEvent types, such as CLICKED, PRESSED,
    RELEASED, MOVED, and DRAGGED.
  - Each constant implements Predicate<MouseEvent>, allowing MouseHandler to efficiently
    determine the event type and trigger the appropriate response.


- These utility classes and enums simplify event handling within the ViewGUI class, making
  it more modular and allowing for precise responses to user interactions and component
  changes. By centralizing event handling logic, they enhance the GUI's flexibility,
  maintainability, and responsiveness.


# Notes on Extension


This section is primarily a way for us, the developers, to convey abstract _design
decisions_
regarding our view for extension of this code The thorough explanation is to help provide
a
_why_ for the subtle decisions we made.


First, consider the following table of the concrete classes in the model


- Note: mutable meaning there is a method that an object exposes that has side effect in
  the scope.


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
extension. We have four types of classes. Enumerations, structures, wrappers, and complex
implementation


- Enumerations (Attack Value, Cardinal Direction)
  - implicitly not extensible
- Structure classes (Card, Coach, GridCellCard, GridCellHole)
  - All the behavior they have is reading fields and updating fields, and comparing them
    with each other.
  - These behaviors are not special enough to warrant different implementations.
  - We will depend on these as a base to build our model on.
- Wrapper classes (Grid)
  - All Grid represents is a 2d array that we can read and write from.
    - There is a caveat, which is that there grid sets up its cells' adjacency maps on
      construction. However, because this class is only unique on construction, there is
      nothing else to extend.
- Complex Implementations (Model, Referee)
  - Consider the model. It exposes two dependencies, Grid and Coach. These are in the
    abstract class. Any inheritor has the freedom to work with these classes. A reason for
    wanting a flat structure instead of a tree is that we don't need to up-cast, and
    instead just use super. Also, we don't really want to pollute the project with
    variations of tiny little rules. This version of the game is strict on these rules,
    like the odd-number of card cells, red goes first, etc... If a game extended ModelBase
    and changed it to allow even numbers of cards, we don't consider it a ModelBase
    anymore. Compose, so the is-a relationship is not compromised of its integrity.
  - Similarly, referees impose a strict set of rules, and are visitors. They are made to
    have a flat structure versus a tree, and especially with only three methods to
    implement, two that you basically get for free, it is quite easy to just make new
    children of RefereeAbstract than go and pollute the RefereeDefault semantics.


### Extension without exposed mutation


- Above, the restrictions are focused on access modifiers. However, extensibility is about
  more than that. What does a model outside the package or a referee outside the package
  get to know about some of its dependencies, like Coach or AGridCell respectively.
  Doesn't it need to be able to mutate?
- The table above suggests that they are effectively immutable outside the package.
  However that's not the entire story, as the abstract classes solve these issues with
  specific protected methods that export important side effect on the respective
  dependencies.
- That way, only models or referees can mutate their dependencies, and nothing can be done
  in main, by the controller, or by the view. The model has dependencies that it exposes
  only for other components to see. It handles extension and mutation in a protected
  manner.
- TLDR: The model component is made specifically to close off extension and mutation in
  every single avenue, except that which preservers the integrity of a ThreeTrios model.


# Part 2


### Assignment 6 Extra Credit


For the extra credit, we implemented Defense Strategy as Strategy 3. This defensive
approach evaluates potential moves based on minimizing a card's exposure to opponents,
aiming to reduce vulnerability and maintain control over key positions on the board.


- Strategy Explanation
- Defense Strategy prioritizes placing cards where they have the fewest exposed sides,
  making it more challenging for the opponent to flip them in subsequent turns. This is
  calculated by counting the number of adjacent cells that remain unoccupied (exposed)
  after placing the card. Move Effectiveness:
  - The effectiveness method assigns higher scores to moves with fewer exposed sides and
    lower scores to moves with greater exposure. Additionally, it considers the attack
    values on exposed sides, allowing moves that are not only defensively sound but also
    better equipped to fend off opponent moves.
  - Location of Implementations
    - Class: DefenseStrategy
    - Package: model.player
  - Methods: The key methods implementing this strategy are:
    - effectiveness: Calculates the defensive value of a move by minimizing exposed sides
      and summing attack values on exposed sides.
    - getExposedSides: Identifies which sides of a cell would be exposed if a card were
      placed there, guiding the calculation of the defensive score.
    - Testing
      - Location of Tests: Tests for Defense Strategy are located in the
        test.cs3500.test.player.testDefenseStrategy player package


## Changes for Part 2


In this iteration, we refined the model design to strengthen immutability and protect key
elements from unauthorized modifications:


- Read-Only Model Interface
  
  - Introduced a ReadonlyThreeTriosModel interface to separate mutator and observer
    methods. This ensures that any observation via a read-only model cannot alter the
    model state, even if accessed by an unauthorized component. Visibility of Both
    Players' Hands

- Enhanced the model to allow observation of both players’ hands at any time.
  - This adjustment supports visibility needs for the game’s visual representation without
    exposing mutable references. Controlled Card Access

- Identified a potential vulnerability where cards could be accessed and potentially
  modified outside the package through read-only methods.
  - To address this, only referees within the model have access to original card
    references, while any external observer receives a copy.

- Grid and Cell Protection Enhancements
  - Redesigned the Grid and GridCell classes to further protect against external mutation:
    - GridCell was made an abstract, package-protected class, with CardCell and HoleCell
      as final subclasses.
    - Implemented a visitable interface for GridCell that restricts mutation access only
      within the visitor pattern’s boundaries.
    - Read-only grid observations return cells that cannot be altered, maintaining
      immutability for external components.

- Removal of Coach Class
  - Simplified the Coach class, reducing it to a mapping of color to a list of cards, as
    it had no additional behaviors. This eliminated the need for a standalone Coach class.

- Rationale for Mutation Control
  - The model exposes minimal points of mutation, focusing specifically on necessary
    extensions:
    - ModelAbstract includes setGridCardAt, which allows cards to be set on the grid;
      however, only copies of the grid are exposed externally.
    - RefereeAbstract provides methods like setCardAttackValue and setCardCoach for
      internal mutation of card properties. Any public-facing read-only methods ensure
      these references are copies, maintaining the original’s integrity.
    - To support immutability, maps and lists returned by the model are wrapped in
      immutable decorators, and structural classes like Grid and Cell are exposed only as
      copies, avoiding unintended side effects in external contexts.

- New Observation Methods
  - Added observation methods to ReadonlyThreeTriosModel to meet assignment requirements:
    
    - Grid Size
      - numRows() and numCols() provide the dimensions of the grid, helping the view align
        with the grid’s layout.
    - Cell Content
      - cardAt(int row, int col) returns the card in a specific cell, if present, using
        Optional<Card>
        to safely indicate absence.
      - ownerAt(int row, int col) returns the owner (coachColor) of the card in a cell, if
        any, using Optional<Coach> to handle null cases safely.
    - Player Hands
      - curCoachesHands() returns each coachColor’s hand as an unmodifiable map. Each card
        is provided as a copy to prevent modification, allowing the view to safely display
        players’ hands.
    - Move Legality
      - canPlayAt(int row, int col) determines whether the current player can place a card
        at a specific cell, based on cell occupancy and whether it can hold a card.
    - Card Flip Potential
      - numFlippedIfPlaced(Card card, int row, int col) calculates the number of opponent
        cards that would be flipped if the player places a specified card at given
        coordinates, supporting decision-making without altering the actual grid.
      - Player Score
        - score(Coach coachColor) calculates the score for a given coachColor, based on
          the number of cards controlled on the grid.


These adjustments maintain model integrity against external modification, refining the MVC
structure by clearly defining responsibilities and protecting the model from unintended
state changes.

# Part 3
## Changes for part 3
### New classes
#### In the model
1. GameListener (Interface)
   - Purpose: Acts as a listener for a playable game, capable of processing moves and 
   allowing access to the current game state.
   - Key Features:
     - Extends BiConsumer with parameters:
       - Consumer<Move>: Accepts moves for the game.
       - Callable<Model>: Provides the current game state when needed.
     - Abstracts the interaction between move processing and game state management.
2. GamePlayer (Interface)
   - Purpose: Represents a player (human or AI) who interacts with the game model and 
   completes moves based on the current state.
   - Key Features:
     - Extends BiConsumer:
       - Consumer<Move>: To accept moves chosen by the player.
       - Supplier<Model>: To provide the current state of the game.
     - Enables interaction between a player and the game by supplying the model state 
         and processing moves.
3. PlayableGame (Interface)
   - Purpose: Defines a playable game that can start with given players and a game model.
   - Key Features:
     - Method start: Begins the game with a Supplier<Model> (provides the model) and two 
       GameListener instances (representing red and blue players).
4. PlayableGameImpl (Class)
   - Purpose: Implements the PlayableGame interface to manage the execution of the game 
   between two players, handling moves and game state updates.
   - Key Features:
     - Fields:
       - Supplier<Model>: Supplies the game model.
       - Deque<Move>: Stores moves for replaying and replicating game state.
       - GameListener red, blue: Listeners for red and blue players.
       - Model cache: Caches the current game state.
     - Methods:
       - start: Initializes the game with players and the model.
       - onMove: Processes a move, updating the game state and handling errors.
       - remakeGame: Recreates the game state by replaying all moves.
       - Helper methods to determine the current player (curPlayerListener) and their 
         opponent (otherPlayerListener).
     - Error Handling:
       - Handles invalid moves and ensures the game state is consistent through the 
       remakeGame method.
     - Game Over Handling:
       - Detects when the game is over and throws a RuntimeException indicating the 
         winner.
#### In controller...
1. AbstractControlPlayer (Abstract Class)
   - Purpose: Serves as a base class for controllers managing player interactions in 
   the game.
   - Key Features:
     - Implements GameListener for listening to game events and processing moves.
     - Abstracts shared functionality for game controllers.
     - Fields:
       - CoachColor color: Indicates the player's team color (red or blue).
       - GameView view: Manages the game's visual representation.
       - GamePlayer player: Represents the associated player.
     - Constructor:
       - Accepts CoachColor, GameView, and GamePlayer to initialize the controller.
2. ControlPlayer (Class)
   - Purpose: Implements the behavior of a player controller in the game. Extends 
   AbstractControlPlayer.
     - Key Features:
       - Method:
         - accept(Consumer<Move> moveConsumer, Callable<Model> modelCallable):
           - Interacts with the game model via the callable.
           - Retrieves the game model, renders the current state, and processes player 
             moves.
           - Handles Exceptions:
             - Uses a Supplier<Model> to safely call the model and catch errors.
             - Renders error messages to the view if exceptions occur.
           - Checks if the game is over before processing moves.
           - Delegates move execution to the associated GamePlayer.
#### Changes in the view...
1. DrawGrid
   - Purpose: Renders the game grid onto a BufferedImage, enabling direct rendering for 
   testing purposes or generating visual outputs (e.g., PNG files).
   - Key Features:
   - Colors cells based on their state (e.g., hole, empty card, or occupied).
   - Calculates cell dimensions dynamically based on grid size and image dimensions.
   - Includes utilities for mapping pixel positions back to grid cells.
2. DrawHand
   - Purpose: Renders a player's hand of cards onto a BufferedImage.
   - Key Features:
     - Visually differentiates between red and blue cards.
     - Displays attack values in cardinal directions (N, S, E, W) on the card image.
     - Includes utilities for determining which card corresponds to a given pixel.
3. GameView (Interface)
   - Purpose: Defines methods for rendering game messages and the read-only model state.
   - Key Features:
     - renderMessage: Displays textual messages (e.g., errors or notifications).
     - renderModel: Renders the game's read-only model for visualization.
4. GUIGridBase
   - Purpose: Provides a graphical representation of the game grid in a GUI context.
   - Key Features:
     - Uses DrawGrid for rendering grid visuals.
     - Supports updates to the grid model and repainting.
5. GUIGridInteractive
   - Purpose: Extends GUIGridBase to add interactivity, allowing users to click on the 
   grid.
   - Key Features:
     - Handles mouse interactions via the MouseHandler utility.
     - Allows users to select cells for moves and provides callbacks to update the game 
       state.
     - Ensures proper turn-taking by disabling input when it's not the player's turn.
6. GUIHandBase
   - Purpose: Provides a graphical representation of a player's hand in the GUI.
   - Key Features:
     - Uses DrawHand for rendering the hand's visuals.
     - Supports updates to the hand and dynamic repainting.
7. GUIHandInteractive
   - Purpose: Extends GUIHandBase to enable interactivity, such as selecting cards by 
   clicking or hovering.
   - Key Features:
     - Tracks hover and selection states for cards.
     - Uses a TriConsumer to integrate with the game logic.
     - Highlights selected and hovered cards visually.
8. GUIPlayerDelegate
   - Purpose: Provides a complete GUI representation for a player (hand and grid).
   - Key Features:
     - Combines GUIHandBase, GUIHandInteractive, and GUIGridBase/GUIGridInteractive.
     - Manages layout and updates views dynamically based on the model state.
     - Implements GameView to render messages and the model.
9. GUIPlayerInteractive
   - Purpose: Extends GUIPlayerDelegate to handle player interactions during the game.
   - Key Features:
     - Implements GamePlayer to interact with the model and facilitate player actions.
     - Ensures smooth gameplay with interactive hands and grid.
     - Uses a "glass pane" to block actions when it's not the player's turn, with 
       appropriate notifications.

# Adapting to provider's code 
### Features we were able to get working

- The GUI is displayed when the code is run
  - player 1 is rendered with our view
  - player 2 is rendered with our provider's view
- We can play moves(up three total between players)
  - player 1 can play a card anywhere after selecting a card
  - player 2 can play a card anywhere after selecting a card from the opposite players deck and 
    then selecting a card from their own deck
  - player 1 can then play a card anywhere after selecting one a card from their own deck
  - when player 2 tries to play, an error is propagated: "can't place card on card cell twice". 
    This error does not propagate as a result of playing a card on a single cell twice.
- Errors are propagated(up to move three)
  - errors include ...
    - "cannot place card on cell twice"
    - "card must be selected"
    - "not your turn"



### Features we were not able to get working
- functionality past move 3
  - We are unsure as to why this bug exists. From our bug fixing efforts, we have concluded that 
    the provider's code did one or more of the following that prevented it from being fully 
    adaptable
    - made assumptions about the size of the panel in methods such as gridSize()
    - used different threads
    - made assumptions about the number of rows of columns in a grid
### Why we removed our testing from homework 5 to 7

- in order to remain under the file limit, we excluded our test files from our submission. We 
  see this as an acceptable solution as no new testing was added, and our previous testing had 
  already evaluated by an instructor.