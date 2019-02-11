/**

https://stackoverflow.com/questions/1461598/what-is-the-point-of-setters-and-getters-in-java/1462424#1462424
https://stackoverflow.com/questions/1568091/why-use-getters-and-setters-accessors




-------

The technique I've used in real projects with reasonable success is Responsibility Driven Design, inspired by Wirfs-Brock's book.

Start with the top level user stories, and with colleagues, at a whiteboard, sketch the high-level interactions they imply. This gets you the first idea of what the big modules are; and an iteration or two of high level CRC-card like play you should have stabilised a list of major components, what they do and how they interact.

Then, if any of the responsibilities are large or complex, refine those modules down until you have things that are small and simple enough to be objects, by playing out the interactions inside the module for each of the major operations identified by the higher level interactions.

Knowing when to stop is a matter of judgement (which only comes with experience).

We suggest driving a design toward completion with the aid of execution scenarios. We start with only one or two obvious cards and start playing "what-if". If the situation calls for a responsibility not already covered by one of the objects we either add the responsibility to one of the objects, or create a new object to address that responsibility. If one of the object becomes too cluttered during this process we copy the information on its card to a new card, searching for more concise and powerful ways of saying what the object does. If it is not possible to shrink the information further, but the object is still too complex, we create a new object to assume some of the responsibilities. We stress the importance of creating objects not to meet mythical future needs, but only under the demands of the moment. This ensures that a design contains only as much information as the designer has directly experienced, and avoids premature complexity. 


Polymorphism:  Polymorphic dispatch replaces if blocks. Instead of `if (thing is Car) thing.drive() else if (thing is Boat) thing.swim()` its just thing.move(). 


I like to be able to say thing.doSomething(). It doesn't always make sense, but sometimes subject-verb syntax is more natural than a function call.



----------

https://stackoverflow.com/questions/1568091/why-use-getters-and-setters-accessors

- Encapsulation the state while exposing behavior associated with getting or setting the property.

-  this allows additional functionality (like validation) to be added more easily later.  This allows you to add verification logic when attempting to set a value - for example, if you had a field for a birth date, you might only want to allow setting that field to some time in the past. This cannot be enforced if the field is publicly accessible and modifyable - you need the getters and setters.  Even if you don't need any verification yet, you might need it in the future. Writing the getters and setters now means the interface is kept consistent, so existing code won't break when you change it.

- Hiding the internal representation of the property while exposing a property using an alternative representation. Hides the underlying variable.

- Insulating your public interface from change - allowing the public interface to remain constant while the implementation changes without affecting existing consumers.

- Getters and setters can allow different access levels - for example the get may be public, but the set could be protected.

- Providing a debugging interception point for when a property changes at runtime - debugging when and where a property changed to a particular value can be quite difficult without this in some languages.

Don't understand:

Controlling the lifetime and memory management (disposal) semantics of the property - particularly important in non-managed memory environments (like C++ or Objective-C).

Improved interoperability with libraries that are designed to operate against property getter/setters - Mocking, Serialization, and WPF come to mind.

Allowing the getter/setter to be passed around as lambda expressions rather than values.

Allowing inheritors to change the semantics of how the property behaves and is exposed by overriding the getter/setter methods.


----





----------

------


What is method overloading? What is method overriding?

Methods are overriden during compile time, but overloaded only on runtime. 





**/