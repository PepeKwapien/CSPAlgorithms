# Constraint Satisfaction Problem
Implemented two CSP algorithms:

* Backtracking
* Forward checking

Algorithms are universal - all thanks to interfaces which have to be implemented by specific elements of new CSP problems.

Available interfaces:
* IVariable - it's an interface that has to be implemented by an element that 'variable' in our CSP problem. Usually a variable is a single element (e.g. color of the house, nationality of a person living in a house) but in this project variable can be treated as a group of variables (e.g. house that can be described by its color, nationality of a person living in it, pet that this person has). IVariable has to contain collection of IValue
* IValue - technically it's an element that you would normally call a variable in a CSP problem because it's a single element that has a domain of possible values. Possible values are of type String
* IConstraint - it's a constraint that's used to check if our current csp solution is correct. It takes array of IVariables and returns true if everything is alright and false otherwise
* IChooseVariable - heuristics to choose next variable from CSP - it sorts array of IVariable
* IChooseValue - heuristics to choose next value from IValue - it sorts collection of strings in domain of IValue

Additionally there are two implemented problems - [Einstein's Riddle/Zebra Puzzle](https://en.wikipedia.org/wiki/Zebra_Puzzle) and [Map Coloring](https://en.wikipedia.org/wiki/Graph_coloring)
