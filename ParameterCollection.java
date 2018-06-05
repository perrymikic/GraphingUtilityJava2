import java.util.ArrayList;
import java.util.List;

// Class to hold a collection of parameter sets.

public class ParameterCollection {

	private List<ParamSet> paramSetCollection;
	public static final int NONEXISTENT = -1;

	// Constructor.

	public ParameterCollection() {
		paramSetCollection = new ArrayList<ParamSet>();
	}

	// Add a parameter set.

	public void addParamSet(ParamSet p) {
		paramSetCollection.add(p);
	}

	// Return the size of the collection.

	public int getSize() {
		return paramSetCollection.size();
	}

	// Return the parameter set with a specified index.

	public ParamSet getParamSet(int index) {
		if (index >= 0 && index < paramSetCollection.size()) {
			return paramSetCollection.get(index);
		} else {
			return null;
		}
	}

	// Return the index of a parameter set with a specified key (name).

	public int getIndex(String key) {
		int j = NONEXISTENT;
		for (int i = 0; i < paramSetCollection.size(); i++) {
			String keyItem = paramSetCollection.get(i).getKey();
			if (keyItem.equals(key)) {
				j = i;
				break;
			}
		}
		return j;
	}

}
