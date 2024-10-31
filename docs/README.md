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
        - **test**
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

### In the view...

- The concrete interface just promises to render a view of the game.
- So far we just have a simple text view implemented. It shows in the following format:

Player: (RED/BLUE)

BB   _

_B   _

_ R  _

Hand:

cardName1 1 2 3 4

cardName2 5 6 7 8

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