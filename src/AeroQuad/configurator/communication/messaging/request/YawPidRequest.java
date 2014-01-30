package AeroQuad.configurator.communication.messaging.request;

import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.YawPidMessageAnalyser;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;

public class YawPidRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;

    public YawPidRequest(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new YawPidMessageAnalyser(_messageDispatcher);
    }

    @Override
    public String getStringMessage()
    {
        return "c";
    }
}
