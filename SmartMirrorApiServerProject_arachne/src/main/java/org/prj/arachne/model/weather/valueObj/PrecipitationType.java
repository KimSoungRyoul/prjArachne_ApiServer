package org.prj.arachne.model.weather.valueObj;


public enum PrecipitationType {


    NONE(0), RAIN(1), RAINANDSNOW(2), SNOW(3);

    private int value;

    PrecipitationType(int num) {
        value = num;

    }

    public static PrecipitationType valueOf(int num) {
        switch (num) {
            case 0:
                return NONE;
            case 1:
                return RAIN;
            case 2:
                return RAINANDSNOW;
            case 3:
                return SNOW;
            default:
                return NONE;
        }

    }
}
