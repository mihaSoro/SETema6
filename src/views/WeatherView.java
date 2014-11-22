package views;

import interfaces.IController;
import interfaces.IModelListener;
import interfaces.IView;
import model.WeatherModel;
import utils.RefreshAction;

import javax.swing.*;
import java.awt.*;

/**
 * The WeatherView implements the MVCs view class
 */
public class WeatherView extends JFrame implements IView, IModelListener {

    private static final long serialVersionUID = -5758555454500685115L;

    //the view components
    private JTextField mTemperatureTf = new JTextField(10);
    private JTextField mPressureTf = new JTextField(10);
    private JTextField mHumidityTf = new JTextField(10);

    private JButton mRefreshBtn = new JButton("Refresh");

    private IController mController;
    private WeatherModel mModel;

    /**
     * The WeatherView constructor initializes the view components and it layouts them
     */
    public WeatherView(){

        mTemperatureTf.setEditable(false);
        mHumidityTf.setEditable(false);
        mPressureTf.setEditable(false);

        // Layout the components.
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        content.add(new JLabel("Temperature:"));
        content.add(mTemperatureTf);
        content.add(new JLabel("Pressure:"));
        content.add(mPressureTf);
        content.add(new JLabel("Humidity:"));
        content.add(mHumidityTf);
        content.add(mRefreshBtn);

        // Finalize layout
        this.setContentPane(content);
        this.pack();

        this.setTitle("Weather Info");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * This method adds a model reference to the view
     * @param model The WeatherModel
     */

    public void addModel(WeatherModel model) {
        mModel = model;
        mTemperatureTf.setText(model.getTemperature());
        mPressureTf.setText(model.getPressure());
        mHumidityTf.setText(model.getHumidity());
    }

    /**
     * This methods sets the event listener for this view- the controller
     * @param controller The WeatherController
     */

    public void addController(IController controller) {
        if (mRefreshBtn.getAction() == null)
            mRefreshBtn.setAction(new RefreshAction());
        mRefreshBtn.setText("Refresh");
        mRefreshBtn.setActionCommand(IController.ACTION_REFRESH);
        mRefreshBtn.addActionListener(controller);

    }

    /**
     * updates the view when changes to the model have been made
     */

    @Override
    public void onUpdate() {

        mTemperatureTf.setText(mModel.getTemperature());
        mPressureTf.setText(mModel.getPressure());
        mHumidityTf.setText(mModel.getHumidity());

    }

    /**
     * This function displays and Error or a Message
     * @param errorStatus if true the message displayed comes from an error
     * @param message The message to be displayed
     */

    @Override
    public void onMessage(boolean errorStatus, String message) {

        if (errorStatus) {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "Weather MVC", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
