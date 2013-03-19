android-slide-down-view
=======================

This is a proof-of-concept project that demonstrates how to create a view element that can be hidden/shown by tapping and dragging up and down on a handle, similar to the Android notification bar.

The view is composed of two main elements
    
  1.  A **content container** (where you would actually place content) 
  2.  A **handle** that is used to hide/reveal the content container

Features:
--------

* Makes use of the [NineOldAndroid library](http://nineoldandroids.com/) which means this is compatible as far back as Android 1.0 (only tested on 2.3 up)
* The Slide Down View hides/reveals content as the handle is dragged up and down
* The Slide Down View animates to an open or closed position on TOUCH_UP depending on the *last drag direction*
* Content in the content container does not resize (just gets hidden/revealed)
