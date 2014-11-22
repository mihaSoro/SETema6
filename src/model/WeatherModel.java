package model;

import interfaces.IModelListener;

import java.util.List;
import java.util.ArrayList;

/**
 * The WeatherModel class that implements the MVCs model
 */
public class WeatherModel {

    /**
     * The members of this class are:
     * mListener contains a list of listeners for the model - the view or views
     * mTemperature, mHumidity, mPressure are the data that the model holds
     */
    private List<IModelListener> mListeners;

    private double mTemperature;
    private int mHumidity ;
    private double mPressure;

    public WeatherModel()
    {
        mTemperature = 0;
        mHumidity = 0;
        mPressure = 0;

    }

    /**
     * Adds listeners to the model
     * @param listener The listener to be added
     */

    public void addModelListener(IModelListener listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<IModelListener>();
        }

        mListeners.add(listener);
    }

    /**
     * Notifies the views listeners of the changed state
     */
    private void notifyListeners() {
        if (mListeners != null && !mListeners.isEmpty()) {
            for (IModelListener listener : mListeners)
                listener.onUpdate();
        }
    }

    public String getTemperature() {
        return String.valueOf(mTemperature);
    }

    public String getHumidity() {
        return String.valueOf(mHumidity);
    }

    public String getPressure() {
        return String.valueOf(mPressure);
    }

    /**
     *  Sets the models data and notifies the listeners that changes have been made
     * @param temperature The temperature in kelvin
     * @param humidity    The humidity as a percentage
     * @param pressure    The pressure
     */
    public void setWeather(double temperature, int humidity, double pressure){

        //converts the temperature in celsius degrees
        this.mTemperature = temperature -273.15;
        this.mHumidity = humidity;
        this.mPressure = pressure;

        notifyListeners();

    }

}
