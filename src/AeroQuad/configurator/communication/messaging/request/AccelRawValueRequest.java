package AeroQuad.configurator.communication.messaging.request;


import AeroQuad.configurator.communication.messaging.messageanalyzer.AccelRawValueMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;

public class AccelRawValueRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;

    public AccelRawValueRequest(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new AccelRawValueMessageAnalyser(_messageDispatcher);
    }

    @Override
    public String getStringMessage()
    {
        return "l";
    }
}
