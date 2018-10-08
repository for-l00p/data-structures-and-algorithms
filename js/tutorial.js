function Animal () { this.x = 0; this.y = 0;}

Animal.prototype.locate = function() { 
  console.log(this.x, this.y);
  return this;
};
Animal.prototype.move = function(x, y) {
  this.x = this.x + x;
  this.y = this.y + y; 
  return this;
}


function Duck () {
    Animal.call(this);
}

Duck.prototype = new Animal();
Duck.prototype.constructor = Duck;
Duck.prototype.speak = function () {
    console.log("quack");
    return this;
}

var daffy = new Duck();

daffy.move(6, 7).locate().speak();
I've read this post by Eric Elliott and if I understand correctly I can use Object.create and Object.assign instead? Is it really that simple?

var animal = {
   x : 0,
   y : 0,
   locate : function () { 
     console.log(this.x, this.y);
     return this;
   },
   move : function (x, y) { 
     this.x = this.x + x; 
     this.y = this.y + y;
     return this;
   }
}

var duck = function () {
   return Object.assign(Object.create(animal), {
     speak : function () { 
       console.log("quack");
       return this;
     }
   });
}

var daffy = duck();

daffy.move(6, 7).locate().speak();