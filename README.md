# QuizManager
This repository contains the files for the java desktop application QuizManager.

<h1>You wish to use the software for your own purposes?</h1>

If you are interested into using the sofware, you can refer to the following page: <a href="http://patfin.github.io/QuizManager">http://patfin.github.io/QuizManager</a>. You will find there a link to <a href="http://patfin.github.io/QuizManager#download" target="_blank"> download the software and to the <a href="http://patfin.github.io/QuizManager/QuizManager_Manual_v1.0.html" target="_blank">User Manual</a>. 

<h1>You want the software to do more things?</h1>

You like the software and maybe even use it, but you found a bug or you wish it could do more things? Feel free to <a href="mailto:finnertypatri@aol.fr" target="_blank">send me an e-mail</a> describing whatever goes wrong with the software you have. I will try to answer in the best delays and study the problem you encountered. This is not a guarnantee that I will be able to provide support for the application or that what you would like the software to do will be implemented.

<h1>You are an interested Devlopper?</h1>

If you wish to access the soure code, you can do so by going into the Quiz/src Folder. It contains 3 packages:
<ul>
<li>deck</li>
<li>display</li>
<li>fileHandler</li>
</ul>

<h2>Package deck</h2> 
It contains the classes that deals with a quiz as an object. 
<ul>
<li>Classes Deck and DeckEle consist of a kind of linkedList used to easily manage a quiz. </li>
<li>Class Question contains all the elements of a question, that is the question, a correct answer, three wrong answers and an explanation. </li>
<li>Class DeckFileFilter extends FileFilter and is used when a JFileChooser is opened to let the user choose a deck file. </li>
</ul>

<h2>Package display</h2> 
It contains all the classes used to manage the display of the application.
<ul>
<li>MainFrame is (true to its name) the main frame of the application. It uses a CardLayout to alternatively display several Panels. <em>It contains a main method that launches a QuizManager window.</em></li>
<li>DeckEditor is the JFrame that handles the editing of decks. <em>It contains a main method that launches a QuizManager window.</em></li>
</ul>

<h2>Package fileHandler</h2> 
So far it contains a single class:
<ul>
<li>Class DeckWriter is the class capable of converting a Deck object into a text writable in a file. It also contains a method capable of converting a text (from a file) into a Deck object (if formated correctly). </li>
</ul>

<h2>Javadoc</h2>
You will find the JAVADOC in the Quiz/doc/ folder.
