android-slide-down-view
=======================

This is a proof-of-concept project that demonstrates how to create a view element that can be hidden/shown by tapping and dragging up and down, similar to the Android notification bar.

The view is composed of two main elements
    
  1.  A **content container** (where you would actually place content) 
  2.  A **handle** that is used to hide/reveal the content container

Features:
--------

* The SlideDownView hides/reveals content as the handle is dragged up and down
* The SlideDownView animates to an open or closed position upon
* Content in the content container does not resize (just gets hidden/revealed)
