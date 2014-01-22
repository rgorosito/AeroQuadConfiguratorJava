package AeroQuad.configurator.communication.messaging.request;

import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.SensorsValuesMessageAnalyser;
import AeroQuad.configurator.messagedispatcher.IMessageDispatcher;


public class SensorsValueRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;
    private boolean _activated;

    public SensorsValueRequest(final IMessageDispatcher messageDispatcher, final boolean activated)
    {
        _messageDispatcher = messageDispatcher;
        _activated = activated;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new SensorsValuesMessageAnalyser(_messageDispatcher);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getStringMessage()
    {
        if (_activated)
        {
            return "i";
        }
        else
        {
            return "I";
        }
    }
}
