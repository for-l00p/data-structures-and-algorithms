const BST = require('./bst.js')


function bstSort (A){

	let bsTree = new BST(A[0])
	console.log(bsTree)

	for(let i = 1;  i < A.length; i++){
		bsTree.insert(A[i])
		//console.log("After insertion" + i + ":" + bsTree)
	}

	 bsTree.traverseDF(function(node){
  	console.log(node.getElement())
  }, 'inOrder')
}

