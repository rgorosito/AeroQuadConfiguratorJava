package AeroQuad.configurator.ui.mainpanel.setup.accelcalibration;



public interface IAccelCalibrationPanel
{
    final static String PROGRESS_STRING = "Progress : ";

    final static String FLAT_UP_TEXT = "<HTML><CENTER>Place the AeroQuad motionless on a flat surface<br>and press start.</CENTER></HTML>";
    final static String FLAT_DOWN_TEXT = "<HTML><CENTER>Place the AeroQuad upside down<br>and press next.</CENTER></HTML>";
    final static String LEFT_SIDE_DOWN_TEXT = "<HTML><CENTER>Place the AeroQuad left edge down<br>and press next.</CENTER></HTML>";
    final static String RIGHT_SIDE_DOWN_TEXT = "<HTML><CENTER>Place the AeroQuad right edge down<br>and press next.</CENTER></HTML>";
    final static String NOSE_UP_TEXT = "<HTML><CENTER>Place the AeroQuad rear edge down<br>and press next.</CENTER></HTML>";
    final static String NOSE_DOWN_TEXT = "<HTML><CENTER>Place the AeroQuad front edge down<br>and press next.</CENTER></HTML>";
    final static String FINISH_TEXT = "<HTML><CENTER>Place back the AeroQuad motionless on a flat surface<br>and press finish</CENTER></HTML>";


    final static String FLAT_UP_IMAGE_NAME = "images/AccelCalflatUp.png";
    final static String FLAT_DOWN_IMAGE_NAME = "images/AccelCalflatDown.png";
    final static String LEFT_SIDE_DOWN_IMAGE_NAME = "images/AccelCalLeftSideDown.png";
    final static String RIGHT_SIDE_IMAGE_NAME = "images/AccelCalRightSideDown.png";
    final static String NOSE_UP_IMAGE_NAME = "images/AccelCalNoseUp.png";
    final static String NOSE_DOWN_IMAGE_NAME = "images/AccelCalNoseDown.png";

    void setCancelButtonEnabled(boolean enabled);

    void setNextButtonEnabeld(boolean enabled);

    void setProgress(int progress);

    void setCurrentCalibrationStep(CalibrationStep currentCalibrationStep);
}

