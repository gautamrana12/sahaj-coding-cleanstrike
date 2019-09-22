public class CleanStrike {

    public static void main(String[] args) {
        int[] score = new int[2];
        int strike ;
        int coin=9;
        int [] playerStats = new int[2];
        int []playerFoul = new int[2];
        int playerToss;
        int flag = 0;
        int redCoin = 1;
        playerToss = (int)(Math.random()*2);

        while(true){
            strike = (int)(Math.random()*14);
            if((strike >=0 && strike <=8) && coin!=0) {
                score[playerToss] += 1;
                coin--;
                playerStats[playerToss]=0;
            } else if(strike==12 && redCoin!=0) {
                score[playerToss] += 3;              //Red Strike
                playerStats[playerToss]=0;
                redCoin=0;
            } else if(strike==9) {
                score[playerToss] -=1;              //Striker Strike
                playerFoul[playerToss] += 1;
            } else if(strike==11) {
                score[playerToss] -=2;             // Defunct Coin
                coin--;
                playerFoul[playerToss] += 1;
            } else if(strike==10) {
                int coinPocketed = (int)(Math.random()*coin);
                coin = coin - coinPocketed;
                score[playerToss] +=2;              //Multi Strike
                coin += 2;
                playerStats[playerToss]=0;
            } else if(strike==13) {                 //Successive 3 turns no coin Pocketed
                if(playerToss==0) {
                    playerStats[playerToss] += 1;
                    if(playerStats[playerToss]==3) {
                        score[playerToss] -= 1;
                        playerFoul[playerToss] += 1;
                    }
                } else {
                    playerStats[playerToss] += 1;
                    if(playerStats[playerToss]==3) {
                        score[playerToss] -= 1;
                        playerFoul[playerToss] += 1;
                    }
                }
            }
            if(playerFoul[playerToss]==3) {
                score[playerToss] -= 1;
            }
            flag = winner(score,coin, flag, redCoin);
            if(flag==1)
                break;

            playerToss = 1 - playerToss;
        }

    }
           // Logic for Winner or Looser or Draw

    private static int winner(int[] score, int coin, int flag, int redCoin) {
        if(score[0] >=5 && (score[0]-score[1]) >= 3) {
            System.out.println("Player 1 wins!!!");
            System.out.println("Player 1 Scores : " + score[0]);
            System.out.println("Player 2 Scores : " + score[1]);
            flag = 1;
        } else if(score[1] >= 5 && (score[1] - score[0]) >= 3) {
            System.out.println("Player 2 wins!!!");
            System.out.println("Player 1 Scores : " + score[0]);
            System.out.println("Player 2 Scores : " + score[1]);
            flag = 1;
        } else if(coin == 0 && redCoin==0) {
            if(((score[0] < 5 && score[1] < 5) && score[0]-score[1] < 3) || (score[0]==score[1])) {
                System.out.println("Match Draw!!!");
                System.out.println("Player 1 Scores : " + score[0]);
                System.out.println("Player 2 Scores : " + score[1]);
            } else if(score[0] > score[1]) {
                System.out.println("Player 1 wins at last!!!");
                System.out.println("Player 1 Scores : " + score[0]);
                System.out.println("Player 2 Scores : " + score[1]);
            } else {
                System.out.println("Player 2 wins at last!!!");
                System.out.println("Player 1 Scores : " + score[0]);
                System.out.println("Player 2 Scores : " + score[1]);
            }
            flag = 1;
        }
        return flag;
    }
}
