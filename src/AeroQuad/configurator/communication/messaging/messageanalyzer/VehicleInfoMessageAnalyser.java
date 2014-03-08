package AeroQuad.configurator.communication.messaging.messageanalyzer;

import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class VehicleInfoMessageAnalyser implements IMessageAnalyser
{
    private final String GPS_KEY = "GPS";
    private final String RANGE_DETECTION_KEY = "Range Detection";
    private final String CAMERA_STABILITY_KEY = "Camera Stability";
    private final String BATTERY_MONITOR_KEY = "Battery Monitor";
    private final String MAGNETOMETER_KEY = "Magnetometer";
    private final String BAROMETER_KEY = "Barometer";
    private final String ACCELEROMETER_KEY = "Accelerometer";
    private final String GYROSCOPE_KEY = "Gyroscope";
    private final String MOTORS_KEY = "Motors";
    private final String NB_RECEIVER_CHANNEL_KEY = "Receiver Channels";
    private final String FLIGHT_CONFIG_KEY = "Flight Config";
    private final String BOARD_TYPE_KEY = "Board Type";
    private final String FLIGHT_SOFTWARE_VERSION_KEY = "Software Version";

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
        final String[] datas = rawData.split(":");
        if (datas.length < 2)
        {
            return false;
        }

        for (int i = 0; i < datas.length;i++)
        {
            datas[i] = datas[i].trim();
        }

        return analyzeData(datas);
    }

    private boolean analyzeData(final String[] datas)
    {
        if (datas[0].contains(GPS_KEY))
        {
            final boolean isGpsEnabled = !datas[1].contains(NOT_ENABLED);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.GPS_PROPERTY_KEY, isGpsEnabled);
            return true;
        }
        else if (datas[0].contains(RANGE_DETECTION_KEY))
        {
            final boolean isRangerEnabled = !datas[1].contains(NOT_ENABLED);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.RANGE_DETECTION_PROPERTY_KEY, isRangerEnabled);
            return true;
        }
        else if (datas[0].contains(CAMERA_STABILITY_KEY))
        {
            final boolean isCameraStabilityEnabled = !datas[1].contains(NOT_ENABLED);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.CAMERA_STABILITY_PROPERTY_KEY, isCameraStabilityEnabled);
            return true;
        }
        else if (datas[0].contains(BATTERY_MONITOR_KEY))
        {
            final boolean isBatteryMonitorEnabled = !datas[1].contains(NOT_ENABLED);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.BATTERY_MONITOR_PROPERTY_KEY, isBatteryMonitorEnabled);
            return true;
        }
        else if (datas[0].contains(MAGNETOMETER_KEY))
        {
            final boolean isMagnetometerDetected = !datas[1].contains(NOT_DETECTED);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.MAGNETOMETER_PROPERTY_KEY, isMagnetometerDetected);
            return true;
        }
        else if (datas[0].contains(BAROMETER_KEY))
        {
            final boolean isBarometerDetected = !datas[1].contains(NOT_DETECTED);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.BAROMETER_PROPERTY_KEY, isBarometerDetected);
            return true;
        }
        else if (datas[0].contains(ACCELEROMETER_KEY))
        {
            final boolean isAccelerometerDetected = !datas[1].contains(NOT_DETECTED);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.ACCELEROMETER_PROPERTY_KEY, isAccelerometerDetected);
            return true;
        }
        else if (datas[0].contains(GYROSCOPE_KEY))
        {
            final boolean isGyroscopeDetected = !datas[1].contains(NOT_DETECTED);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.GYROSCOPE_PROPERTY_KEY, isGyroscopeDetected);
            return true;
        }
        else if (datas[0].contains(MOTORS_KEY))
        {
            final int nbMotors = Integer.parseInt(datas[1]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.NB_MOTORS_PROPERTY_KEY, nbMotors);
            return true;
        }
        else if (datas[0].contains(NB_RECEIVER_CHANNEL_KEY))
        {
            final int nbReceiverChannel = Integer.parseInt(datas[1]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.NB_RECEIVER_CHANNEL_PROPERTY_KEY, nbReceiverChannel);
            return true;
        }
        else if (datas[0].contains(FLIGHT_CONFIG_KEY))
        {
            _messageDispatcher.dispatchMessage(IMessageDispatcher.FLIGHT_CONFIG_PROPERTY_KEY, datas[1]);
            return true;
        }
        else if (datas[0].contains(BOARD_TYPE_KEY))
        {
            _messageDispatcher.dispatchMessage(IMessageDispatcher.BOARD_TYPE_PROPERTY_KEY, datas[1]);
            return true;
        }
        else if (datas[0].contains(FLIGHT_SOFTWARE_VERSION_KEY))
        {
            _messageDispatcher.dispatchMessage(IMessageDispatcher.FLIGHT_SOFTWARE_VERSION_PROPERTY_KEY, datas[1]);
            return true;
        }
        return false;
    }
}
