

function Stack(){
	var top = null;
	var count = 0;
	
	this.push = function(data){

		var node = {
			data: data,
			next: null
		}
		node.next = top

		top = node;
		console.log("Top is now:" + node.data)
		count++;
		console.log("Count is now:" + count);
	}

	this.pop = function(){
		if(top === null){
			return null;
		} else {
		var out = top
		top = top.next;
		count--;
		return out.data}
	}

	this.peek = function(){
		console.log("executing peak with "+ top.data);
		if(top === null){
			return null} 
			else {console.log("Entered else loop with "+  top.data);
				return top.data;}
	}

	this.print = function(){
		var array = new Array();
		var temp = top;
		console.log("count to be used in loop: " + count);
		for (var i = count - 1; i >= 0; i--) {
			console.log("i is now: " + i);
			array[i] = temp.data;
			temp = temp.next;
			
		}
		console.log(array.join(' '));
			return array;
	}

	this.length = function(){
		return count;

	}
}




const stack = new Stack();
stack.push(1);
stack.push(2);
stack.push(3);
stack.print(); // => 1 2 3
stack.peek();
stack.length();

