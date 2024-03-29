<h1>Maze-Project</h1>
This program can generate Mazes using Wilson's algorithm.

It can also create Mazes with a bias towards longer passages.

The following command line arguments are expected when ran (Valid values in parenthesis):
* First argument must be the starting size (1 - Integer.MAX_VALUE)
* Second argument must be the ending size (1 - Integer.MAX_VALUE)
* Third argument must be the bias of the maze (0.50 - 1.0, where 0.5 is unbiased and 1.0 is fully biased)
* Fourth argument must be the amount of mazes to test per size (1 - Integer.MAX_VALUE)
* Fifth argument must be the size gap between each test (1 - Integer.MAX_VALUE)

Defaults will be used

The Mazes generated are tested to have the following properties:
* Complete - Every point in the maze must be reachable by another
* Reachable - Every point in the maze can be reached by all other points

This was implemented using JGraphT (http://jgrapht.org/) and a custom built maze generator

Included with the project is an R Script that is designed to graph the output spreadsheet