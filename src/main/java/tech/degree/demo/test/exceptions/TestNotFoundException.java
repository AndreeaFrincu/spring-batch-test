package tech.degree.demo.test.exceptions;

public class TestNotFoundException extends RuntimeException {
    public TestNotFoundException(String message) {
        super(message);
    }
}
