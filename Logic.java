package KelpAssignment;

// All the calculations will be done here.

public class Logic {

    // These functions specific to withdrawing and depositing will perform the necessary calculations.
    public int calcWithdraw( Integer currentAmountW, int amount){
        currentAmountW -= amount;
        return currentAmountW;
    }
    public int calcDeposit(Integer currentAmountD, int amount){
        currentAmountD += amount;
        return currentAmountD;
    }




    // Returning the calculated amount of both Withdrawing and Depositing
    public Integer calcWithdrawAmount(Integer currentAmountD, int amount){

        if ( (currentAmountD - amount) >= 0 ) {
            return calcWithdraw(currentAmountD, amount);
        }else{
            return -1;
        }
        
    }
    public Integer calcDepositAmount(Integer currentAmountW, int amount){

        if ( (currentAmountW + amount) >= 0 ) {
            return calcDeposit(currentAmountW, amount);
        }else{
            return -1;
        }

    }

    

}
