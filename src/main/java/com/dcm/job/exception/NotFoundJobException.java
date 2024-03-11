package com.dcm.job.exception;

public class NotFoundJobException extends RuntimeException {

    public NotFoundJobException() {
        super("Job is not found");
    }

    public NotFoundJobException(final Long jobId) {
        super(String.format("[Job ID: %s] job is not found", jobId));
    }

}
