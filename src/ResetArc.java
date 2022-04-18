public class ResetArc extends Arc {

    @Override
    void run() {
        if(input instanceof Place){
            ((Place) output).removeAllTokens();
        }
    }
}
