# QuizManager
This repository contains the files for the java desktop application QuizManager.
We are currently at version 1.0 of the software.

<h2>You wish to use or download the software?</h2>

You can go to the project page http://patfin.github.io/QuizManager !
You should find enough information there. 

<h2>You are an interested Devlopper?</h2>

You can either fork this project and do your own stuff with it.

Or you can try to go into the dev branch and see what will be coming soon and what is left to do!

<h3>Javadoc</h3>
You will find the JAVADOC in the Quiz/doc/ folder.

<h3>package organisation in the main branch</h3>

<ul>
<li>deck</li>
<li>display</li>
<li>fileHandler</li>
</ul>

<h4>Package deck</h4> 
It contains the classes that deals with a quiz as an object. 
<ul>
<li>Classes Deck and DeckEle consist of a kind of linkedList used to easily manage a quiz. </li>
<li>Class Question contains all the elements of a question, that is the question, a correct answer, three wrong answers and an explanation. </li>
<li>Class DeckFileFilter extends FileFilter and is used when a JFileChooser is opened to let the user choose a deck file. </li>
</ul>

<h4>Package display</h4> 
It contains all the classes used to manage the display of the application.
<ul>
<li>MainFrame is (true to its name) the main frame of the application. It uses a CardLayout to alternatively display several Panels. <em>It contains a main method that launches a QuizManager window.</em></li>
<li>DeckEditor is the JFrame that handles the editing of decks. <em>It contains a main method that launches a QuizManager window.</em></li>
</ul>

<h4>Package fileHandler</h4> 
So far it contains a single class:
<ul>
<li>Class DeckWriter is the class capable of converting a Deck object into a text writable in a file. It also contains a method capable of converting a text (from a file) into a Deck object (if formated correctly). </li>
</ul>
