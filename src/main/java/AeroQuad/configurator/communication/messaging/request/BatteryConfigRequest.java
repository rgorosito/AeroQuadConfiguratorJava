package AeroQuad.configurator.communication.messaging.request;


import AeroQuad.configurator.communication.messaging.messageanalyzer.BatteryConfigMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class BatteryConfigRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;

    public BatteryConfigRequest(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new BatteryConfigMessageAnalyser(_messageDispatcher);
    }

    @Override
    public String getStringMessage()
    {
        return "n";
    }
}
