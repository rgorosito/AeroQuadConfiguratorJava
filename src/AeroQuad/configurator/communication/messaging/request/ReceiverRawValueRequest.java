package AeroQuad.configurator.communication.messaging.request;

import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.ReceiverRawValueMessageAnalyser;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class ReceiverRawValueRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;
    private final int _nbChannel;

    public ReceiverRawValueRequest(final IMessageDispatcher messageDispatcher, final int nbChannel)
    {
        _messageDispatcher = messageDispatcher;
        _nbChannel = nbChannel;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new ReceiverRawValueMessageAnalyser(_messageDispatcher, _nbChannel) ;
    }

    @Override
    public String getStringMessage()
    {
        return "t";
    }
}
