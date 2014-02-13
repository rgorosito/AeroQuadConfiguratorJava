package AeroQuad.configurator.ui.mainpanel.monitoring.motordisplaypanel;

public interface IMotorDisplayPanel
{
    void setMotor1CommandValue(String value);
    void setMotor2CommandValue(String value);
    void setMotor3CommandValue(String value);
    void setMotor4CommandValue(String value);
    void setMotor5CommandValue(String value);
    void setMotor6CommandValue(String value);
    void setMotor7CommandValue(String value);
    void setMotor8CommandValue(String value);

    void setNbMotor(int nbMotor);

    void setEditable(boolean editable);

    void setMaxMotorsValue(int maxValue);

    public void addUserMotorValueChangedByUser(IUserMotorValueChangedListenrer listener);

    void setMotorValue(int motor, Integer value);
}
