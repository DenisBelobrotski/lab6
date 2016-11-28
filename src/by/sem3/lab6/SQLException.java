package by.sem3.lab6;

class SQLException extends Exception {
    public SQLException() {
        super("Incorrect or unsupported SQL request.");
    }

    public SQLException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
