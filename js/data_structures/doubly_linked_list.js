function DoublyLinkedList(){
	this.head = null;  
	this.tail = null;

}


DoublyLinkedList.prototype.createNode = function(value, previous, next){
		return {value: value,
				previous: previous,
				next: next}
} //Alternative: create a "construction function": which does not return an object but just initializes it. When used with new keyword this will return an object. Constructor functions were introduced to be able to use classical inheritance. But because we have prototypical inheritance, we dont need them. 
// Argument against constructors: 
// https://medium.com/javascript-scene/the-two-pillars-of-javascript-ee6f3281e7f3
//https://medium.com/javascript-scene/3-different-kinds-of-prototypal-inheritance-es6-edition-32d777fa16c9//
// https://medium.com/javascript-scene/javascript-factory-functions-vs-constructor-functions-vs-classes-2f22ceddf33e
// To understand: http://tobyho.com/2010/11/22/javascript-constructors-and/



DoublyLinkedList.prototype.addToHead = function(value){

	var newHead = this.createNode(value, null, this.head);
	if (this.head){this.head.previous = newHead;} else {this.tail = newHead};
	this.head = newHead;
}


DoublyLinkedList.prototype.addToTail = function(value){
	var newTail = this.createNode(value, this.tail, null);
	if (this.tail){this.tail.next = newTail;} else (this.head = newTail);
	this.tail = newTail;
}


DoublyLinkedList.prototype.getNodeAt = function(position){
	var count = 0;
	var outputNode = this.head;
	while (!(count === position)){
	    outputNode = outputNode.next;
	    count++;
	}
	return outputNode;

};

DoublyLinkedList.prototype.delete = function(position){

};

DoublyLinkedList.prototype.search = function(value){

};



list = new DoublyLinkedList();
list.addToHead(10);
list.addToHead(9);
list.addToTail(8);
list.addToTail(5);
console.log(list.getNodeAt(1));
console.log(list);
console.log(list.search(9));
console.log(list.search(4));