This is coded as part of Datastructures and algorithms course taken during fall 2021 @ UTD.

Containes implementation of the following data structures from Mark Allen Weiss,
1. Binary Search Tree
2. LinkedList
3. QuadraticProbing Hashtable
4. Disjoint sets

The author's code is modified to add addional functionalities and to solve additional problems.

The following was done as part of understading algorithms and using datastructures to solve problems:
1. Maze generation - using Disjoint sets.
2. Word Puzzle generation and solving - using quadratic probing hashtable.
3. Finding shortest paths to connect all cities - demonstarion of Kruskal's algorithm using Disjoint sets.

This is run as a lambda function in AWS and exposed as an REST api through API gateway and serves as a backend to the UI app
(https://github.com/krishnanravi03/datastructures-algorithms-ui).


Navigation:
Package descriptions:
1. Algorithms - Kruskal's implementation.
2. com/datastructures - Lambda handler.
3. configs - resource and file paths.
4. datastructures - implementation of all the datastructures.
5. examples - Demonstration of all the implmentations and some functions of datastructures. This can take in user inputs/console inputs.
6. helpers - some external classes and common functions used across other classes.
7. Projects - Logic for maze/puzzle generation.

This project can also be run as a stand-alone console app.
Coded using Java 8, jdk 17

