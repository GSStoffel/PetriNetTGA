public abstract class Arc {
    protected Object input;
    protected Object output;

    public Object getInput() {
        return input;
    }

    public Object getOutput() {
        return output;
    }

    abstract void run();
}
