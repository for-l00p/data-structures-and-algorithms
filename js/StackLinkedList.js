
module.exports = Stack


const LinkedListLibrary = require('./SingleLinkedList.js');



function Stack(){
	this.list = new LinkedListLibrary.SingleLinkedList();
}

Stack.prototype = {

	push: function(data){
		//console.log("Pushing data " + console.dir(data))
		this.list.addToHead(data);
	},

	pop: function(){

	 var top = this.list.getNode(0).getElement();
	 this.list.removeNodeAtPosition(0);
	 return top
	},

	peek: function(){
		//console.log("Peeking" );
		return this.list.getNode(0).getElement();
	},

	length: function(){
		return this.list.length;
	},

	isEmpty:function(){
		return this.list.isEmpty()
	},

  	print: function(){
		return this.list.getAll();
	
	}
}


// const stack = new Stack();
// stack.push(1);
// stack.push(10);
// stack.push('x');
// stack.print(); // => 1 2 3
// stack.peek();
// stack.length();