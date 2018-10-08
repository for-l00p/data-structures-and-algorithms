function kmpNaive (T, P){
	let startIndex = []
	for (i = 0; i < (T.length - P.length + 1); i++){
		let j = 0
		while(T[i+j] == P[j] && j < P.length){
			//console.log("Comparing: " + T[i+j] + " and " + P[j])
			j++
		}
		if(j == P.length){
			startIndex.push(i)
		}
	}
	console.log(startIndex)
	return startIndex	
}




function kmp(T,P){

	let startIndex = []
	let prefixArray = preProcess(P)
	//console.log(prefixArray)
	let matches = 0
	for (i = 0; i < T.length; i++){
		while (matches > 0 && T[i] != P[matches]){
			matches = prefixArray[matches-1]
		}

		if(T[i] == P[matches]){
			matches++
		}

		if(matches == P.length){
			startIndex.push(i + 1 - matches)
			matches = prefixArray[matches-1]
		}
	}
	console.log(startIndex)
}




function preProcess(P){
	let prefixArray = [0];
	for (var i = 1; i < P.length; i++){
		let matches = prefixArray[i-1]
			while(matches > 0 && P[i] != P[matches]){
				matches = prefixArray[matches-1]
			}
			if (P[i] == P[matches]){
				matches = matches + 1
			} 
			prefixArray[i] = matches-1		
		}
	
	console.log(prefixArray)
	return prefixArray
}


kmp('THIS IS A TEST TEXT', 'TEST')
kmp('AABAACAADAABAABA', 'AABA')
kmp('ABAABAABA', 'ABA')
preProcess('acacabacacabacacac', 'ca')

