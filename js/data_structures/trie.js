module.exports = Trie;
//var LinkedListLibrary = require('./SingleLinkedList.js'); // This could instead be implemented an array, or a hashmap



function TrieNode(character, isWord, next, firstChild ){
	this.element = character,
	this.isWord = isWord
	this.next = next,
	this.firstChild = firstChild

}


// const trieNodeListFactory = (c, endFlag) => Object.assign({}, {element: c, endFlag: endFlag, children: new LinkedListLibrary.SingleLinkedList()})

// const trieNodeArrayFactory = (c, endFlag) => Object.assign(Object.create(Position), {element: c, endFlag: endFlag, children: []})

// const trieNodeHashMapFactory = (c, endFlag) => Object.assign({}, {character: c, endFlag: endFlag, children: new LinkedListLibrary.SingleLinkedList()})




function Trie(childrenContainer){

	switch(childrenContainer){
		case "linkedlist":
			this._root = new TrieNode('', false)
			
	}

	this._childrenContainer = childrenContainer
}


Trie.prototype = {

	insert: function(key){

		switch(this._childrenContainer){
			case "linkedlist":
				current_node = this._root;
				console.log("Inserting word: " + key)
				for (let c of key){
					//console.log("Inserting: " + c)
					nodeToCheck = current_node.firstChild
					//console.log("Checking: " + JSON.stringify(nodeToCheck))
					
					// Insert c in the right place if not present. 
					if(nodeToCheck == null || nodeToCheck.element > c ){
						newNode = new TrieNode(c, false)
						newNode.next = nodeToCheck
						current_node.firstChild = newNode
						nodeToCheck = newNode
					}

					while(nodeToCheck.element < c){
						let nextNode = nodeToCheck.next
						if (nextNode == null || nextNode.element > c){
							newNode = new TrieNode(c, false)
							newNode.next = nextNode
							nodeToCheck.next = newNode
							nodeToCheck = newNode
						}  else {nodeToCheck = nextNode}
					}

					
					if (nodeToCheck.element == c){
						//console.log("Found character. Now moving to children " + c)
						current_node = nodeToCheck
					}  


				}	
				current_node.isWord = true
		}
		

	},


	search: function(key){
		switch(this._childrenContainer){
			case "linkedlist":
				current_node = this._root;
				for (let c of key){
					nodeToCheck = current_node.firstChild

					while(nodeToCheck != null && nodeToCheck.element != c){
						nodeToCheck = nodeToCheck.next
					}

					if (nodeToCheck == null){
						console.log("Key not present: " + key)
						return
					} else if  (nodeToCheck.element == c){
						current_node = nodeToCheck
					} 

				}
				if (current_node.isWord){
					console.log("Key found: " + key)
				} else {
					console.log(key + ": Prefix present but no end of word " )
				}
				break;


		}
	
	},

    delete: function(key){
    	console.log("Starting delete: " + key)

    	function recurse(root, keyInput){

    		let allowedToDelete = false

    		if (root == null){
    			allowedToDelete = false	
    			console.log(key +  ": The word to be deleted seems to be not present")
    			return allowedToDelete
    		}
    		//console.log("Called delete on " + JSON.stringify(root) + " with string: " + keyInput)
    		
    		if (keyInput == ''){

    			root.isWord = false
    			//console.log("is not now a word:" + JSON.stringify(root))
    			//If at the end of the word (base case), there are still children, then the word is a prefix to another word and is to be left alone. Otherwise, if there are no children, then it can be deleted. 
    			if (root.firstChild){
    				allowedToDelete = false
    				console.log(key + ": Pattern still present as prefix of another word.")
    			} else {
    				allowedToDelete = true
    			} 
    			return allowedToDelete
    		
    		}

    		let newRoot = root.firstChild
    		let previousNode = root
    		
    		
			while(newRoot != null && newRoot.element != keyInput[0]){
			
				previousNode = newRoot
				newRoot = newRoot.next
			}

    		// At this point, we know that currentNode is not null. 	
    		let nextString = keyInput.substr(1);
    		if (recurse(newRoot, nextString) == true){
   		
    			if (newRoot.isWord || (newRoot.firstChild && newRoot.firstChild.next)){
    				 allowedToDelete = false	
    			} else {
    				if (previousNode.firstChild == newRoot){
    					previousNode.firstChild = newRoot.next
    					allowedToDelete = true
    				} else {
    					previousNode.next = newRoot.next
    					allowedToDelete = true
					  }
    			  }
    			return allowedToDelete
    		} else {
    			allowedToDelete = false
    			return allowedToDelete
    		  }
    	}

    	return recurse(this._root, key)

    } 



};







var testTrie = new Trie('linkedlist');
testTrie.insert('the')
testTrie.insert('a')
testTrie.insert('there')
testTrie.insert('answer')
testTrie.insert('any')
testTrie.insert('by')
testTrie.insert('bye')
testTrie.insert('their')
//console.log(JSON.stringify(testTrie))
testTrie.search('the')
testTrie.search('there')
testTrie.search('their')
testTrie.delete('th')
testTrie.delete('theri')
testTrie.search('ther')
testTrie.delete('the')
testTrie.search('the')
testTrie.delete('there')
testTrie.search('there')
testTrie.insert('there')
testTrie.search('there')
testTrie.insert('the')
testTrie.delete('a')
testTrie.search('a')





//console.log(testTrie)
