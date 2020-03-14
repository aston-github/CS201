import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	/**
	 * Construct a BaseMarkov object with
	 * the specified order
	 * @param order size of this markov generator
	 */
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String,ArrayList<String>>();
	}

	/**
	 * Default constructor has order 3
	 */
	public EfficientMarkov() {
		this(3);
	}

	@Override
	public void setTraining(String text) {
		super.setTraining(text);
		myMap.clear();
		for (int i=0; i<myText.length()-myOrder+1; i++){
			String key = myText.substring(i, i+myOrder);
			myMap.putIfAbsent(key, new ArrayList<String>());
			if (i==myText.length()-myOrder){
				myMap.get(key).add(PSEUDO_EOS);
			}
			else{
				myMap.get(key).add(myText.substring(i+myOrder, i+myOrder+1));
			}

		}
	}

	@Override
	public ArrayList<String> getFollows(String key){
		ArrayList<String> follows = new ArrayList<String>();
		if (!myMap.containsKey(key)){
			throw new NoSuchElementException(key+" not in map");
		}
		else{
			follows = myMap.get(key);
		}

		return follows;
	}

}

