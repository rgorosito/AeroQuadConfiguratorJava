package AeroQuad.configurator.communication.messaging.request;

import AeroQuad.configurator.communication.messaging.messageanalyzer.AttitudePidMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class AttitudePidRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;

    public AttitudePidRequest(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new AttitudePidMessageAnalyser(_messageDispatcher);
    }

    @Override
    public String getStringMessage()
    {
        return "b";
    }
}
