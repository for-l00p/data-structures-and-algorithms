
// element is private variable and cannot be changed from the outside.
// Position factory returns an object literal with a getElement method, with the actual element hidden in the scope. This is a great use of scoping, as every execution of position factory creates a new scope with a separate element.  

module.exports = {positionFactory, listNodeFactory}

var positionFactory = function(element){
	var _element = element // saving it if we want to modify or do other things to it. 
	return {
		getElement: function(){
			return _element
		}
	}
}

//Con: While you've achieved private data with this pattern, it comes with an enormous memory cost.  Every time you call your factory function for a new instance, it actually duplicates all those public functions as well, giving fresh copies to each new object.  Not a big deal in your small example, but in larger factories called many times to produce many objects, that cost will be gargantuan.  Public functions should only be created once and then shared between instances (like they are in stampit's methods({..}) object). There is no way to achieve private data without either paying that enormous memory cost, or using ugly hacks like WeakMap that don't suffer memory issues but do have big run-time costs for the hash lookup each time.  If you care about memory use and efficiency, private data is currently impossible in JavaScript.﻿ Even if you don't mind all that wasted memory, it should still make you feel dirty that you have 1,000 copies of the same function in memory when 1 copy is all you need.﻿  If you are creating 60000 items per second or something like that, then it makes sense to worry about those things (but you should probably worry about the fact that you are creating so many objects in the first place, not the individual performance and memory consumption per object)
//Con2: Less readable code to someone who doesnt understand Object.create or Object. assign or prototypes. 

//Pro:  More readable code as No need to take care of binding 'this'. https://www.youtube.com/watch?v=ImwrezYhw4w&t=287s. efficiency is not a principle or religion, it is a tool that is a double-edged sword. You can almost always squeeze more performance or memory out of your code by sacrificing convenience, but if you don't actually NEED that efficiency you have just needlessly complicated your code.﻿ Would Be Graveyards this argument is made over and over in the comments, and my response is the same every time: it's completely inconsequential if you are just creating a few thousand objects per second. That should NOT make you "feel dirty" - my phone can literally create millions of objects per second and it barelymake a dent in the memory graphs. Computers are (pretty) powerful, and numbers that are huge for computers are not huge for humans. A couple of thousands of objects is almost nothing. 
// Pro2: Private variables achieved. 
// Pro3: Working with objects only, so closer to JS's true nature. No syntactic sugar of constructors.




//listNodes produced such have a getElement method in their prototype.

var listNodeFactory = function(element, next){
	return Object.assign(positionFactory(element),{next: next}) 
}	



