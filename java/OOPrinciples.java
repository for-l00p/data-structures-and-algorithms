/**

Law of Demeter: An object should be as much unaware of other objects as possibble, so that it is only loosely coupled with any other object.
Information Hiding: Details of implementation should not be visible to anyone outside the object.
Principle of Least Privilege
Single Responsbility Principle




Polymorphism:  Polymorphic dispatch replaces if blocks. Instead of `if (thing is Car) thing.drive() else if (thing is Boat) thing.swim()` its just thing.move(). 


I like to be able to say thing.doSomething(). It doesn't always make sense, but sometimes subject-verb syntax is more natural than a function call.



----------

https://stackoverflow.com/questions/1568091/why-use-getters-and-setters-accessors


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

Tell, don't ask (helps to preserve encapsulation). objects should be instructed (by other objects) to perform a specific task. Procedural code gets information then makes decisions. Object-oriented code tells objects to do things. you should endeavor to tell objects what you want them to do; do not ask them questions about their state, make a decision, and then tell them what to do. The problem is that, as the caller, you should not be making decisions based on the state of the called object that result in you then changing the state of the object. The logic you are implementing is probably the called object’s responsibility, not yours. For you to make decisions outside the object violates its encapsulation.  it’s very easy to get lulled into examining some referenced object and then calling different methods based on the results. But that may not be the best way to go about doing it. Tell the object what you want. Let it figure out how to do it. Think declaratively instead of procedurally!
It is easier to stay out of this trap if you start by designing classes based on their responsibilities, you can then progress naturally to specifying commands that the class may execute, as opposed to queries that inform you as to the state of the object.

https://pragprog.com/articles/tell-dont-ask

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

 nother way to avoid this problem is to return a copy of Bob's hair colour (as a new instance), which is no longer coupled to Bob. I find that to be an inelegant solution because it means there is behaviour that another class desires, using a Person's hair, that is no longer associated with the Person itself. That reduces the ability to reuse code, which leads to duplicated code.


----

Design and document for inheritence or forbid it.



Methods of a class can be:
- private. Since method overriding works on dynamic binding, its not possible to override private method in Java. private methods are not even visible to Child class, they are only visible and accessible in the class on which they are declared. 
- abstract (so that only concrete implementations specify them at the time of object creation). Other methods defined in the class, thus do not depend on its implementation.
- non-static final (so that they cannot be overriden).
- static (Parent class methods that are static are not part of a child class (although they are accessible)m so there is no question of overriding it.  Even if you add another static method in a subclass, identical to the one in its parent class, this subclass static method is unique and distinct from the static method in its parent class.
- empty implementation
- non-static, non-final, non-abstract, non-empty implementation. These methods are overridable.

Problem:
Say a class has a method someMethod() which invokes a non-static, non-final-non-abstract method someOverridableMethod(). Let say a subClass overrides this method. Now this breaks the someMethod() implementation, without the subClass knowing about it. (The interaction of inherited classes with their parents can be surprising and unpredicatable if the ancestor wasn't designed to be inherited from.)


Solution: 

Classes should therefore come in two kinds :
Classes designed to be extended, and with enough documentation to describe how it should be done
Classes marked final


Make all classes final by default (forbid inheritence by default). If inheritance is to be allowed, then design for it and document it. : If you feel that you must allow inheritance from such a class, one reasonable approach is to ensure that the class never invokes any of its overridable methods and to document this fact. In other words, eliminate the class’s self-use of overridable methods entirely. In doing so, you’ll create a class that is reasonably safe to subclass. Overriding a method will never affect the behavior of any other method.

https://stackoverflow.com/questions/218744/good-reasons-to-prohibit-inheritance-in-java



One reason to make a class final would be if you wanted to force composition over inheritance. 

 */