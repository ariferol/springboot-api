package tr.com.common.core.base;

import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author arif.erol
 */
public class  ResponseDTO<T> implements IBaseResponseDTO {
    private final T data;
    private final int statusCode;
    private final String description;

    private final long errorCode;
    private final String responseTime;

    private ResponseDTO(Builder<T> builder) {
        this.data = builder.data;
        this.statusCode = builder.statusCode;
        this.description = builder.description;
        this.errorCode = builder.errorCode;
        this.responseTime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
    }


    public T getData() {
        return data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public static class Builder<T> {
        private T data;
        private int statusCode;
        private String description;

        private long errorCode;

        private Builder(int statusCode, String description) {
            this.statusCode = statusCode;
            this.description = description;
        }

        public static <T> Builder<T> newInstance() {
            return new Builder<T>(HttpStatus.OK.value(), HttpStatus.OK.toString());
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder<T> description(String description) {
            this.description = description;
            return this;
        }

        public Builder<T> errorCode(long errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ResponseDTO<T> build() {
            return new ResponseDTO<T>(this);
        }
    }

}
