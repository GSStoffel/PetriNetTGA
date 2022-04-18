public class ResetArc extends Arc {

    @Override
    void run() {
        if(output instanceof Place){
            ((Place) output).removeAllTokens();
        }
    }
}
