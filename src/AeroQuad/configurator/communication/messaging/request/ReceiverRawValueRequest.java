package AeroQuad.configurator.communication.messaging.request;

import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.ReceiverRawValueMessageAnalyser;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class ReceiverRawValueRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;

    public ReceiverRawValueRequest(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new ReceiverRawValueMessageAnalyser(_messageDispatcher) ;
    }

    @Override
    public String getStringMessage()
    {
        return "t";
    }
}
