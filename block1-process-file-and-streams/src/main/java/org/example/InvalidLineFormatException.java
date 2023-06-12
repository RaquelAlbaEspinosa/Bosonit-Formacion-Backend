package org.example;

public class InvalidLineFormatException extends Exception {
    private Object line;

    public InvalidLineFormatException(Object line, String message) {
        super(line + " -> " + message);
        printStackTrace();
        this.line = line;
    }

    public Object getLine() {
        return line;
    }

}
