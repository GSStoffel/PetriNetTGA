public abstract class Arc {
    protected String label;
    protected Object input;
    protected Object output;

    abstract void run();

    public Object getInput() {
        return input;
    }

    public Object getOutput() {
        return output;
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setInput(Object input) {
        this.input = input;
    }

    public void setOutput(Object output) {
        this.output = output;
    }
}
