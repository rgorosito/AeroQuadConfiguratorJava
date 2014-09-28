package AeroQuad.configurator.communication.messaging.messageanalyzer;


import AeroQuad.configurator.messagesdispatcher.BatteryMonitorConfigData;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class BatteryConfigMessageAnalyser implements IMessageAnalyser
{
    private final IMessageDispatcher _messageDispatcher;

    public BatteryConfigMessageAnalyser(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public boolean analyzeRawData(final String rawData)
    {
        try
        {
            final String splittedData[] = rawData.split(",");
            final BatteryMonitorConfigData configData = new BatteryMonitorConfigData(splittedData[0], splittedData[1], splittedData[2]);
            _messageDispatcher.dispatchMessage(IMessageDispatcher.BATTERY_MONITOR_CONFIG_KEY,configData);
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }
}
