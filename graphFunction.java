
public class graphFunction extends Grid {
	
	public static double[][] fn_quadratic() {

		int index;
		String myType;

		// Default parameters.

		double xmin = -10.;
		double xmax = 10.;
		double a = .1;
		double h = 2;
		double k = 1;

		setFunctionDescription("y = a(x-h)^2+k");

		myType = getFnQuadratic();

		// If the parameter set for this function has not been added to the
		// parameter collection yet (i.e., on the first time in), make a
		// new parameter set, load it with the default parameters for this
		// function, and add this set to the parameter collection.

		index = getParamCollection().getIndex(myType);

		if (index == ParameterCollection.NONEXISTENT) {
			ParamSet paramSet = new ParamSet(myType);
			paramSet.addParam("a", a);
			paramSet.addParam("h", h);
			paramSet.addParam("k", k);
			paramSet.addParam("xmin", xmin);
			paramSet.addParam("xmax", xmax);
			getParamCollection().addParamSet(paramSet);
		}

		// Set the values of the local parameters equal to the
		// values stored in the parameter set.

		index = getParamCollection().getIndex(myType);
		if (index != ParameterCollection.NONEXISTENT) {
			ParamSet p = getParamCollection().getParamSet(index);
			a = p.getValueByName("a");
			h = p.getValueByName("h");
			k = p.getValueByName("k");
			xmin = p.getValueByName("xmin");
			xmax = p.getValueByName("xmax");
		}

		if (isDebug()) {
			// Print out the current values of the parameters.
			index = getParamCollection().getIndex(myType);
			if (index != ParameterCollection.NONEXISTENT) {
				getParamCollection().getParamSet(index).printParams(myType + ":");
			}
		}

		// Arrays to hold the x and y values.
		double[] x = new double[getPoints()];
		double[] y = new double[getPoints()];

		double dx = (xmax - xmin) / (getPoints() - 1);

		for (int i = 0; i < getPoints(); i++) {
			x[i] = xmin + i * dx;
			y[i] = a * (x[i] - h) * (x[i] - h) + k;
		}

		// Combine the x and y arrays into a single 2D array.

		double[][] data = { x, y };

		return data;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static double[][] fn_line() { // Return a linear function

		int index;
		String myType;

		// Default parameters.

		double xmin = -10;
		double xmax = 10;
		double a = .1;
		double b = 2;


		setFunctionDescription("y = ax + b");

		myType = getFnLine();
		
		setFixAspectRatio(true);

		// If the parameter set for this function has not been added to the
		// parameter collection yet (i.e., on the first time in), make a
		// new parameter set, load it with the default parameters for this
		// function, and add this set to the parameter collection.

		index = getParamCollection().getIndex(myType);

		if (index == ParameterCollection.NONEXISTENT) {
			ParamSet paramSet = new ParamSet(myType);
			paramSet.addParam("a", a);
			paramSet.addParam("b", b);
			paramSet.addParam("xmin", xmin);
			paramSet.addParam("xmax", xmax);
			getParamCollection().addParamSet(paramSet);
		}

		// Set the values of the local parameters equal to the
		// values stored in the parameter set.

		index = getParamCollection().getIndex(myType);
		if (index != ParameterCollection.NONEXISTENT) {
			ParamSet p = getParamCollection().getParamSet(index);
			a = p.getValueByName("a");
			b = p.getValueByName("b");
			xmin = p.getValueByName("xmin");
			xmax = p.getValueByName("xmax");
		}

		if (isDebug()) {
			// Print out the current values of the parameters.
			index = getParamCollection().getIndex(myType);
			if (index != ParameterCollection.NONEXISTENT) {
				getParamCollection().getParamSet(index).printParams(myType + ":");
			}
		}

		// Arrays to hold the x and y values.
		double[] x = new double[getPoints()];
		double[] y = new double[getPoints()];

		double dx = (xmax - xmin) / (getPoints() - 1);

		for (int i = 0; i < getPoints(); i++) {
			x[i] = xmin + i * dx;
			y[i] = a * x[i] + b;
		}

		// Combine the x and y arrays into a single 2D array.

		double[][] data = { x, y };

		return data;
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static double[][] fn_absvalue() { // Return a absolute function

		int index;
		String myType;

		// Default parameters.

		double xmin = -10.;
		double xmax = 10.;
		double a = 2;
		double h = 2;
		double k = -5;

		setFunctionDescription("y = a|x-h| + k");

		myType = getFnAbsvalue();
		
		setFixAspectRatio(true);

		// If the parameter set for this function has not been added to the
		// parameter collection yet (i.e., on the first time in), make a
		// new parameter set, load it with the default parameters for this
		// function, and add this set to the parameter collection.

		index = getParamCollection().getIndex(myType);

		if (index == ParameterCollection.NONEXISTENT) {
			ParamSet paramSet = new ParamSet(myType);
			paramSet.addParam("a", a);
			paramSet.addParam("h", h);
			paramSet.addParam("k", k);
			paramSet.addParam("xmin", xmin);
			paramSet.addParam("xmax", xmax);
			getParamCollection().addParamSet(paramSet);
		}

		// Set the values of the local parameters equal to the
		// values stored in the parameter set.

		index = getParamCollection().getIndex(myType);
		if (index != ParameterCollection.NONEXISTENT) {
			ParamSet p = getParamCollection().getParamSet(index);
			a = p.getValueByName("a");
			h = p.getValueByName("h");
			k = p.getValueByName("k");
			xmin = p.getValueByName("xmin");
			xmax = p.getValueByName("xmax");
		}

		if (isDebug()) {
			// Print out the current values of the parameters.
			index = getParamCollection().getIndex(myType);
			if (index != ParameterCollection.NONEXISTENT) {
				getParamCollection().getParamSet(index).printParams(myType + ":");
			}
		}

		// Arrays to hold the x and y values.
		double[] x = new double[getPoints()];
		double[] y = new double[getPoints()];

		double dx = (xmax - xmin) / (getPoints() - 1);

		for (int i = 0; i < getPoints(); i++) {
			x[i] = xmin + i * dx;
			y[i] = a * (Math.abs(x[i]-h)+ k);
		}

		// Combine the x and y arrays into a single 2D array.

		double[][] data = { x, y };

		return data;
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static double[][] fn_sqrt() { // Return a square root function

		int index;
		String myType;

		// Default parameters.

		double xmin = -1;
		double xmax = 10.;
		double a = .1;
		double h = 0;
		double k = 0;

		setFunctionDescription("y = a sqrt(x-h) + k");

		myType = getFnSqrt();

		// If the parameter set for this function has not been added to the
		// parameter collection yet (i.e., on the first time in), make a
		// new parameter set, load it with the default parameters for this
		// function, and add this set to the parameter collection.

		index = getParamCollection().getIndex(myType);

		if (index == ParameterCollection.NONEXISTENT) {
			ParamSet paramSet = new ParamSet(myType);
			paramSet.addParam("a", a);
			paramSet.addParam("h", h);
			paramSet.addParam("k", k);
			paramSet.addParam("xmin", xmin);
			paramSet.addParam("xmax", xmax);
			getParamCollection().addParamSet(paramSet);
		}

		// Set the values of the local parameters equal to the
		// values stored in the parameter set.

		index = getParamCollection().getIndex(myType);
		if (index != ParameterCollection.NONEXISTENT) {
			ParamSet p = getParamCollection().getParamSet(index);
			a = p.getValueByName("a");
			h = p.getValueByName("h");
			k = p.getValueByName("k");
			xmin = p.getValueByName("xmin");
			xmax = p.getValueByName("xmax");
		}

		if (isDebug()) {
			// Print out the current values of the parameters.
			index = getParamCollection().getIndex(myType);
			if (index != ParameterCollection.NONEXISTENT) {
				getParamCollection().getParamSet(index).printParams(myType + ":");
			}
		}

		// Arrays to hold the x and y values.
		double[] x = new double[getPoints()];
		double[] y = new double[getPoints()];

		double dx = (xmax - xmin) / (getPoints() - 1);

		for (int i = 0; i < getPoints(); i++) {
			x[i] = xmin + i * dx;
			double xmh =x[i]-h;
			if (xmh < 0) {
				y[i] = Float.NaN;
			} else {
				y[i] = a * (Math.sqrt(xmh)) + k;
			}

		}

		// Combine the x and y arrays into a single 2D array.

		double[][] data = { x, y };

		return data;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// Return a cicle.

		public static double[][] fn_circle() {

			int index;
			String myType;

			// Default parameters.

			double r = 3.0;
			double x0 = 1.5;
			double y0 = 2.0;

			setFunctionDescription("(x-x0)^2 + (y-y0)^2 = r^2");

			myType = getFnCircle();

			setFixAspectRatio(true);

			// If the parameter set for this function has not been added to the
			// parameter collection yet (i.e., on the first time in), make a
			// new parameter set, load it with the default parameters for this
			// function, and add this set to the parameter collection.

			index = getParamCollection().getIndex(myType);

			if (index == ParameterCollection.NONEXISTENT) {
				ParamSet paramSet = new ParamSet(myType);
				paramSet.addParam("r", r);
				paramSet.addParam("x0", x0);
				paramSet.addParam("y0", y0);
				getParamCollection().addParamSet(paramSet);
			}

			// Set the values of the local parameters equal to the
			// values stored in the parameter set.

			index = getParamCollection().getIndex(myType);
			if (index != ParameterCollection.NONEXISTENT) {
				ParamSet p = getParamCollection().getParamSet(index);
				r = p.getValueByName("r");
				x0 = p.getValueByName("x0");
				y0 = p.getValueByName("y0");
			}

			if (isDebug()) {
				// Print out the current values of the parameters.
				index = getParamCollection().getIndex(myType);
				if (index != ParameterCollection.NONEXISTENT) {
					getParamCollection().getParamSet(index).printParams(myType + ":");
				}
			}

			// Arrays to hold the x and y values.
			double[] x = new double[getPoints()];
			double[] y = new double[getPoints()];

			double dt = 2 * Math.PI / (getPoints() - 1);

			for (int i = 0; i < getPoints(); i++) {
				double t = i * dt;
				x[i] = x0 + r * Math.cos(t);
				y[i] = y0 + r * Math.sin(t);
			}

			// Combine the x and y arrays into a single 2D array.

			double[][] data = { x, y };

			return data;

		}



	
	
	
	
	
	
	
	
	
	
	
	
	public static double[][] fn_cubic() { // Return a cubic function

		int index;
		String myType;

		// Default parameters.

		double xmin = -10.;
		double xmax = 10.;
		double a = .1;
		double b = 2;
		double c = 1;
		double d = .5;

		setFunctionDescription("y = ax^3 + bx^2 + cx + d");

		myType = getFnCubic();

		// If the parameter set for this function has not been added to the
		// parameter collection yet (i.e., on the first time in), make a
		// new parameter set, load it with the default parameters for this
		// function, and add this set to the parameter collection.

		index = getParamCollection().getIndex(myType);

		if (index == ParameterCollection.NONEXISTENT) {
			ParamSet paramSet = new ParamSet(myType);
			paramSet.addParam("a", a);
			paramSet.addParam("b", b);
			paramSet.addParam("c", c);
			paramSet.addParam("d", d);
			paramSet.addParam("xmin", xmin);
			paramSet.addParam("xmax", xmax);
			getParamCollection().addParamSet(paramSet);
		}

		// Set the values of the local parameters equal to the
		// values stored in the parameter set.

		index = getParamCollection().getIndex(myType);
		if (index != ParameterCollection.NONEXISTENT) {
			ParamSet p = getParamCollection().getParamSet(index);
			a = p.getValueByName("a");
			b = p.getValueByName("b");
			c = p.getValueByName("c");
			d = p.getValueByName("d");
			xmin = p.getValueByName("xmin");
			xmax = p.getValueByName("xmax");
		}

		if (isDebug()) {
			// Print out the current values of the parameters.
			index = getParamCollection().getIndex(myType);
			if (index != ParameterCollection.NONEXISTENT) {
				getParamCollection().getParamSet(index).printParams(myType + ":");
			}
		}

		// Arrays to hold the x and y values.
		double[] x = new double[getPoints()];
		double[] y = new double[getPoints()];

		double dx = (xmax - xmin) / (getPoints() - 1);

		for (int i = 0; i < getPoints(); i++) {
			x[i] = xmin + i * dx;
			y[i] = (a * (x[i]*x[i]*x[i])) + (b *(x[i] * x[i]) + (c*x[i]) + d);
		}

		// Combine the x and y arrays into a single 2D array.

		double[][] data = { x, y };

		return data;

	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	public static double[][] fn_exponential() { // Return an exponential function

		int index;
		String myType;

		// Default parameters.

		double xmin = 0.;
		double xmax = 4;
		double a = 2;

		setFunctionDescription("y = |a|^x");

		myType = getFnExponential();

		// If the parameter set for this function has not been added to the
		// parameter collection yet (i.e., on the first time in), make a
		// new parameter set, load it with the default parameters for this
		// function, and add this set to the parameter collection.

		index = getParamCollection().getIndex(myType);

		if (index == ParameterCollection.NONEXISTENT) {
			ParamSet paramSet = new ParamSet(myType);
			paramSet.addParam("a", a);
			paramSet.addParam("xmin", xmin);
			paramSet.addParam("xmax", xmax);
			getParamCollection().addParamSet(paramSet);
		}

		// Set the values of the local parameters equal to the
		// values stored in the parameter set.

		index = getParamCollection().getIndex(myType);
		if (index != ParameterCollection.NONEXISTENT) {
			ParamSet p = getParamCollection().getParamSet(index);
			a = p.getValueByName("a");
			xmin = p.getValueByName("xmin");
			xmax = p.getValueByName("xmax");
		}

		if (isDebug()) {
			// Print out the current values of the parameters.
			index = getParamCollection().getIndex(myType);
			if (index != ParameterCollection.NONEXISTENT) {
				getParamCollection().getParamSet(index).printParams(myType + ":");
			}
		}

		// Arrays to hold the x and y values.
		double[] x = new double[getPoints()];
		double[] y = new double[getPoints()];

		double dx = (xmax - xmin) / (getPoints() - 1);

		for (int i = 0; i < getPoints(); i++) {
			x[i] = xmin + i * dx;
			y[i] =  Math.pow(Math.abs(a),x[i]);
			
		}

		// Combine the x and y arrays into a single 2D array.

		double[][] data = { x, y };

		return data;
	}

	
	
	
	
	
	// Return an ellipse.

	public static double[][] fn_ellipse() {

		int index;
		String myType;

		// Default parameters.

		double a = 1.0;
		double b = 2.0;
		double x0 = -2.5;
		double y0 = 1.7;

		setFunctionDescription("[(x-x0)/a]^2 + [(y-y0)/b]^2 = 1");

		myType = getFnEllipse();

		setFixAspectRatio(true);

		// If the parameter set for this function has not been added to the
		// parameter collection yet (i.e., on the first time in), make a
		// new parameter set, load it with the default parameters for this
		// function, and add this set to the parameter collection.

		index = getParamCollection().getIndex(myType);

		if (index == ParameterCollection.NONEXISTENT) {
			ParamSet paramSet = new ParamSet(myType);
			paramSet.addParam("a", a);
			paramSet.addParam("b", b);
			paramSet.addParam("x0", x0);
			paramSet.addParam("y0", y0);
			getParamCollection().addParamSet(paramSet);
		}

		// Set the values of the local parameters equal to the
		// values stored in the parameter set.

		index = getParamCollection().getIndex(myType);
		if (index != ParameterCollection.NONEXISTENT) {
			ParamSet p = getParamCollection().getParamSet(index);
			a = p.getValueByName("a");
			b = p.getValueByName("b");
			x0 = p.getValueByName("x0");
			y0 = p.getValueByName("y0");
		}

		if (isDebug()) {
			// Print out the current values of the parameters.
			index = getParamCollection().getIndex(myType);
			if (index != ParameterCollection.NONEXISTENT) {
				getParamCollection().getParamSet(index).printParams(myType + ":");
			}
		}

		// Arrays to hold the x and y values.
		double[] x = new double[getPoints()];
		double[] y = new double[getPoints()];

		double dt = 2 * Math.PI / (getPoints() - 1);

		for (int i = 0; i < getPoints(); i++) {
			double t = i * dt;
			x[i] = x0 + a * Math.cos(t);
			y[i] = y0 + b * Math.sin(t);
		}

		// Combine the x and y arrays into a single 2D array.

		double[][] data = { x, y };

		return data;

	}



	
	
	
	
	
	
	

	public static double[][] fn_log10() {

		int index;
		String myType;

		// Default parameters.

		double xmin = 0.;
		double xmax = 10.;

		setFunctionDescription("y = log10(x)");

		myType = getFnLog10();

		// If the parameter set for this function has not been added to the
		// parameter collection yet (i.e., on the first time in), make a
		// new parameter set, load it with the default parameters for this
		// function, and add this set to the parameter collection.

		index = getParamCollection().getIndex(myType);

		if (index == ParameterCollection.NONEXISTENT) {
			ParamSet paramSet = new ParamSet(myType);
			paramSet.addParam("xmin", xmin);
			paramSet.addParam("xmax", xmax);
			getParamCollection().addParamSet(paramSet);
		}

		// Set the values of the local parameters equal to the
		// values stored in the parameter set.

		index = getParamCollection().getIndex(myType);
		if (index != ParameterCollection.NONEXISTENT) {
			ParamSet p = getParamCollection().getParamSet(index);
			xmin = p.getValueByName("xmin");
			xmax = p.getValueByName("xmax");
		}

		if (isDebug()) {
			// Print out the current values of the parameters.
			index = getParamCollection().getIndex(myType);
			if (index != ParameterCollection.NONEXISTENT) {
				getParamCollection().getParamSet(index).printParams(myType + ":");
			}
		}

		// Arrays to hold the x and y values.
		double[] x = new double[getPoints()];
		double[] y = new double[getPoints()];

		double dx = (xmax - xmin) / (getPoints() - 1);

		for (int i = 0; i < getPoints(); i++) {
			x[i] = xmin + i * dx;
			if (x[i] <= 0) {
				y[i] = Float.NaN;
			} else {
				y[i] = Math.log10(x[i]);
			}
		}

		// Combine the x and y arrays into a single 2D array.

		double[][] data = { x, y };

		return data;

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	// Return a natural log function.

		public static double[][] fn_ln() {

			int index;
			String myType;

			// Default parameters.

			double xmin = 0.;
			double xmax = 10.;

			setFunctionDescription("y = ln(x)");

			myType = getFnLn();

			// If the parameter set for this function has not been added to the
			// parameter collection yet (i.e., on the first time in), make a
			// new parameter set, load it with the default parameters for this
			// function, and add this set to the parameter collection.

			index = getParamCollection().getIndex(myType);

			if (index == ParameterCollection.NONEXISTENT) {
				ParamSet paramSet = new ParamSet(myType);
				paramSet.addParam("xmin", xmin);
				paramSet.addParam("xmax", xmax);
				getParamCollection().addParamSet(paramSet);
			}

			// Set the values of the local parameters equal to the
			// values stored in the parameter set.

			index = getParamCollection().getIndex(myType);
			if (index != ParameterCollection.NONEXISTENT) {
				ParamSet p = getParamCollection().getParamSet(index);
				xmin = p.getValueByName("xmin");
				xmax = p.getValueByName("xmax");
			}

			if (isDebug()) {
				// Print out the current values of the parameters.
				index = getParamCollection().getIndex(myType);
				if (index != ParameterCollection.NONEXISTENT) {
					getParamCollection().getParamSet(index).printParams(myType + ":");
				}
			}

			// Arrays to hold the x and y values.
			double[] x = new double[getPoints()];
			double[] y = new double[getPoints()];

			double dx = (xmax - xmin) / (getPoints() - 1);

			for (int i = 0; i < getPoints(); i++) {
				x[i] = xmin + i * dx;
				if (x[i] <= 0) {
					y[i] = Float.NaN;
				} else {
					y[i] = Math.log(x[i]);
				}
			}

			// Combine the x and y arrays into a single 2D array.

			double[][] data = { x, y };

			return data;

		}
}