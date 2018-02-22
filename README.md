<h1>Maze-Project</h1>
This program can generate Mazes using Wilson's algorithm.

It can also create Mazes with a bias towards longer passages.

The following command line arguments are expected when ran (Valid values in parenthesis):
* First argument must be the length of the maze (1 - Integer.MAX_VALUE)
* Second argument must be the height of the maze (1 - Integer.MAX_VALUE)
* Third argument must be the bias of the maze (0.50 - 1.0, where 0.5 is unbiased and 1.0 is fully biased)
* Fourth argument must be the amount of mazes to test (1 - Integer.MAX_VALUE)
* Fifth argument must be the amount of random points to test (1 - Integer.MAX_VALUE)

The Mazes generated are tested to have the following properties:
* Complete - Every point in the maze must be reachable by another
* Reachable - Every point in the maze can be reached by all other points

In addition to these properties, when a start/end point are chosen and the shortest path is found, a complexity is established.
This complexity is defined as the length of all the paths that can be taken along the shortest path (the "wrong turns")

This was implemented using JGraphT (http://jgrapht.org/) and a custom built maze generator