package AeroQuad.configurator.communication.messaging.request;

import AeroQuad.configurator.communication.messaging.messageanalyzer.GpsPidMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class GpsPidRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;

    public GpsPidRequest(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new GpsPidMessageAnalyser(_messageDispatcher);
    }

    @Override
    public String getStringMessage()
    {
        return "v";
    }
}
