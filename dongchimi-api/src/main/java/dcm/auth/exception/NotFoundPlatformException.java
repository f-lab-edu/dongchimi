package dcm.auth.exception;

public class NotFoundPlatformException extends RuntimeException {

    public NotFoundPlatformException(final String platform) {
        super(platform + " 플랫폼은 존재하지 않습니다.");
    }

}
