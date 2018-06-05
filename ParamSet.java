import java.util.List;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Toolkit;

// Class to manage a set of parameters.

public class ParamSet {

	private String key;
	private List<Param> paramSet;

	// Constructor.

	public ParamSet(String paramSetKey) {
		paramSet = new ArrayList<Param>();
		key = paramSetKey;
	}

	// Return the size of the parameter set.

	public int getSize() {
		return paramSet.size();
	}

	// Return the key (name) of the parameter set.

	public String getKey() {
		return key;
	}

	// Add a parameter to the parameter set.

	public void addParam(String name, double value) {
		Param p = new Param(name, value);
		paramSet.add(p);
	}

	// Get the name of a parameter for a specified index.

	public String getName(int index) {
		if (index >= 0 && index < paramSet.size()) {
			return paramSet.get(index).name;
		} else {
			return "### ERROR in ParamSet.getName: Invalid index = " + index;
		}
	}

	// Get the value of a parameter for a specified index.

	public double getValue(int index) {
		if (index >= 0 && index < paramSet.size()) {
			return paramSet.get(index).value;
		} else {
			return Float.NaN;
		}
	}

	// Get the value of a parameter for a specified parameter name.

	public double getValueByName(String name) {
		double value = Float.NaN;
		boolean found = false;
		for (int i = 0; i < paramSet.size(); i++) {
			String paramName = paramSet.get(i).name;
			if (name.equals(paramName)) {
				value = paramSet.get(i).value;
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println();
			System.out.println("### ERROR in ParamSet.getValueByName:");
			System.out.println("### The requested parameter does not exist:");
			System.out.println("Parameter set: " + key);
			System.out.println("Parameter: " + name);
			System.out.println();
			System.out.println("### This is most likely a programming error.");
			System.exit(0);
		}
		return value;
	}

	// Allow the user to change the parameters in a parameter set
	// using a JOptionPane dialog.

	public int changeParams() {

		// This function returns 1 if the parameters were changed,
		// and 0 if they were not changed.

		int changed = 0;

		String errorText = "";
		String caption = "Please enter new values:";
		Object[] itemList;
		boolean done;

		// Load the list of labels and JTextField values for each
		// parameter into the array of objects.

		itemList = loadObjects(caption, errorText);

		// Use a dialog box to set the new values.

		done = false;
		while (!done) {
			done = true;
			int result = JOptionPane.showConfirmDialog(null, itemList, "Parameter Entry", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				changed = 1;
				for (int i = 0; i < paramSet.size(); i++) {
					try {
						JTextField f = (JTextField) itemList[4 + 2 * i];
						paramSet.get(i).value=Double.parseDouble(f.getText());
					} catch (Exception e) {
						Toolkit.getDefaultToolkit().beep();
						errorText = "Invalid entry: please retry.";
						itemList = loadObjects(caption, errorText);
						done = false;
					}
				}
			}
		}
		return changed;
	}

	// Return an Object array with a list of labels and text entry boxes for
	// each parameter in the parameter set, to be used in a JOptionPane dialog.

	private Object[] loadObjects(String caption, String errorText) {
		int nparams = paramSet.size();
		Object[] itemList = new Object[3 + 2 * nparams];
		JLabel lab;

		int j = 0;
		lab = new JLabel(errorText);
		lab.setForeground(Color.red);
		itemList[j] = lab;
		j++;
		itemList[j] = caption;
		j++;
		itemList[j] = " ";
		j++;
		for (int i = 0; i < nparams; i++) {
			itemList[j] = "Value of " + paramSet.get(i).name + ":";
			j++;
			itemList[j] = new JTextField(Double.toString(paramSet.get(i).value), 20);
			j++;
		}
		return itemList;
	}

	// Print out the values of the parameters in a parameter set.

	public void printParams(String label) {
		System.out.println("");
		System.out.println(label);
		for (int i = 0; i < paramSet.size(); i++) {
			System.out.println(paramSet.get(i).name + ": " + paramSet.get(i).value);
		}
	}

}
