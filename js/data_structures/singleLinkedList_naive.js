module.exports = SingleLinkedList;

function SingleLinkedList(){
	this.head =  null;  
	this.length = 0;

}


SingleLinkedList.prototype = {

	createNode: function(element, next){	
		return {position: new Position(element),
				next: next}
	}, 
	//Alternative: create a "construction function": which does not return an object but just initializes it. When used with new keyword this will return an object. Constructor functions were introduced to be able to use classical inheritance. But because we have prototypical inheritance, we dont need them. 
// Argument against constructors: 
// https://medium.com/javascript-scene/the-two-pillars-of-javascript-ee6f3281e7f3
//https://medium.com/javascript-scene/3-different-kinds-of-prototypal-inheritance-es6-edition-32d777fa16c9//
// https://medium.com/javascript-scene/javascript-factory-functions-vs-constructor-functions-vs-classes-2f22ceddf33e
// To understand: http://tobyho.com/2010/11/22/javascript-constructors-and/



	addToHead : function(element){

	var newHead = this.createNode(element, this.head);
	this.head = newHead;
	console.log("Head is now: " + JSON.stringify(newHead));
	this.length++
	},


	addToTail : function(element){

  		var newNode = this.createNode(element, null);
		if (this.head === null){
		console.log("Head not found, adding to head")
		this.head = newNode;
		this.length++;
		return;
		}
		var current_node = this.head;
		while(!(current_node.next === null)){
		 current_node = current_node.next
		}
		current_node.next = newNode;
		this.length++
	},

	removeNode : function(element){

		var previous_node = null;
		var current_node = this.head;
		if (current_node === null){
			console.log("List Empty");
		return 
		}

		if (current_node.element === element){
			console.log("Found element at head")
			this.head = current_node.next;
			this.length--;
			return
		}
	
		while(current_node != null && current_node.element != element){
				console.log("Shifting to previous:" + JSON.stringify(current_node));
		 		previous_node = current_node;
		 		current_node = current_node.next
		}
		if (current_node === null){console.log("node not found");
			return} 
		previous_node.next = current_node.next;
		this.length--;
	},


	removeNodeAtPosition : function(position){

		var previous_node = null;
		var current_node = this.head;
		var i = 0;
		while(i != position) {
		previous_node = current_node;
		current_node = current_node.next;
		i++;
		}
		if (previous_node ===null){this.head = current_node.next; this.length--}
			else {previous_node.next = current_node.next; this.length--}
	},

	getAll : function(){
		var array = new Array();
		var temp = this.head;
		//console.log(JSON.stringify(temp));
		for (var i = 0; i < this.length ; i++) {
			array[i] = temp.element;
			temp = temp.next;
		}
		console.log(array.join(' '));
		return array;
	},

	getNode : function(position){
		if (position > this.length) {
				console.log("Position out of bounds")
			}
		var nodeToCheck = this.head;
		console.log("Checking node: " + nodeToCheck)
		var i = 0;
		while(i != position) {
			temp = temp.next;
			i++;
		}
		return nodeToCheck;
	}


}
// list = new SingleLinkedList();
// list.addToHead(10);
// list.addToHead(9);
// list.addToHead(8);
// list.addToTail("ee");
// list.removeNodeAtPosition(2);
// console.log(list);
// list.getAll();
// console.log(list.getNode(0));
