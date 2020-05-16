package googlechallenge;

/**
 *
 * @author Jonathan
 */
public class GoogleChallenge {

    public static void main(String[] args) {
        String list[] = {"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"};
        //String list[] = {"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"};
        list = Solution.solution(list);
        
        for (int i = 0; i < list.length; ++i){
            System.out.println(list[i]);
        }
        
        
    }
}