package dcm.hobbydetail.exception;

public class NotFoundHobbyDetailException extends RuntimeException {

    public NotFoundHobbyDetailException(Long hobbyDetailId) {
        super(String.format("[Hobby Detail Id ID: %s] hobby detail is not found", hobbyDetailId));
    }
}
