
/**
 * Suppose we have a large body of text and we would like a data structure that allows us to query if particular strings occur in the text. Given a string t of length n, we can insert each of the n suxes of t into a Patricia tree. We call the resulting tree the sux tree for t. Now, if we want to know if some string s occurs in t we need only do a prex search for s in the Patricia tree. Thus, we can test if s occurs in t in O(|s|) time. In the same amount of time, we can locate some occurrence of s in t and in O(|s| + k) time we can locate all occurrences of s in t, where k is the number of occurrences.

A suffix tree T for S is a rooted directed Tree whose edges are labelled by non-empty substrings of S.

Each leaf corresponds to a suffix of S in the sense that the concatenation of the edge labels on the unique path traced from the leaf to the root spells out an edge label. 

Space complexity: O(n + |P|), where n is the text length (This is because n = number of suffix words, and the space complexity of a compressed trie is O(w).)

Time complexity: 
Preprocessing: O(n)
Pattern matching: O(|P| + k) - where k is the number of occurance of the pattern (k leaves so O(k) internal nodes in the subtree  rooted where the pattern ends)

Not all strings are guaranteed to have corresponding suffix trees (if a suffix ends at an internal node.) To fix this, we add a termination character to ensure each leaf corresponds to a suffix. 


 */

public class SuffixTree{

	private CompressedTrie suffixTree;
	

	// Constructor: construct a compressed Trie of suffixes from the given text
	public SuffixTree(Char[] text){



	}

	public void search(Char pat[]){


	}

	// Unit test. 
	public static void main(String[] args){
		Char[] text = {'m','i','n','i','m','i','z','e'};
		Char[] pattern = {'n','i','m'};
		SuffixTree finder = new SuffixTree(text);
		finder.search(pattern);
	}


}