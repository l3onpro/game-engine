package shellderp.game.network;

import java.net.SocketAddress;

/**
 * A PendingConnection is used to track half started connections, that is, the client has sent a connect
 * request and we sent one back, now we wait for the final ack of the 3-way handshake, as in TCP.
 * <p>
 * Created by: Mike
 */
class PendingConnection {
    private final SocketAddress socketAddress;
    private final int serverSequence;

    // Not used in comparison, but for removing old entries.
    private transient final long timeAddedMs;

    PendingConnection(SocketAddress socketAddress, int serverSequence) {
        this.socketAddress = socketAddress;
        this.serverSequence = serverSequence;
        this.timeAddedMs = System.currentTimeMillis();
    }

    public long getTimeAddedMs() {
        return timeAddedMs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PendingConnection that = (PendingConnection) o;

        if (serverSequence != that.serverSequence) {
            return false;
        }
        if (!socketAddress.equals(that.socketAddress)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = socketAddress.hashCode();
        result = 31 * result + serverSequence;
        return result;
    }

    @Override
    public String toString() {
        return "PendingConnection{" +
               "socketAddress=" + socketAddress +
               ", serverSequence=" + serverSequence +
               ", timeAddedMs=" + timeAddedMs +
               '}';
    }
}
