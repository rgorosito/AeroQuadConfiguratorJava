package AeroQuad.configurator.communication.messaging.request;

import AeroQuad.configurator.communication.messaging.messageanalyzer.AltitudeHoldPidMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;

public class AltitudeHoldPidRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;

    public AltitudeHoldPidRequest(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new AltitudeHoldPidMessageAnalyser(_messageDispatcher);
    }

    @Override
    public String getStringMessage()
    {
        return "d";
    }
}
