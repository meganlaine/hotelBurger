
/**
 * Write a description of class Member here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Member extends Guest
{
    // instance variables - replace the example below with your own
    int membershipNumber = 1000;

    /**
     * Constructor for objects of class Member
     */
    public Member(String first, String middle, String last,String bDay, String phoneNum,
                 String guestEmail, boolean isMil, boolean isGov, boolean isCheckedin,
                 String roomReserved)
    {
        super(first,middle,last,bDay,phoneNum,guestEmail,isMil,isGov, isCheckedin,roomReserved);
        membershipNumber = membershipNumber +1;
        incrementMembershipNumber();
    }
   

    public int getMembershipNumber() {
        return membershipNumber;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    private void incrementMembershipNumber()
    {
        membershipNumber = membershipNumber +1;
    }
}
