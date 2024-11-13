- add read only model
- make it so you can see both players hands at any given time

TLDR: now, any observation from read only model, if somehow mutated,
via some imposter model or imposter referee, 
won't have an effect on the model.





- saw a liability with where cards outside the package could be
- grabbed by a read only method and then passed into a fake referee
- now, only referees working inside a model have access to the same pointers as the ones in the 
  model
- referees who just get a card from a read only model don't have the actual pointer

- also protected grid 
  - made grid cell abstract package protected
  - made a composition read only version also package protected 
  - grid cell visitable interface for extension
  - card cell and hole cell are final but you can only get them
    - when you are inside a visitor
    - any read only observation of a model will return a cell that 
    - cannot be mutated

got rid of coaches. they're just a map from color to list of card
there's no other behavior so it's not even worth a class

Rationale:

What mutation does our model expose for extension?
ModelAbstract 
- setGridCardAt
  - sets a card on a grid
  - so, we want the grid from get grid to just be some copy
  
RefereeAbstract
- setCardAttackValue
  - mutate card's internal map of direction -> attack value 
- setCardCoach
  - mutate a card's internal coach color

We want to make sure that any _public_ read-only method
returns copies for this stuff, because even though outside the package,
they are read only, the inheritors of the model package's abstract
classes can actually mutate them


the model provides objects that have methods with effect
- for maps and lists, wrap them in immutable decorators 
  - mutation isn't necessary, they can re-wrap if they want mutation
the model returns structure classes 
- ensure we avoid mutation

- grid
  - placeCard is dangerous, it effects grid and cell
  - we have to copy grid and cell
  - cell can return a card, we have to copy that too

