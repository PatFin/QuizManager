# QuizManager
This repository contains the files for the java desktop application QuizManager.
You are in the dev branch of the project.

<h2>You wish to use or download the software?</h2>

You can go to the project page http://patfin.github.io/QuizManager !
You should find enough information there. 

<h2>TO DO</h2>
<h3>Structure</h3>
<ul>
<li>- [X] Make a better separation of what will now be called Quiz Player and Quiz Editor.~~
<p>This should eventually lead to two different java archive downloads. </p>
<p>The user manual will have to be seperated into two different pages (currently a single one)</p>
</li>
</ul>

<h3>New Features</h3>
<h4>in the Editor</h4>
<ul>
<li>- [X] Implement a warning against unsaved document when the user is about to remove all the current fields (on New Deck option for instance</li>
<li>- [ ] Implement an overview system to allow the user to see all the questions at once</li>
<li>- [X] Implement a question check upon file saving. Warn the user that some questions are empty and propose removal of those</li>
<li>- [ ] Add a toolbar to the editor (with the possibility to hide it in Preferences menu) with buttons for question insert and removal, deck save options and so on ...</li>
<li>- [X] Add a message on save indicating success</li>
<li>- [X] Add a link to user manual in the MenuBar</li>
<li>- [ ] ...</li>
</ul>

<h4>in the Player</h4>
<ul>
<li>- [X] Propose an option after a quiz to repeat the quiz with only the questions on which the user had trouble getting the right answer
<p>In Learning mode -> Put questions with more than X nb of wrong answers, in Test mode -> Put the questions to which the user gave a wrong answer. ~ Debug done</p> 
</li> 
<li>- [X] Add a preferences save system ~ Debug done</li>
<li>- [X] Add nb failures to add question to difficult in Learning mode to Preferences options.</li>
<li>- [X] Add possibility to skip congratulation message in Learning mode and go to next question when a good answer is provided</li>
<li>- [ ] ...</li>
</ul>
