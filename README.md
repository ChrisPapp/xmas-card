# Java Christmas Card

This is a simple Christmas card Java application written as an educational
example to demonstrate the use of diverse Java features.

* Inheritance and dynamic dispatch
* Containers
* Threads
* AWT graphics
* MIDI sound
* JavaDoc comments

# Turkey Hunt Game

Click on the flying Turkeys to kill them and increase your score!
Be careful, and don't miss!

## Additions to the original xmas-card

* Add Turkey class that extends Drawable
* Add a Mouse Listener to DrawPanel, to handle the Turkey Hunting logic
* Decrease Thread sleeping and reduce spawn rates to achieve higher framerates
* Add WavPlayer class, to play shotgun.wav
* Add SoundThread class, so wav sounds can be played without stopping the program
* Add Score class to handle score counting
* Remove MidiPlayer class

## Building

Build with Maven

## Running

Run
`java -jar target/xmas-1.0-jar-with-dependencies.jar`.
