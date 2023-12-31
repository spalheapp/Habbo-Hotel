package org.alexdev.kepler.messages.outgoing.messenger;

import org.alexdev.kepler.game.player.Player;
import org.alexdev.kepler.game.messenger.MessengerUser;
import org.alexdev.kepler.messages.types.MessageComposer;
import org.alexdev.kepler.server.netty.streams.NettyResponse;
import org.alexdev.kepler.util.config.GameConfiguration;

import java.util.List;

public class FRIENDLIST extends MessageComposer {
    private final Player player;
    private final List<MessengerUser> friends;

    public FRIENDLIST(Player player, List<MessengerUser> friends) {
        this.player = player;
        this.friends = friends;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.friends.size());

        for (MessengerUser friend : this.friends) {
            friend.serialise(response);
        }
    }

    @Override
    public short getHeader() {
        return 263; // "DG"
    }
}