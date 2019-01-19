
/**
 * Enumeration class Status. A reservation object can have different statuses: 
 * WAITING= reservation is made, but guest not yet in hotel
 * IN= reservation exists, guest 'checked in' to hotel
 * OUT= reservation close, guest 'checked out' of hotel
 * CANCELED= reservation was made, but canceled.
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 01/17/2019
 */
public enum Status
{
    WAITING, IN, OUT, CANCELED
}

