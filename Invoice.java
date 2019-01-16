/**
 * Write a description of class Invoice here.
 *
 * @author Xingyu Liu
 * @version alpha test
 */
public class Invoice
{
    private double charges, tendered, extracharges, discount; 
    //private String dateCreated, invoiceNumber;
    private boolean paid;
    private String guest;
    //private int length;
    private int invoiceNumber = 100000;
    /**
     * Constructor for objects of class Invoice
     * 
     * @param reservation the reservation which will get the invoice.
     */
    public Invoice(Reservation reservation)
    {
        //dateCreated = reservation.getCheckinDate();
        guest = reservation.getReserver();
        charges = reservation.getRoom().getPrice();
        //invoiceNumber = dateCreated + toString();
        discount = 1.0;
        //length = reservation.getLength();
        paid = false;
        incrementor();
    }

    /**
     * Set the extracharges for the invoice.
     *
     * @param  charge the amount of the extracharge
     */
    public void setExtraCharge(double charge)
    {
        extracharges = charge;
    }
    
    /**
     * get the extracharges for the invoice.
     * 
     * @return the amount of the extracharges
     */
    public double getExtraCharge()
    {
        return extracharges;
    }
    
    /**
     * get the date created for the invoice.
     * 
     * @return the date created for the invoice.
     */    
    //public String getDate()
    //{
    //    return dateCreated;
    //}
    
    /**
     * get the invoice number for the invoice.
     * 
     * @return the invoice number for the invoice.
     */    
    //public String getInvoiceNumber()
    //{
    //    return invoiceNumber;
    //}

    /**
     * Pay the bill.
     */
    public void payBill(){
        paid = true;
    }
    
    /**
     * check if the bill is paid
     * 
     * @return if the bill is paid
     */
    public boolean isPaid(){
        return paid;
    }
    
    /**
     * get the discount of the invoice.
     * 
     * @return the discount of the invoice.
     */
    public double getDiscount(){
        return discount;
    }
    
    /**
     * set the discount of the invoice.
     * 
     * @param discount discount of the invoice.
     */
    public void setDiscount(){
        if(guest.isGovernment()){
            discount = 0.85;
        }
        else if(guest.isMilitary()){
            discount = 0.93;
        }
    }
    
    /**
     * get the tendered of the invoice.
     * 
     * @return tendered of the invoice.
     */
    public double getTendered(){
        return tendered;
    }
    
    /**
     * set the tendered of the invoice.
     * 
     * @param tendered tendered of the invoice.
     */
    public void setTendered(double tendered){
        this.tendered = tendered;
    }
    
    /**
     * get the final invoice.
     * 
     * @return the final invoice
     */
    public double getInvoice(){
        return charges * discount + extracharges - tendered;
    }
    
    private void incrementor() {
        invoiceNumber++;
    }
}

