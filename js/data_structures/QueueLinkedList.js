
module.exports = Queue;
const SingleLinkedList = require('./SingleLinkedList.js');

// As in real life, you add to tail of the queue and remove from the head. 

function Queue(){

	this.list = null;
	this._tail = null;
	this._length = 0; // If length is zero, queue is empty. Ensure that length = 0 iff head or tail are null, and it is never the case that only one of them is.  

}


Queue.prototype = {
	enqueue: function(element){

		var nodeToEnqueue = new SingleLinkedList.ListNode(element, null);
		// next is null. We need to add to tail of the queue, so we need to point the next of the tail to this. Easy Peasy. 
		if (this.isEmpty()){
			//console.log(JSON.stringify(node) + " is the new head");
			this._head = nodeToEnqueue;	
			this._tail = nodeToEnqueue;
			this._length++
			return
		} 
		this._tail.next = nodeToEnqueue;
		this._tail = nodeToEnqueue;
		this._length++
		
		

	},

    dequeue: function(){

		if (this.isEmpty()){
			console.log("Found nothing to dequeue; returning blank; ");
			return;
		} 
		
		var nodeToDequeue = this._head;
		this._head = this._head.next
		
		//console.log("Decreasing length by 1. Old length: " + this._length);
		this._length--;

		if (this.isEmpty()){
			 //console.log("Queue is now empty. Resetting head and tail to null ");
			this._tail = null;
			this._head = null

		}

		return nodeToDequeue.getElement();



	
	}, 

	isEmpty : function(){
		return (this._length == 0);
	},

	length: function(){
		return this._length
	}


}

// const queue = new Queue();
// queue.enqueue(3);
// queue.enqueue(4);
// queue.enqueue('hello');
// console.log(queue.dequeue());
// console.log(queue.dequeue());


