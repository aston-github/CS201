import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
    private Map<WordGram,ArrayList<String>> myMap;
    /**
     * Construct an EfficientWordMarkov object with
     * the specified order
     * @param order size of this markov generator
     */
    public EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<WordGram,ArrayList<String>>();
    }

    /**
     * Default constructor has order 3
     */
    public EfficientWordMarkov() {
        this(3);
    }

    @Override
    public void setTraining(String text) {
        myWords = text.split("\\s+");
        myMap.clear();
        for (int i=0; i<myWords.length-myOrder+1; i++){
            WordGram key = new WordGram(myWords, i, myOrder);
            myMap.putIfAbsent(key, new ArrayList<String>());
            if (i==myWords.length-myOrder){
                myMap.get(key).add(PSEUDO_EOS);
            }
            else {
                myMap.get(key).add(myWords[i + myOrder]);
            }
            }
    }

    @Override
    public ArrayList<String> getFollows(WordGram key){
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

