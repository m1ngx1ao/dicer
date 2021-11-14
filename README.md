Small playground for evaluating strategies for the game of
* throwing a dice and retaining its points a limited amount of times
* to finally arrive at the pre-defined number of total points.

The rules are defined by the values of
* `targetPoints` How many points are needed to win? *(default 15)*
* `diceSides` How many sides does the dice have? *(default 6)*
* `maxThrows` How many times are we allowed to throw the dice? *(default 10)*
* `maxRetains` How many times are we allowed to retain the dice result? *(default 6)*

To execute, use:
* `OneGameVisualizer` for getting an overview of what happened in one game.
* `Simulator` for playing 100,000 games and obtaining the success rate.

Currently, there are the following strategies:
* Trivial strategy `TrivialContestant` that has a win-rate of $\frac{2}{3}$.
* Dynamic programming based `ProbabilityTensor` that has an optimal win-rate of about $71.45\%$.
* And there is a further surprise strategy...
