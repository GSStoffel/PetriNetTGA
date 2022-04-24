public abstract class Arc {
    protected Object input;
    protected Object output;
    protected int cardinality = 1;

    abstract void run();

    public int getCardinality() {
        return cardinality;
    }

    public Object getInput() {
        return input;
    }

    public Object getOutput() {
        return output;
    }

    public void setInput(Object input) {
        this.input = input;
    }

    public void setOutput(Object output) {
        this.output = output;
    }
}
