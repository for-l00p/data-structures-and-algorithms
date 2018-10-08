module.exports = { Position, ListNode, SingleLinkedList} 




//Method 2
function Position(element){
	this._element = element
}

Position.prototype.getElement = function(){
		return this._element
}	

function ListNode(element, next){
	this.next = next,
	Position.call(this, element)
}

ListNode.prototype = Object.create(Position.prototype)








function SingleLinkedList(){
	this.head =  null;  
	this.length = 0;

}



SingleLinkedList.prototype = {

	addToHead : function(element){
		var newHead = new ListNode(element, this.head);
		this.head = newHead;
		this.length++
	},


	addToTail : function(element){

  		var newNode = new ListNode(element, null);
		if (this.head === null){
			//console.log("Head not found, adding to head")
			this.head = newNode;
			this.length++;
		return;
		}
		var currentNode = this.head;
		while(!(currentNode.next === null)){
		 currentNode = currentNode.next
		}
		currentNode.next = newNode;
		this.length++
		
	},

	removeNode : function(element){

		var previousNode = null;
		var currentNode = this.head;
		if (currentNode === null){
			console.log("List Empty");
			return 
		}

		if (currentNode.getElement() === element){
			console.log("Found element at head")
			this.head = currentNode.next;
			this.length--;
			return
		}
	
		while(currentNode != null && currentNode.getElement() != element){
				console.log("Shifting to next:" + JSON.stringify(currentNode.getElement()));
		 		previousNode = currentNode;
		 		currentNode = currentNode.next
		}
		if (currentNode === null){console.log("node not found");
			return} 
		previousNode.next = currentNode.next;
		this.length--;
	},

	isEmpty: function(){
		return (this.length ===0)
	},

	removeNodeAtPosition : function(position){

		var previousNode = null;
		var currentNode = this.head;
		var i = 0;
		while(i != position) {
			previousNode = currentNode;
			currentNode = currentNode.next;
			i++;
		}
		if (previousNode ===null){this.head = currentNode.next; this.length--}
			else {previousNode.next = currentNode.next; this.length--}
	},

	getAll : function(){
		var array = new Array();
		var temp = this.head;
		for (var i = 0; i < this.length ; i++) {
			
			array[i] = temp.getElement();
			temp = temp.next;
		}
		//console.log(array.join(' '));
		return array;
	},

	getNode : function(position){
		if (position >= this.length) {
				throw new Error("Position out of bounds")
			}
		var nodeToCheck = this.head;
		//console.log("Checking node: " + nodeToCheck.getElement())
		var i = 0;
		while(i != position) {
			nodeToCheck = nodeToCheck.next;
			//console.log("New nodeToCheck: " + nodeToCheck.getElement())
			i++;
		}
		return nodeToCheck;
	},

	findIndex: function(node){
		var nodeToCheck = this.head;
		var index = 0;
		while(nodeToCheck != node && index < this.length){
			nodeToCheck = nodeToCheck.next;
			index++
		}
		if (index >= this.length){
			console.log("Node not found!")
		} else {
			return index
		}
	},

	findNode: function(element){
		var nodeToCheck = this.head;
		while(nodeToCheck.getElement() != element){
			nodeToCheck = nodeToCheck.next
		}

		if (nodeToCheck == null){
			console.log("Node not found")
		} else {
			return nodeToCheck
		}

	},

	[Symbol.iterator]: function(){
		var currentNode = this.head	
		return {
			 next: function(){
			 	if (currentNode === null){
			 		return {value: undefined, done: true}
			 	}
				var value = currentNode.getElement()
				currentNode = currentNode.next
				return {
					value: value,
					done: false
				}
			}
		
		}
	}

}


