/**

 https://stackoverflow.com/questions/1100819/how-do-you-design-object-oriented-projects

 The steps that I use for initial design (getting to a class diagram), are:

Requirements gathering. Talk to the client and factor out the use cases to define what functionality the software should have. Make absolutely sure you know what your program is all about before you start. What is your program? What will it not do? What problem is it trying to solve?


Compose a narrative of the individual use cases. Your first set of use cases shouldn't be a laundry list of everything the program will eventually do. Start with the smallest set of use cases you can come up with that still captures the essence of what your program is for. For this web site, for example, the core use cases might be log in, ask a question, answer a question, and view questions and answers. Nothing about reputation, voting, or the community wiki, just the raw essence of what you're shooting for.


Go through the narrative and highlight nouns (person, place, thing), as candidate classes and verbs (actions), as methods / behaviors. As you come up with potential classes, don't think of them only in terms of what noun they represent, but what responsibilities they have. I've found this to be the biggest aid in figuring out how classes relate to each other during program execution. It's easy to come up with relationships like "a dog is an animal" or "a puppy has one mother." It's usually harder to figure out relationships describing run-time interactions between objects. You're program's algorithms are at least as important as your objects, and they're much easier to design if you've spelled out what each class's job is.

Discard duplicate nouns and factor out common functionality.

Create a class diagram. 

Apply OOD principles to organize your classes (factor out common functionality, build hierarchies, etc.)


Once you've got that minimal set of use cases and objects, start coding. Get something that actually runs as soon as possible, even though it doesn't do much and probably looks like crap. It's a starting point, and will force you to answer questions you might gloss over on paper.

Now go back and pick more use cases, write up how they'll work, modify your class model, and write more code. Just like your first cut, take on as little at a time as you can while still adding something meaningful. Rinse and repeat.

The technique I've used in real projects with reasonable success is Responsibility Driven Design, inspired by Wirfs-Brock's book.

Start with the top level user stories, and with colleagues, at a whiteboard, sketch the high-level interactions they imply. This gets you the first idea of what the big modules are; and an iteration or two of high level CRC-card like play you should have stabilised a list of major components, what they do and how they interact.

Then, if any of the responsibilities are large or complex, refine those modules down until you have things that are small and simple enough to be objects, by playing out the interactions inside the module for each of the major operations identified by the higher level interactions.

Knowing when to stop is a matter of judgement (which only comes with experience).

We suggest driving a design toward completion with the aid of execution scenarios. We start with only one or two obvious cards and start playing "what-if". If the situation calls for a responsibility not already covered by one of the objects we either add the responsibility to one of the objects, or create a new object to address that responsibility. If one of the object becomes too cluttered during this process we copy the information on its card to a new card, searching for more concise and powerful ways of saying what the object does. If it is not possible to shrink the information further, but the object is still too complex, we create a new object to assume some of the responsibilities. We stress the importance of creating objects not to meet mythical future needs, but only under the demands of the moment. This ensures that a design contains only as much information as the designer has directly experienced, and avoids premature complexity. 



Law of Demeter: An object should be as much unaware of other objects as possibble, so that it is only loosely coupled with any other object.
Information Hiding: Details of implementation should not be visible to anyone outside the object.
Principle of Least Privilege
Single Responsbility Principle


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


SOLID principles:  


https://softwareengineering.stackexchange.com/questions/153410/what-are-the-design-principles-that-promote-testable-code-designing-testable-c
http://wiki.c2.com/?PrinciplesOfObjectOrientedDesign



OOD Tips:

 Not every noun is a class. All your classes which describe roles should probably simply be variables, not classes. Pet is such an example. Both, a dog and a cat can be a pet. In fact, one can have any animal as pet. Pet is much more a description of the relationship between the pet holder and the animal.
 
Use Delegation/Composition whenever it makes  sense to create classes for roles. You could have a class Pet. But in that case, Pet should not be a subclass of these other classes like class Dog. Instead you should use delegation / composition, like this:

class Pet {
    Animal animal;
}
Then the class Pet describes a role, and that role can be fulfilled by any type of animal.


Separation of Concerns
This is more of an esoteric critique, but generally speaking you'll want your interfaces to be well-segregated and more specific. The current Animals interface doesn't really communicate what the interface is used for. If I were making an application for example that only showed pictures of various animals, it is unclear whether or not I should use your interface on my new class. Rather, it is better to split interfaces (usually) based on the behavior they add. This forces them to be very targeted and succint, which in turn produces code that is more stable and typically easier to maintain since changes are very isolated.

For example, instead of:

interface Animals {

    void callSound();
    int run();

}
You might have:

interface IMakesSound {
    void callSound();
}

interface ICanMove {
    int move();
}

interface IWarmBlooded {
    int currentBodyTemperature();
    bool isOverheated();
}

abstract class Animal implements IMakesSound, ICanMove {
    abstract void callSound();
    abstract int move();
}

abstract class Mammal extends Animal implements IWarmBlooded {
    private int _bodyTemp;
    abstract void callSound(); // We don't know what ALL mammals sound like
    abstract int move(); // Some mammals run, others walk, some swim, and some do all of the above.
    public int currentBodyTemperature() {
        return _bodyTemp;
    }

    public bool isOverheated() {
        return _bodyTemp > 98; // This can be overridden based on the child class if needed
    }
}

// Other classes here that inherit either from `Animal` or `Mammal`.
In the above example, I have separated out your interface into two separate interfaces and an abstract base type that combines them. Using this structure, I can create something that makes a sound but isn't an animal (for example, a robot toy dog) that can still have all of the attributes of a "real" dog, but none of the inherited animal features. This allows your code to be more flexible and more loosely coupled (which is the point of using an interface). By having code dependent on these loosely typed constructs, it allows for more flexibility.

https://codereview.stackexchange.com/questions/144468/animals-inheritance-and-interfaces





-----


ENCAPSULATION vs INFORMATION HIDING

----------


POLYMORPHISM VS ABSTRACTION

Why is polymorphism useful?

polymorphism is the ability (in programming) to present the same interface for differing underlying forms (data types). Polymorphism describes a pattern in object oriented programming in which classes have different functionality while sharing a common interface.

Examples: A real world analogy for polymorphism is a button. Everyone knows how to use a button: you simply apply pressure to it. What a button “does,” however, depends on what it is connected to and the context in which it is used — but the result does not affect how it is used. If your boss tells you to press a button, you already have all the information needed to perform the task.

The classic example is the Shape class and all the classes that can inherit from it (square, circle, dodecahedron, irregular polygon, splat and so on). With polymorphism, each of these classes will have different underlying data. A point shape needs only two co-ordinates (assuming it's in a two-dimensional space of course). A circle needs a center and radius. A square or rectangle needs two co-ordinates for the top left and bottom right corners and (possibly) a rotation. An irregular polygon needs a series of lines.

Why is it useful? 
polymorphism is used to make applications more 

(1) Modular:  Instead of messy conditional statements describing different courses of action, you create interchangeable objects that you select based on your needs. when you have different types of objects and can write classes that can work with all those different types because they all adhere to the same API (e.g. List of objects sharing a base class/interface). By making the class responsible for its code as well as its data, you can achieve polymorphism. This is in contrast to the old way of doing things in which the code was separate from the data, shape.Draw() vs drawSquare() and drawCircle().


(2) extensible.  you can add new FlyingMachines to your application without changing any of the existing logic.

Polymorphism helps implement the Open Closed Principle. https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle#Polymorphic_open/closed_principle


https://stackoverflow.com/questions/1031273/what-is-polymorphism-what-is-it-for-and-how-is-it-used

------
INHERITANCE

What are the pitfalls of using inheritance? 

- Child extends Father extends Grandfather. Father implements foo. Grandfather class implements foo(), unaware of Father class’s foo() method. Now Child needs foo. Which foo will it get access to? https://en.wikipedia.org/wiki/Fragile_base_class
- In multiple inheritance, its not clear which method is inherited. Copier extends Scanner, Printer. Scanner,  Printer extends PoweredDevice. PoweredDevice halt method stops power.  Printer overwrites halt() method which stops printing. which halt() method does Copier have access to? )If your Copier paper jams, you might want to tell it to halt() to prevent damage. Fortunately, you don’t have to write that method because it’s already implemented in Printer. Unfortunately, because inheritance usually is implemented via depth-first search of the inheritance tree, you’ve called the PoweredDevice.halt() method, removing power to the Copier altogether, losing all of the other queued jobs.
These problems arise because of two conflicting tendencies of classes: classes tend to increase in size as they take on more responsibilities, but code reuse requires decrease in size.  Inheritance shouldn’t be used to share code. Although one of the advantages of inheritance is that you can put common code in the parent class, sharing code shouldn;t be the reason why you use inheritance. Inheritance should be used to used to model classes that share behavior, not merely code. 
And A pure behavior, sans state should not be a class in the first place. (Egglaying NOT OK. Mammal OK)

How should we code with these in mind? 
Programmers should "Design and document for inheritance or else prohibit it". 
How to document for inheritance?
If a method or class is not to be overridden or extended, it should be labelled as final. 

Programmers should favour composition over inheritance. Composition is a "has-a" relationship, while inheritence is an is-a relationship.

https://www.youtube.com/watch?v=wfMtDGfHWpA&t=2s 
https://stackoverflow.com/questions/8696695/composition-inheritance-and-aggregation-in-javascript
https://www.reddit.com/r/programming/comments/5dxq6i/composition_over_inheritance/da8bplv/     

What is multiple inheritance?

------

What is method overloading? What is method overriding?

Methods are overriden during compile time, but overloaded only on runtime. 

-----



https://www.programcreek.com/2013/11/arrays-sort-comparator/

- Design Patterns (Builder, Interpretor, Decorator, Chain of Responsibility, Facade, Adapter, Accumulator)
Delegation/forwarding Interpretor Patten

Chain of Responsibility

Sentinel Pattern

Accumulator: Used in Recursion. A mutable object is passed around in a recursive function.


Builder: Used when the number of arguments is variable. 

Decorator

Factory Method: Make new instances of a class without exposing the class name. 
Example: We avoid providing direct access to database connections (make its constructor private) because they're resource intensive. So we use a static factory method getDbConnection that creates a connection if we're below the limit.  Otherwise, it tries to provide a "spare" connection, failing with an exception if there are none.


Template



**/
 */