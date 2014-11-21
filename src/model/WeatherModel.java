package model;

import interfaces.IModelListener;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by hp on 11/20/2014.
 */
public class WeatherModel {

    private List<IModelListener> mListeners;

    private int mTemperature;
    private int mHumidity ;
    private double mPressure;

    public WeatherModel()
    {
        mTemperature = 0;
        mHumidity = 0;
        mPressure = 0;

    }

    public void addModelListener(IModelListener listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<IModelListener>();
        }

        mListeners.add(listener);
    }

    /**
     * Notifies the views listeners of the changed state (value)
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

    public void setWeather(int temperature, int humidity, double pressure){

        this.mTemperature = temperature;
        this.mHumidity = humidity;
        this.mPressure = pressure;

        notifyListeners();

    }

}
