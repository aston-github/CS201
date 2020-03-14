
/**
 * A WordGram represents a sequence of strings
 * just as a String represents a sequence of characters
 * 
 * @author Aston Yong
 *
 */
public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;// cached hash value
	private boolean stringMade = false;
	private boolean hashMade = false;

	/**
	 * Create WordGram by creating instance variable myWords and copying
	 * size strings from source starting at index start
	 * @param source is array of strings from which copying occurs
	 * @param start starting index in source for strings to be copied
	 * @param size the number of strings copied
	 */
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		myToString = null;
		myHash = 0;

		// TODO: initialize myWords
		for (int i=start; i<(start + size); i++){
			myWords[i-start] = source[i];
		}

	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * Complete this comment
	 * @return length of myWords
	 */
	public int length(){
		// TODO: change this
		return myWords.length;
	}


	/**
	 * Complete appropriate comment here
	 * @param o is comparing object
	 * @return boolean if objects are equal
	 */
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		// TODO: Complete this method
		else {
			WordGram wg = (WordGram) o;
			if (wg.length() != this.length()){
				return false;
			}
			for (int i = 0; i<(wg.length()); i++){
				if (! wg.wordAt(i).equals(this.wordAt(i))){
					return false;
				}
			}
			return true;
		}
	}

	/**
	 *
	 * @return myHash which is the hashCode to the string of this
	 */
	@Override
	public int hashCode(){
		// TODO: complete this method
		if (hashMade == false) {
			myHash = this.toString().hashCode();
			hashMade = true;
		}
		return myHash;
	}
	

	/**
	 * Create and complete this comment
	 * @param last is last String of returned WordGram
	 * @return new WordGram object wg with shifted myWords
	 */
	public WordGram shiftAdd(String last) {
		WordGram wg = new WordGram(myWords,0,myWords.length);
		// TODO: Complete this method
		String[] myWordsWg = new String[myWords.length];
		for (int i = 1; i<myWords.length; i++){
			myWordsWg[i-1] = myWords[i];
		}
		myWordsWg[myWords.length-1] = last;
		wg.myWords = myWordsWg;
		return wg;
	}

	/**
	 *
	 * @return a string made from myWords with spaces in between the words
	 */
	@Override
	public String toString(){
		// TODO: Complete this method
		if (stringMade == false) {
			myToString = String.join(" ", myWords);
			stringMade = true;
		}
		return myToString;
	}
}
