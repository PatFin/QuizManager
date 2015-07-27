# QuizManager
This repository contains the files for the java desktop application QuizManager.

If you wish to test (or even use) the application, a executable Java ARchive (QuizManager.jar) is available in the Jars folder.

If you wish to access the soure code, you can do so by going into the Quiz/src Folder. It contains 3 packages:
_ deck
_ display
_fileHandler

Package deck contains the classes that deals with a quiz as an object. 
_ Classes Deck and DeckEle consist of a kind of linkedList used to easily manage a quiz. 
_ Class Question contains all the elements of a question, that is the question, a correct answer, three wrong answers and an explanation. 
_ Class DeckFileFilter extends FileFilter and is used when a JFileChooser is opened to let the user choose a deck file.

Package display contains all the classes used to manage the display of the application.
_ MainFrame is (true to its name) the main frame of the application. It uses a CardLayout to alternatively display several Panels. IT CONTAINS A MAIN METHOD THAT LAUNCHES A QUIZMANAGER WINDOW.
_ DeckEditor is the JFrame that handles the editing of decks. IT CONTAINS A MAIN METHOD THAT LAUNCHES A NEW EDITING WINDOW.

_ package fileHandler contains a single class:
_ Class DeckWriter is the class capable of converting a Deck object into a text writable in a file. It also contains a method capable of converting a text (from a file) into a Deck object (if formated correctly).

That's pretty much it. You will find more details in the JAVADOC available in the Quiz/doc/ folder.
