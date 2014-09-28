package AeroQuad.configurator.ui.mainpanel.setup.accelcalibration;

public enum CalibrationStep
{
    FLAT_UP,
    FLAT_DOWN,
    LEFT_SIDE_DOWN,
    RIGHT_SIDE_DOWN,
    NOSE_UP,
    NOSE_DOWN,
    FINISHED;

    public static CalibrationStep getNextStep(final CalibrationStep currentCalibrationStep)
    {
        switch (currentCalibrationStep)
        {
            case FLAT_UP:
                return FLAT_DOWN;
            case FLAT_DOWN:
                return LEFT_SIDE_DOWN;
            case LEFT_SIDE_DOWN:
                return RIGHT_SIDE_DOWN;
            case RIGHT_SIDE_DOWN:
                return NOSE_UP;
            case NOSE_UP:
                return NOSE_DOWN;
            case NOSE_DOWN:
                return FINISHED;
        }
        return FINISHED;
    }
}
