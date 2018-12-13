/**
 * 
When we have a mismatch at j+1, can we use the knowledge that T[i….j] matches perfectly with P[i….j] and T[j+1] =/ P[j+1],  to determine which index to start matching with next? 
Let’s check if we can rule out some indexes.  For example, could we say that we don’t need to check T[i+1]? 
By saying we are not checking T[i+1], we miss out on the possible pattern match if T[i+1….j] perfectly matches P[i….j-1]. But for that to be true, P[i+1…j] should match perfectly P[i….j-1].  Very unlikely, but still possible. Similarly, by not checking T[i+2], we miss out on a pattern if T[i+2….j] perfectly matches P[i…j-2], or if P[i+2…j] equals P[i…j-2]. And so on. For example, say P[i…j] = abcabcabc.  We know that abcabcab is not equal to bcabcabc, so we can skip 1 step. Infact, we know that abcabca is not equal to cabcab, so we can skip 2. But abcabc is equal to abcabc, so we can’t skip 3.

In our original text, we had a perfect match with abcabcabc. Define the overlap of two strings x and y to be the longest word that's a suffix of x and a prefix of y. Here x is the text, and y is the pattern.  We need only skip to the start of the overlap (suffix of the original text), because any pattern before the start of the suffix cannot possibly give us the pattern, because if it did, that would be a longer overlap: then our overlap would not be the longest

 and ab is not equal to bc, but abc is equal to abc, so we can afford to skip step 3 steps

Hence,  insofar we are sure that P[i…j-k] is not equal to  P[i+k..j] , we can skip k steps. 


Why the second condition? That is a further optimization.   Because we haven’t used our knowledge that T[j+1] =/ P[j+1] to rule out a few indexes. 
Lets say we found the k such that P[i….j-k] is equal to P[i+k…j]. If P[j-k +1] = P[j+1], then this can’t be a pattern, because else we would have another mismatch. 
T[j-k……j] would match with P[i….j-k], but at index j+1 T[j+1] =/ P[j-k+1]

Say our pattern is  [abcabcabc]a and we are searching in the text [abcabcabc]d. Here after we get a mismatch, we can do better than just skipping 3 steps.

By skipping three steps, we try to match:
T: [abcabc]d….
P: [abcabc]abca

But we already know that d does not match with a. So, infuse, we can skip 9 steps! 

Run with this Example: abaababaabaab
Overlap: ab
Overlap2:  abaab
 

http://qr.ae/TUGYzq
https://www.ics.uci.edu/~eppstein/161/960227.html

 */