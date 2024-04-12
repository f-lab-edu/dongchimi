package dcm.hobby.exception;

public class NotFoundHobbyException extends RuntimeException {

    public NotFoundHobbyException(Long hobbyId) {
        super(String.format("[Hobby ID: %s] hobby is not found", hobbyId));
    }

}
