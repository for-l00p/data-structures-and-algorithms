/**
 * 

Why use getters and setters?

https://stackoverflow.com/questions/1568091/why-use-getters-and-setters-accessors
https://stackoverflow.com/questions/4709175/what-are-enums-and-why-are-they-useful


- Encapsulation of behavior associated with getting or setting the property - this allows additional functionality (like validation) to be added more easily later. Hides the underlying variable. This allows you to add verification logic when attempting to set a value - for example, if you had a field for a birth date, you might only want to allow setting that field to some time in the past. This cannot be enforced if the field is publicly accessible and modifyable - you need the getters and setters.

Even if you don't need any verification yet, you might need it in the future. Writing the getters and setters now means the interface is kept consistent, so existing code won't break when you change it.

- Hiding the internal representation of the property while exposing a property using an alternative representation.

- Insulating your public interface from change - allowing the public interface to remain constant while the implementation changes without affecting existing consumers.

- Getters and setters can allow different access levels - for example the get may be public, but the set could be protected.

- Providing a debugging interception point for when a property changes at runtime - debugging when and where a property changed to a particular value can be quite difficult without this in some languages.

Don't understand:

Controlling the lifetime and memory management (disposal) semantics of the property - particularly important in non-managed memory environments (like C++ or Objective-C).

Improved interoperability with libraries that are designed to operate against property getter/setters - Mocking, Serialization, and WPF come to mind.

Allowing the getter/setter to be passed around as lambda expressions rather than values.

Allowing inheritors to change the semantics of how the property behaves and is exposed by overriding the getter/setter methods.




-------

Tell, don't ask. objects should be instructed (by other objects) to perform a specific task. Procedural code gets information then makes decisions. Object-oriented code tells objects to do things. you should endeavor to tell objects what you want them to do; do not ask them questions about their state, make a decision, and then tell them what to do. The problem is that, as the caller, you should not be making decisions based on the state of the called object that result in you then changing the state of the object. The logic you are implementing is probably the called object’s responsibility, not yours. For you to make decisions outside the object violates its encapsulation.  it’s very easy to get lulled into examining some referenced object and then calling different methods based on the results. But that may not be the best way to go about doing it. Tell the object what you want. Let it figure out how to do it. Think declaratively instead of procedurally!
It is easier to stay out of this trap if you start by designing classes based on their responsibilities, you can then progress naturally to specifying commands that the class may execute, as opposed to queries that inform you as to the state of the object.

Person bob = new Person();
bob.setHairColour( Colour.RED );

Bob has complete control over what colour his hair will become because no other object in the system is allowed to change that colour without Bob's permission.


Instead of:
Person bob = new Person();
Colour hair = bob.getHairColour();
hair.setRed( 255 );

which is same as 

Person bob = new Person();
Colour hair = bob.hairColour;
hair.red = 255;

Both code snippets expose the idea that a Person is tightly coupled to Hair.  it becomes difficult to change how a Person's hair is stored.

 Bob has no control over what colour his hair would become. Great for a hair stylist with a penchant for redheads, not so great for Bob who despises that colour.

 Another way to avoid this problem is to return a copy of Bob's hair colour (as a new instance), which is no longer coupled to Bob. I find that to be an inelegant solution because it means there is behaviour that another class desires, using a Person's hair, that is no longer associated with the Person itself. That reduces the ability to reuse code, which leads to duplicated code.


https://en.wikipedia.org/wiki/Law_of_Demeter
https://en.wikipedia.org/wiki/Information_hiding
https://en.wikipedia.org/wiki/Principle_of_least_privilege
https://en.wikipedia.org/wiki/Single_responsibility_principle
----


I like to be able to say thing.doSomething(). It doesn't always make sense, but sometimes subject-verb syntax is more natural than a function call.

 Polymorphic dispatch (is that the term?) to replace if blocks. Instead of `if (thing is Car) thing.drive() else if (thing is Boat) thing.swim()` its just thing.move(). 
 */