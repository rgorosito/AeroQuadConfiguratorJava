package AeroQuad.configurator.communication.messaging.request;

import AeroQuad.configurator.communication.messaging.messageanalyzer.IMessageAnalyser;
import AeroQuad.configurator.communication.messaging.messageanalyzer.VehicleInfoMessageAnalyser;
import AeroQuad.configurator.messagesdispatcher.IMessageDispatcher;

public class VehicleInfoRequest implements IRequest
{
    private final IMessageDispatcher _messageDispatcher;

    public VehicleInfoRequest(final IMessageDispatcher messageDispatcher)
    {
        _messageDispatcher = messageDispatcher;
    }

    @Override
    public IMessageAnalyser getMessageAnalyser()
    {
        return new VehicleInfoMessageAnalyser(_messageDispatcher);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getStringMessage()
    {
        return "#";
    }
}
