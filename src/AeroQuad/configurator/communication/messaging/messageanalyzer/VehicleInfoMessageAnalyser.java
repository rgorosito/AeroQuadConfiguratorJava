package AeroQuad.configurator.communication.messaging.messageanalyzer;

import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class VehicleInfoMessageAnalyser implements IMessageAnalyser
{
    final int GYRO_DETECTED        = 0x001;
    final int ACCEL_DETECTED       = 0x002;
    final int MAG_DETECTED         = 0x004;
    final int BARO_DETECTED        = 0x008;
//    final int HEADINGHOLD_ENABLED  = 0x010;
//    final int ALTITUDEHOLD_ENABLED = 0x020;
    final int BATTMONITOR_ENABLED  = 0x040;
//    final int CAMERASTABLE_ENABLED = 0x080;
//    final int RANGE_ENABLED        = 0x100;


    private final String GPS_KEY = "GPS";
    private final String RANGE_DETECTION_KEY = "Range Detection";
    private final String CAMERA_STABILITY_KEY = "Camera Stability";
    private final String BATTERY_MONITOR_KEY = "Battery Monitor";
    private final String MOTORS_KEY = "Motors";
    private final String NB_RECEIVER_CHANNEL_KEY = "ReceiverNbChannels";
    private final String FLIGHT_CONFIG_KEY = "FlightConfig";
    private final String BOARD_TYPE_KEY = "Board Type";
    private final String FLIGHT_SOFTWARE_VERSION_KEY = "Software Version";

    private final String RECEIVER_TYPE_KEY = "ReceiverType";
    private final String YAW_DIRECTION_KEY = "YawDirection";


    private final String NOT_ENABLED = "Not Enabled";
    private final String NOT_DETECTED = "Not Detected";

    private final IMessageDispatcher _messageDispatcher;

    public VehicleInfoMessageAnalyser(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public boolean analyzeRawData(final String rawData)
    {
        try
        {
            final String[] vehicleConfigArray = rawData.split(";");
            if (!analyseVehicleEnabledSensors(vehicleConfigArray[0]))
            {
                return false;
            }
            for (final String config : vehicleConfigArray)
            {
                final String[] datas = config.split(":");
                for (int i = 0; i < datas.length; i++)
                {
                    datas[i] = datas[i].trim();
                }
                analyzeData(datas);
            }
        }
        catch (Exception e)
        {
            return false;
        }

        _messageDispatcher.dispatchMessage(IMessageDispatcher.GPS_PROPERTY_KEY, false);
        return true;
    }

    private boolean analyseVehicleEnabledSensors(final String vehicleConfigString)
    {
        int vehicleConfig = 0;
        try
        {
            vehicleConfig = Integer.parseInt(vehicleConfigString);
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        final boolean isGyroscopeDetected = (vehicleConfig & GYRO_DETECTED) != 0;
        _messageDispatcher.dispatchMessage(IMessageDispatcher.GYROSCOPE_PROPERTY_KEY, isGyroscopeDetected);

        final boolean isAccelerometerDetected = (vehicleConfig & ACCEL_DETECTED) != 0;
        _messageDispatcher.dispatchMessage(IMessageDispatcher.ACCELEROMETER_PROPERTY_KEY, isAccelerometerDetected);

        final boolean isMagnetometerDetected = (vehicleConfig & MAG_DETECTED) != 0;
        _messageDispatcher.dispatchMessage(IMessageDispatcher.MAGNETOMETER_PROPERTY_KEY, isMagnetometerDetected);

        final boolean isBarometerDetected = (vehicleConfig & BARO_DETECTED) != 0;
        _messageDispatcher.dispatchMessage(IMessageDispatcher.BAROMETER_PROPERTY_KEY, isBarometerDetected);

        final boolean isBatteryMonitorEnabled = (vehicleConfig & BATTMONITOR_ENABLED) != 0;
        _messageDispatcher.dispatchMessage(IMessageDispatcher.BATTERY_MONITOR_PROPERTY_KEY, isBatteryMonitorEnabled);

        return true;
    }

    private void analyzeData(final String[] datas)
    {

        if (datas[0].contains(GPS_KEY))
        {
            final boolean isGpsEnabled = !datas[1].contains(NOT_ENABLED);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.GPS_PROPERTY_KEY, isGpsEnabled);
        }
        else if (datas[0].contains(RANGE_DETECTION_KEY))
        {
            final boolean isRangerEnabled = !datas[1].contains(NOT_ENABLED);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RANGE_DETECTION_PROPERTY_KEY, isRangerEnabled);
        }
        else if (datas[0].contains(CAMERA_STABILITY_KEY))
        {
            final boolean isCameraStabilityEnabled = !datas[1].contains(NOT_ENABLED);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.CAMERA_STABILITY_PROPERTY_KEY, isCameraStabilityEnabled);
        }
        else if (datas[0].contains(BATTERY_MONITOR_KEY))
        {
            final boolean isBatteryMonitorEnabled = !datas[1].contains(NOT_ENABLED);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.BATTERY_MONITOR_PROPERTY_KEY, isBatteryMonitorEnabled);
        }
        else if (datas[0].contains(MOTORS_KEY))
        {
            final int nbMotors = Integer.parseInt(datas[1]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.NB_MOTORS_PROPERTY_KEY, nbMotors);
        }
        else if (datas[0].contains(NB_RECEIVER_CHANNEL_KEY))
        {
            final int nbReceiverChannel = Integer.parseInt(datas[1]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.NB_RECEIVER_CHANNEL_PROPERTY_KEY, nbReceiverChannel);
        }
        else if (datas[0].contains(FLIGHT_CONFIG_KEY))
        {
            _messageDispatcher.dispatchMessage(IMessageDispatcher.FLIGHT_CONFIG_PROPERTY_KEY, datas[1]);
        }
        else if (datas[0].contains(BOARD_TYPE_KEY))
        {
            _messageDispatcher.dispatchMessage(IMessageDispatcher.BOARD_TYPE_PROPERTY_KEY, datas[1]);
        }
        else if (datas[0].contains(FLIGHT_SOFTWARE_VERSION_KEY))
        {
            _messageDispatcher.dispatchMessage(IMessageDispatcher.FLIGHT_SOFTWARE_VERSION_PROPERTY_KEY, datas[1]);
        }
        else if (datas[0].contains(RECEIVER_TYPE_KEY))
        {
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RECEIVER_TYPE_KEY, datas[1]);
        }
        else if (datas[0].contains(YAW_DIRECTION_KEY))
        {
            _messageDispatcher.dispatchMessage(IMessageDispatcher.YAW_DIRECTION_KET, datas[1]);
        }
    }
}
