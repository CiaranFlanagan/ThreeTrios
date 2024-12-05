Tickets, Reasons, and Changes

1. We sent the model for view as it was just a wrapper that we mandated was used in 
   our view to protect the model from mutation in a read only view. 

2. Next, we made interfaces for Card and Grid because we were under
   planning on those being dependencies, but they were immutable outside the package 
   which made it hard to adapt.

3. We received a change request for a bug in our renderCard() method in DrawHand, but 
   we did not have the time to change it since it was on the last day. We were 
   rendering cards backwards but didn't catch it because all our cards in examples had 
   the same numbers. 
