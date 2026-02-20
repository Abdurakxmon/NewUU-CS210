public class MathTool {
    public static long factorialRecursive(int n) {
        if(n==0) return 1;
        if(n==1) return 1;
        return factorialRecursive(n-1)*n;
    }

    public static long factorialIterative(int n) {
        long ans=1;
        for(int i=1;i<=n;i++) {
            ans=ans*i;
        }
        return ans;
    }
}
