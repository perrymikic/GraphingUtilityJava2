// Graphing utility built with the JFreeChart library.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

public class Grid extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;

	// Types of functions available.
	private static final String FN_QUADRATIC = "Quadratic";
	private static final String FN_LOG10 = "Log (base 10)";
	private static final String FN_LN = "Natural Log";
	private static final String FN_CIRCLE = "Circle";
	private static final String FN_CUBIC = "Cubic";
	private static final String FN_EXPONENTIAL = "Exponential";
	private static final String FN_SQRT = "Square Root";
	private static final String FN_ELLIPSE = "Ellipse";
	private static final String FN_ABSVALUE = "Absolute Value";
	private static final String FN_LINE = "Line";
	

	// Control button names.
	private static final String BTN_ZOOM_IN = "+";
	private static final String BTN_ZOOM_OUT = "-";
	private static final String BTN_MOVE_LEFT = "<";
	private static final String BTN_MOVE_RIGHT = ">";
	private static final String BTN_MOVE_DOWN = "v";
	private static final String BTN_MOVE_UP = "^";
	private static final String BTN_RESET = "Reset Zoom";
	private static final String BTN_CHANGE_PARAMS = "Change Parameters";

	// Aspect ratio.
	private static final String TRUE_ASPECT_RATIO = "True Aspect Ratio";
	private static final String FREE_ASPECT_RATIO = "Free Aspect Ratio";

	// Zoom type names.
	private static final String ZOOM_TYPE_BOTH = "Zoom: both";
	private static final String ZOOM_TYPE_X = "Zoom: x";
	private static final String ZOOM_TYPE_Y = "Zoom: y";

	// Number of points to use for the curve.
	private static final int POINTS = 501;

	
	// Accessor and mutator methods so these variables are able to be used in graphFunction class
	public static int getPoints() {
		return POINTS;
	}

	public static String getFunctionDescription() {
		return functionDescription;
	}

	public static void setFunctionDescription(String functionDescription) {
		Grid.functionDescription = functionDescription;
	}

	public static String getFnQuadratic() {
		return FN_QUADRATIC;
	}
	public static String getFnExponential() {
		return FN_EXPONENTIAL;
	}

	public static ParameterCollection getParamCollection() {
		return paramCollection;
	}

	public static void setParamCollection(ParameterCollection paramCollection) {
		Grid.paramCollection = paramCollection;
	}

	public static boolean isDebug() {
		return debug;
	}

	public static void setDebug(boolean debug) {
		Grid.debug = debug;
	}

	public static String getFnLog10() {
		return FN_LOG10;
	}

	public static String getFnLn() {
		return FN_LN;
	}

	public static String getFnCircle() {
		return FN_CIRCLE;
	}

	public static boolean isFixAspectRatio() {
		return fixAspectRatio;
	}

	public static void setFixAspectRatio(boolean fixAspectRatio) {
		Grid.fixAspectRatio = fixAspectRatio;
	}

	public static String getFnEllipse() {
		return FN_ELLIPSE;
	}

	public static String getFnAbsvalue() {
		return FN_ABSVALUE;
	}

	public static String getFnSqrt() {
		return FN_SQRT;
	}

	public static String getFnCubic() {
		return FN_CUBIC;
	}

	public static String getFnLine() {
		return FN_LINE;
	}
	
	
	// Current plot area.
	private static double plt_xmin, plt_xmax;
	private static double plt_ymin, plt_ymax;

	// Aspect ratio of plot area.
	private static double aspectRatio;
	private static double screenAspectRatio;
	private static boolean fixAspectRatio = false;
	private static double fixedAspectRatio = 1.0;

	// Zoom factor (larger values zoom more).
	private static double zoomFac = 1.1;

	// Current plot type selected.
	private static String plotType = getFnQuadratic();

	// Main plot, frame, chart, and data set.
	private static Grid gUtil;
	private static JFrame frame;
	private static JFreeChart chart;
	private static ChartPanel cp;
	private static DefaultXYDataset ds;
	private static XYPlot plot;
	private static String previousLegend = null;
	private static String legend;
	private static String functionDescription;
	private static String plotTitle;

	// Current zoom options.
	private static boolean zoom_in_x = true;
	private static boolean zoom_in_y = true;

	// The parameter set collection.
	private static ParameterCollection paramCollection = new ParameterCollection();

	// Debugging flag.
	private static boolean debug = false;

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gUtil = new Grid();
				frame = new JFrame("Function Graphing Utility");
				ds = new DefaultXYDataset();

				// Load the data set and create a chart.
				loadDataset();
				plotTitle = "Plot of function: " + plotType + " [" + functionDescription + "]";
				chart = ChartFactory.createXYLineChart(plotTitle, "x", "y", ds, PlotOrientation.VERTICAL, true, true, false);

				// Create a chart panel.
				cp = new ChartPanel(chart);

				// Get the plot associated with the chart.
				plot = (XYPlot) chart.getPlot();

				// Set up the frame.
				frame.setSize(1200, 800);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// Add the chart to the frame.
				frame.getContentPane().add(cp);

				// Make a panel that contains the control buttons.
				gUtil.setControls();

				// Make the frame visible.
				frame.setVisible(true);

				// Customize the plot.
				customizePlot();

				// Set the current plot limits.
				getPlotLimits();
				setPlotLimits();

			}
		});

	}

	// Define the panel that controls the list of available functions,
	// and the zoom and pan of the plot.

	private void setControls() {

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel container = new JPanel();
		JButton button;

		// Combo box to select the plot type.
		String[] curveNames = { FN_QUADRATIC, FN_CIRCLE, FN_LOG10, FN_LN, FN_LINE, FN_ELLIPSE, FN_ABSVALUE, FN_SQRT, FN_EXPONENTIAL };
		JComboBox<String> curveList = new JComboBox<String>(curveNames);
		curveList.setSelectedIndex(0);
		panel1.add(curveList);

		// Add a listener to process the plot type selection.
		curveList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedPlotType = (String) combo.getSelectedItem();
				if (!selectedPlotType.equals(plotType)) {
					plotType = selectedPlotType;
					switchPlot();
				}
			}
		});

		// Button to change the parameters.
		button = new JButton(BTN_CHANGE_PARAMS);
		button.setActionCommand(BTN_CHANGE_PARAMS);
		button.addActionListener(this);
		panel1.add(button);

		// Button to set a fixed aspect ratio.
		button = new JButton(TRUE_ASPECT_RATIO);
		button.setActionCommand(TRUE_ASPECT_RATIO);
		button.addActionListener(this);
		panel1.add(button);

		// Button to set a free aspect ratio.
		button = new JButton(FREE_ASPECT_RATIO);
		button.setActionCommand(FREE_ASPECT_RATIO);
		button.addActionListener(this);
		panel1.add(button);

		// Combo box to select the zoom type.
		String[] zoomNames = { ZOOM_TYPE_BOTH, ZOOM_TYPE_X, ZOOM_TYPE_Y };
		JComboBox<String> zoomList = new JComboBox<String>(zoomNames);
		zoomList.setSelectedIndex(0);
		panel2.add(zoomList);

		// Add a listener to process the zoom type selection.
		zoomList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String zoomType = (String) combo.getSelectedItem();
				setZoomType(zoomType);
			}
		});

		// Zoom/pan buttons.
		String[] buttonNames = { BTN_ZOOM_IN, BTN_ZOOM_OUT, BTN_MOVE_LEFT, BTN_MOVE_RIGHT, BTN_MOVE_DOWN, BTN_MOVE_UP,
				BTN_RESET };
		for (int i = 0; i < buttonNames.length; i++) {
			button = new JButton(buttonNames[i]);
			button.setActionCommand(buttonNames[i]);
			button.addActionListener(this);
			panel2.add(button);
		}

		// Put the controls in two rows, one above the other.

		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		container.add(panel1);
		container.add(panel2);

		// Add the panels to the bottom of the frame.

		frame.add(container, BorderLayout.SOUTH);

		// Add a listener to the frame to process a window resize event.
		// This is needed to control the aspect ratio.
		// Use a timer to prevent continuous redraws.

		ActionListener updateFrame = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				getPlotLimits();
				setPlotLimits();
			}
		};

		// Timer delay in milliseconds.
		int delay = 300;
		Timer timer = new Timer(delay, updateFrame);
		timer.setRepeats(false);

		cp.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent event) {
				timer.stop();
				timer.start();
			}
		});

	}

	// Customize the look of the plot.

	public static void customizePlot() {
		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
		// Set the line color.
		renderer.setSeriesPaint(0, Color.blue);
		// Set the line thickness.
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		plot.setRenderer(renderer);
		// Prevent the y axis from always including a zero value.
		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setAutoRangeIncludesZero(false);
		cp.restoreAutoBounds();
	}

	// Process a control event.

	public void actionPerformed(ActionEvent e) {

		// Get the current plot limits.

		getPlotLimits();

		double xc = .5 * (plt_xmin + plt_xmax);
		double yc = .5 * (plt_ymin + plt_ymax);
		double dx = plt_xmax - plt_xmin;
		double dy = plt_ymax - plt_ymin;

		// Perform the action for the selected button.

		String buttonSelected = e.getActionCommand();

		switch (buttonSelected) {
		case TRUE_ASPECT_RATIO:
			setFixAspectRatio(true);
			setPlotLimits();
			break;
		case FREE_ASPECT_RATIO:
			setFixAspectRatio(false);
			cp.restoreAutoBounds();
			getPlotLimits();
			setPlotLimits();
			break;
		case BTN_ZOOM_IN:
			if (zoom_in_x) {
				plt_xmin = xc - .5 * dx / zoomFac;
				plt_xmax = xc + .5 * dx / zoomFac;
			}
			if (zoom_in_y) {
				plt_ymin = yc - .5 * dy / zoomFac;
				plt_ymax = yc + .5 * dy / zoomFac;
			}
			setPlotLimits();
			break;
		case BTN_ZOOM_OUT:
			if (zoom_in_x) {
				plt_xmin = xc - .5 * dx * zoomFac;
				plt_xmax = xc + .5 * dx * zoomFac;
			}
			if (zoom_in_y) {
				plt_ymin = yc - .5 * dy * zoomFac;
				plt_ymax = yc + .5 * dy * zoomFac;
			}
			setPlotLimits();
			break;
		case BTN_MOVE_RIGHT:
			plt_xmin = plt_xmin - dx * (zoomFac - 1);
			plt_xmax = plt_xmax - dx * (zoomFac - 1);
			setPlotLimits();
			break;
		case BTN_MOVE_LEFT:
			plt_xmin = plt_xmin + dx * (zoomFac - 1);
			plt_xmax = plt_xmax + dx * (zoomFac - 1);
			setPlotLimits();
			break;
		case BTN_MOVE_UP:
			plt_ymin = plt_ymin - dy * (zoomFac - 1);
			plt_ymax = plt_ymax - dy * (zoomFac - 1);
			setPlotLimits();
			break;
		case BTN_MOVE_DOWN:
			plt_ymin = plt_ymin + dy * (zoomFac - 1);
			plt_ymax = plt_ymax + dy * (zoomFac - 1);
			setPlotLimits();
			break;
		case BTN_RESET:
			cp.restoreAutoBounds();
			getPlotLimits();
			setPlotLimits();
			break;
		case BTN_CHANGE_PARAMS:
			// Use a dialog to change the parameters for the current function.
			int index = getParamCollection().getIndex(plotType);
			if (index != ParameterCollection.NONEXISTENT) {
				ParamSet p = getParamCollection().getParamSet(index);
				if (p.changeParams() != 0) {
					switchPlot();
				}
			}
			break;
		}
	}

	// Get the current x and y limits of the plotting area.

	public static void getPlotLimits() {
		plt_xmin = plot.getDomainAxis().getLowerBound();
		plt_xmax = plot.getDomainAxis().getUpperBound();
		plt_ymin = plot.getRangeAxis().getLowerBound();
		plt_ymax = plot.getRangeAxis().getUpperBound();
	}

	// Set the x and y limits of the plotting area.

	public static void setPlotLimits() {
		if (isFixAspectRatio())
			enforceAspectRatio();
		plot.getDomainAxis().setLowerBound(plt_xmin);
		plot.getDomainAxis().setUpperBound(plt_xmax);
		plot.getRangeAxis().setLowerBound(plt_ymin);
		plot.getRangeAxis().setUpperBound(plt_ymax);
	}

	// Get the aspect ratio of the screen.

	public static void getScreenAspectRatio() {
		Rectangle2D rect = cp.getScreenDataArea();
		screenAspectRatio = rect.getHeight() / rect.getWidth();
	}

	// Enforce a fixed aspect ratio.

	public static void enforceAspectRatio() {
		getScreenAspectRatio();
		double dx = plt_xmax - plt_xmin;
		double dy = plt_ymax - plt_ymin;
		aspectRatio = dy / dx;
		double overallAspectRatio = aspectRatio / screenAspectRatio;
		if (overallAspectRatio > fixedAspectRatio) {
			dx = dx * overallAspectRatio / fixedAspectRatio;
			double xc = .5 * (plt_xmin + plt_xmax);
			plt_xmin = xc - .5 * dx;
			plt_xmax = xc + .5 * dx;
		} else if (overallAspectRatio < fixedAspectRatio) {
			dy = dy * fixedAspectRatio / overallAspectRatio;
			double yc = .5 * (plt_ymin + plt_ymax);
			plt_ymin = yc - .5 * dy;
			plt_ymax = yc + .5 * dy;
		}
	}

	// Switch to a new plot type.

	public void switchPlot() {
		loadDataset();
		cp.restoreAutoBounds();
		getPlotLimits();
		setPlotLimits();
		plotTitle = "Plot of function: " + plotType + " [" + functionDescription + "]";
		chart.setTitle(plotTitle);
	}

	// Set the zoom type.

	public void setZoomType(String zoomType) {
		if (zoomType.equals(ZOOM_TYPE_BOTH)) {
			zoom_in_x = true;
			zoom_in_y = true;
		} else if (zoomType.equals(ZOOM_TYPE_X)) {
			zoom_in_x = true;
			zoom_in_y = false;
			setFixAspectRatio(false);
		} else if (zoomType.equals(ZOOM_TYPE_Y)) {
			zoom_in_x = false;
			zoom_in_y = true;
			setFixAspectRatio(false);
		}
	}

	// Load the data values into the data series for the current function.

	private static void loadDataset() {

		double[][] data = null;

		// Select the function based on the requested plot type.

		switch (plotType) {
		case FN_QUADRATIC:
			data = graphFunction.fn_quadratic();
			legend = "Quadratic";
			break;
		case FN_LOG10:
			data = graphFunction.fn_log10();
			legend = "log10(x)";
			break;
		case FN_LN:
			data = graphFunction.fn_ln();
			legend = "ln(x)";
			break;
		case FN_CIRCLE:
			data = graphFunction.fn_circle();
			legend = "Circle";
			break;
	   case FN_ELLIPSE:
			data = graphFunction.fn_ellipse();
			legend = "Ellipse";
			break;  
		case FN_CUBIC:
			data = graphFunction.fn_cubic();
			legend = "Cubic";
			break;
		case FN_EXPONENTIAL:
			data = graphFunction.fn_exponential();
			legend = "Exponential";
			break;
		case FN_SQRT:
			data = graphFunction.fn_sqrt();
			legend = "Square Root";
			break;
		case FN_ABSVALUE:
			data = graphFunction.fn_absvalue();
			legend = "Absolute Value";
			break;
		case FN_LINE:
			data = graphFunction.fn_line();
			legend = "Line";
			break;
			
		default:
			System.out.println();
			System.out.println("### ERROR in loadDataset:");
			System.out.println("### Invalid plot type requested.");
			System.out.println("Plot type: " + plotType);
			System.exit(0);
		}

		// Add the parameters to the legend.

		int index = getParamCollection().getIndex(plotType);

		if (index != ParameterCollection.NONEXISTENT) {
			ParamSet p = getParamCollection().getParamSet(index);
			int nparam = p.getSize();
			for (int i = 0; i < nparam; i++) {
				String paramName = p.getName(i);
				double paramValue = p.getValue(i);
				if (i == 0)
					legend = legend + ":";
				legend = legend + " " + paramName + " = " + paramValue;
				if (i < nparam - 1)
					legend = legend + ",";
			}
		}

		// Add the data series to the dataset (removing the previous data series
		// first).

		if (previousLegend != null) {
			ds.removeSeries(previousLegend);
		}
		ds.addSeries(legend, data);

		// Save the legend for the current data series, since this is used
		// as a series key to possibly remove it in the future.

		previousLegend = legend;

	}


}

