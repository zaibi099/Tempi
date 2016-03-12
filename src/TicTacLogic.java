import java.util.Random;


public class TicTacLogic{

	private char[][] winnerCombo=null;
	private int baari;
	private int winPos;
	private boxNode[] boxArray= null;
	char[] rBot=null;
	char[] pLayr=null;
	
	TicTacLogic(int turn){
		winPos = 0;
		rBot = new char[5]; 
		pLayr = new char[5];
		boxArray = new boxNode[9];
		
		for(int i=0;i<9;i++){
			boxArray[i] = new boxNode();
			boxArray[i].empty=true;
			boxArray[i].boxNum=i;
			boxArray[i].playerId=-1;
		}
		
		winnerCombo = new char[8][3];
		populateWinnerCombos();
	}
	
	public int countDabbay(){
		int counter = 1;
		for(int i=0;i<=8;i++){
			if(boxArray[i].empty==false){
				counter++;
			}
		}
		
		System.out.println("Baaari   === " + counter);
		return counter;
	}
	
	public int robotMove(){
		
		int returnMove=-1;
		Random rand = new Random();
		
        baari = countDabbay();
		
		if(baari==1){						// means all boxes are empty, ist turn
			returnMove = rand.nextInt(9);			
		}
		else if(baari==2){						// means it is second Trun Can Place anyWhere.
			do{
				returnMove = rand.nextInt(9);
				System.out.println("----2nd Baari-----");
				
			}while(cellEmpty(returnMove)!=true);
		}
		else if(baari==3){						// means it is first turn , no box is occupied
			do{
				returnMove = rand.nextInt(9);
				System.out.println("----3rd Baari-----");
				
			}while(cellEmpty(returnMove)!=true);
		}
		else if(baari>=4){
			if(meConsecutive()==true){
				returnMove = winPos;
			}
			else if(enemyConsecutive()==true){
				System.out.println("-----ENEMY TIME---");
				returnMove = winPos;
			}
			else{
				if(gameDraw()==true){
					return -1;
				}
				else{
				do{
					returnMove = rand.nextInt(9);
					System.out.println("----Baari : " + baari);
					
				}while(cellEmpty(returnMove)!=true);
				}
			}
		}
		return returnMove;
	}
	
	public boolean gameDraw(){
		for(int i=0;i<9;i++){
			if(boxArray[i].empty==true){
				return false;
			}
		}
		return true;
	}
	
	public boolean enemyConsecutive(){

		System.out.println("-----ENEMY TIME---");
		String plyr = getPlayerPositions();
		if(plyr.length()<2){
			return false;
		}
		
		else{
			for(int i=0;i<8;i++){
//				System.out.println("winner Combo = : " + String.valueOf(winnerCombo[i]));
//				System.out.println("Bot Values   = : " + String.valueOf(plyr));
				if(RemoveMatchedCharsInnStrings(String.valueOf(winnerCombo[i]),plyr)==true){
					return true;
				}
			}
			}
			return false;
	}
	
    public boolean meConsecutive(){
		
		String bot = getBotPositions();
		if(bot.length()<2){
			return false;
		}
		else{
			for(int i=0;i<8;i++){
//				System.out.println("winner Combo = : " + String.valueOf(winnerCombo[i]));
//				System.out.println("Bot Values   = : " + String.valueOf(bot));
				if(RemoveMatchedCharsInnStrings(String.valueOf(winnerCombo[i]),bot)==true){
					return true;
				}
			}
			}
		
		return false;
	}
    
    public boolean RemoveMatchedCharsInnStrings(String first,String second)
    {
        for(int i = 0 ;i < second.length() ; i ++)
        {
        	for(int j = 0 ;j < first.length() ; j ++){
        		if(first.charAt(j)==second.charAt(i)){
        			first = removeCharAt(first,j);
        		}
        	}
        }
        System.out.println(" f --- >" + first);
        System.out.println(" s --- >" + second);
        if(first.length()==1){
        	int r = Integer.parseInt(first);
        	System.out.println("VALUE OF ALONE IS : " + r);
        	if(cellEmpty(r)==true){
        		winPos=r;
        		return true;
        	}
        	
        }
        return false;

    }
    
    private String removeCharAt(String s, int i) {
        StringBuffer buf = new StringBuffer(s.length() -1);
        buf.append(s.substring(0, i)).append(s.substring(i+1));
        return buf.toString();
    }
	
	public String getBotPositions(){
		String pos ="";
		
		for(int i=0;i<9;i++){
			if(boxArray[i].empty==false && boxArray[i].playerId==1){
				pos+=boxArray[i].boxNum;
			}
		}
		System.out.println("\nBot String : " + pos);
		return pos.trim();
	}
	public String getPlayerPositions(){
        String pos ="";
		
		for(int i=0;i<9;i++){
			if(boxArray[i].empty==false && boxArray[i].playerId==0){
				pos+=boxArray[i].boxNum;
			}
		}
		System.out.println("\nPlayer String : " + pos);
		return pos.trim();
	}
	
	public boolean cellEmpty(int num){
		if(boxArray[num].empty==true){
			return true;
		}
		return false;
	}
	
	public int checkTries(int x){
		int counter=0;
			for(int i=0;i<9;i++){
				if(boxArray[i].empty==false && boxArray[i].playerId==x){
					counter++;
				}
			}
		return counter;
	}
	
	public void setPlayerIpnut(int playerId,int boxNum,String b){
	
		boxArray[boxNum].empty=false;
		System.out.println(boxNum + "IS FULL\n");
		boxArray[boxNum].playerId=playerId;
		boxArray[boxNum].boxNum=boxNum;
	}
	
	
    public int checkWinner(){
		
		int ret_val=-1;
		if(countDabbay()<2){	
			ret_val=-1;
		}
		else{
			
			char[] ert = getBotPositions().toCharArray();
			for(int i=0;i<ert.length;i++){
				rBot[i]=ert[i];
			}
		    ert = getPlayerPositions().toCharArray();
			for(int i=0;i<ert.length;i++){
				pLayr[i]=ert[i];
			}
			
			Minesort(rBot);
			Minesort(pLayr);
			
				int counter=0,counter_2=0;
				
				for(int i=0;i<8;i++){
					for(int j=0;j<3;j++){
						for(int k=0;k<5;k++){
							if(rBot[k]==winnerCombo[i][j]){
								counter++;
							}
							if(pLayr[k]==winnerCombo[i][j]){
								counter_2++;
							}
						}
					}
					if(counter==3){
						ret_val=0;
						break;
					}
					if(counter_2==3){
						ret_val=1;
						break;
					}
					else{
						counter=0;
						counter_2=0;
					}
				}
		}
		return ret_val;
	}
	
	private void populateWinnerCombos(){
	
		winnerCombo[0]="012".toCharArray();
		winnerCombo[1]="048".toCharArray();
		winnerCombo[2]="036".toCharArray();
		winnerCombo[3]="147".toCharArray();
		winnerCombo[4]="246".toCharArray();
		winnerCombo[5]="258".toCharArray();
		winnerCombo[6]="345".toCharArray();
		winnerCombo[7]="678".toCharArray();
	}
	
	private void Minesort(char[] arr){
		
		int sz=getLength(arr);
		for(int i=0;i<sz;i++){
			for(int j=i;j<sz;j++){
				
				if(arr[i]>arr[j]){
					
					char x=arr[j];
					arr[j]=arr[i];
					arr[i]=x;
					i=0;	
				}
			}
		}
	}
	
	public int getLength(char [] arr){
	
		int size=0;
		for(int x=0;x<arr.length;x++){
			if(arr[x]!='\0'){
				size++;
			}
		}
		return size;
	}

}