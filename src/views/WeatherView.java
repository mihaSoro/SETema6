package views;

import interfaces.IController;
import interfaces.IModelListener;
import interfaces.IView;
import model.WeatherModel;
import utils.RefreshAction;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hp on 11/20/2014.
 */
public class WeatherView extends JFrame implements IView, IModelListener {

    private static final long serialVersionUID = -5758555454500685115L;

    private JTextField mTemperatureTf = new JTextField(10);
    private JTextField mPressureTf = new JTextField(10);
    private JTextField mHumidityTf = new JTextField(10);

    private JButton mRefreshBtn = new JButton("Refresh");

    private IController mController;
    private WeatherModel mModel;

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


    public void addModel(WeatherModel model) {
        mModel = model;
        mTemperatureTf.setText(model.getTemperature());
        mPressureTf.setText(model.getPressure());
        mHumidityTf.setText(model.getHumidity());
    }

    public void addController(IController controller) {
        if (mRefreshBtn.getAction() == null)
            mRefreshBtn.setAction(new RefreshAction());
        mRefreshBtn.setText("Refresh");
        mRefreshBtn.setActionCommand(IController.ACTION_REFRESH);
        mRefreshBtn.addActionListener(controller);

    
    }


    @Override
    public void onUpdate() {

        mTemperatureTf.setText(mModel.getTemperature());
        mPressureTf.setText(mModel.getPressure());
        mHumidityTf.setText(mModel.getHumidity());

    }

    @Override
    public void onMessage(boolean errorStatus, String message) {

        if (errorStatus) {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "Weather MVC", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
