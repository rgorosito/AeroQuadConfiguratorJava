package AeroQuad.configurator.communication.messaging.request;


import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.MagRawValueMessageAnalyser;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class MagRawValueRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;

    public MagRawValueRequest(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new MagRawValueMessageAnalyser(_messageDispatcher);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getStringMessage()
    {
        return "j";  //To change body of implemented methods use File | Settings | File Templates.
    }
}
