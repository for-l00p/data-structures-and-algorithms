module.exports = Queue;


// As in real life, you add to tail of the queue and remove from the head. 

function Queue(){
	this._head = null;
	this._tail = null;
	this._length = 0; // If length is zero, queue is empty. Ensure that length = 0 iff head or tail are null, and it is never the case that only one of them is.  

}


Queue.prototype = {
	enqueue: function(element){

		var node = {
			element: element,
			previous: this._tail
		}
		//console.log(JSON.stringify(node.element) + " is being added to queue");
		if (this.isEmpty()){
			//console.log(JSON.stringify(node) + " is the new head");
			this._head = node;	
		} 
		//console.log(JSON.stringify(node) + " is the new tail");
		this._tail = node;
		//console.log("Increasing length by 1. Old length: " + this._length);
		this._length++; 

	},

    dequeue: function(){


		if (this.isEmpty()){
			console.log("Found nothing to dequeue; returning blank; ");
			return;
		} 
		var currentNode = this._tail;
		var nextNode = null;
		while(currentNode.previous){
			nextNode = currentNode;
			currentNode = currentNode.previous;
		}

		//console.log("Found head to dequeue: " +  JSON.stringify(currentNode));

		var nodeToDequeue = currentNode;
		if(nextNode){
			nextNode.previous = null;
			//console.log("Resetting head to: " + JSON.stringify(nextNode));
			this._head = nextNode;
		}
		
		//console.log("Decreasing length by 1. Old length: " + this._length);
		this._length--;

		if (this.isEmpty()){
			 console.log("Queue is now empty. Resetting head and tail to null ");
			this._tail = null;
			this._head = null

		}

		return nodeToDequeue.element;



	
	}, 

	isEmpty : function(){
		return (this._length == 0);
	},

	length: function(){
		return this._length
	}


}

const queue = new Queue();
queue.enqueue(3);
queue.enqueue(4);
queue.enqueue('hello');
console.log(queue.dequeue());
console.log(queue.dequeue());


