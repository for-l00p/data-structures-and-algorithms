 //Method 1. This keeps name as private but creates a seaprate prototype for each employee.

 var PrivatePerson = function(privateKey){
 	var _privateKey = privateKey;
    var hash = key => 12345*key
 	return {
 		showPublicKey: function(){
			console.log("My public key is " + hash(_privateKey))
		}
	}

 }
		
var PublicPerson = function(privateKey, designation){
	return Object.assign(Object.create(PrivatePerson(privateKey)),{designation: designation}) 
}	

var me = new PublicPerson(123, 'CEO')


-----


function PrivatePerson(privateKey){
	var hash = key => 12345*key 
	this.publicKey = hash(privateKey);

}


PrivatePerson.prototype.showPublicKey = function(){
	console.log("My public key is " + this.publicKey)
}

var PublicPerson = function(privateKey, designation){
	return Object.assign(Object.create(PrivatePerson),{privateKey: privateKeydesignation: designation}) 
}


var me = new PublicPerson(123, 'CEO')


// console.log(me)
// console.dir(me)
// console.log(me.showPublicKey())
me.showPublicKey()
console.log(me)
//Method 2. This keeps 