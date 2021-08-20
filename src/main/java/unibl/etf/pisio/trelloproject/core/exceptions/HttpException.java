package unibl.etf.pisio.trelloproject.core.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class HttpException extends RuntimeException {

    private HttpStatus status;
    private Object data;

    public HttpException(HttpStatus _status, Object _data){
        this.status = _status;
        this.data = _data;
    }

    public HttpException(Object _data){
        this(HttpStatus.INTERNAL_SERVER_ERROR, _data);
    }

    public HttpException(){
        this(HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
}
