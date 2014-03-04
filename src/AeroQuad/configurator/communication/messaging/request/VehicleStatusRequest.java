package AeroQuad.configurator.communication.messaging.request;

import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.VehicleStatusMessageAnalyser;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class VehicleStatusRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;
    private boolean _activated;

    public VehicleStatusRequest(final IMessageDispatcher messageDispatcher, final boolean activated)
    {
        _messageDispatcher = messageDispatcher;
        _activated = activated;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new VehicleStatusMessageAnalyser(_messageDispatcher);
    }

    @Override
    public String getStringMessage()
    {
        if (_activated)
        {
            return "s";
        }
        else
        {
            return "S";
        }
    }
}
