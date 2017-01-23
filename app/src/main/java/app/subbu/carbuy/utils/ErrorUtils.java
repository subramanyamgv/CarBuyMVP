package app.subbu.carbuy.utils;

import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import app.subbu.carbuy.R;

/**
 * Created by Subramanyam on 23-Jan-2017.
 */

public class ErrorUtils {

    public enum ErrorType {
        NETWORK_ERROR(R.string.error_network),
        SERVER_ERROR(R.string.error_server),
        UNKNOWN_ERROR(R.string.error_unknown);

        int res;

        ErrorType(int res) {
            this.res = res;
        }

        public int getRes() {
            return res;
        }
    }

    public static ErrorType getErrorType(Throwable e) {

        if (e instanceof UnknownHostException) {
            return ErrorType.NETWORK_ERROR;
        } else if (e instanceof UnknownServiceException) {
            return ErrorType.SERVER_ERROR;
        } else {
            return ErrorType.UNKNOWN_ERROR;
        }
    }
}
