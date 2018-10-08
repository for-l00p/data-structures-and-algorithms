function Stack(){
	this.stack = [];
}

Stack.prototype.push = function(value){
	this.stack.push(value);
}

Stack.prototype.pop = function(){
	return this.stack.pop();
}
	
Stack.prototype.peek = function(){
	var index = this.stack.length - 1;
	return this.stack[index];
}

Stack.prototype.length = function(){
	return this.stack.length;
}

Stack.prototype.print = function(){
	console.log(this.stack.join(' '));
}
