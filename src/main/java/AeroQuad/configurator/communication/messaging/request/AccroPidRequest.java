package AeroQuad.configurator.communication.messaging.request;

import AeroQuad.configurator.communication.messaging.messageanalyzer.AccroPidMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class AccroPidRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;

    public AccroPidRequest(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new AccroPidMessageAnalyser(_messageDispatcher);
    }

    @Override
    public String getStringMessage()
    {
        return "a";
    }
}
