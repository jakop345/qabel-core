package de.qabel.core.drop;

import org.spongycastle.util.encoders.UrlBase64;

import java.util.Arrays;

public abstract class DropIdGenerator {
    /**
     * Length of the drop id in base64 encoding.
     */
    public static final int DROP_ID_LENGTH_BYTE = 32;
    /**
     * Length of the base64url encoded id.
     * The terminating padding character is not part of the id.
     */
    public static final int DROP_ID_LENGTH = 4 * ((DROP_ID_LENGTH_BYTE + 2) / 3) - 1;

    public static DropIdGenerator getDefaultDropIdGenerator() {
        return new AdjustableDropIdGenerator();
    }

    abstract byte[] generateDropIdBytes();

    /**
     * Generates identifier for a drop encoded in Base64url.
     *
     * @return the identifier
     */
    public String generateDropId() {
        byte[] id = Arrays.copyOf(generateDropIdBytes(), DROP_ID_LENGTH_BYTE);
        return new String(UrlBase64.encode(id)).substring(0, DROP_ID_LENGTH); // cut off terminating dot
    }
}
