package AeroQuad.configurator.communication.messaging.request;

import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.VehicleStatusMessageAnalyser;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class VehicleStatusRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;

    public VehicleStatusRequest(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new VehicleStatusMessageAnalyser(_messageDispatcher);
    }

    @Override
    public String getStringMessage()
    {
        return "s";
    }
}
