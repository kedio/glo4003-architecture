package ca.ulaval.glo4003.model;

public class SectionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3535297065442490385L;

    public SectionNotFoundException(String message) {
        super(message);
    }
}