package model;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class EnrollEnums {
    public enum PREFFERED_LANGUAGE{
        ENGLISH(25),POLISH(26);
        final int index;
        PREFFERED_LANGUAGE(int index){
            this.index = index;
        }
    };
    public enum PREFFERED_EGZAM_TYPE{
        PAPER("Paper"),ELECTRONIC("Electronic");
        final String type;
        PREFFERED_EGZAM_TYPE(String type){
            this.type = type;
        }};
    public enum INVOICE_TYPE{
        NONE("None"),ELECTRONIC("Electronic"),PAPER("Paper");
        final String type;
        INVOICE_TYPE(String type){
            this.type = type;
        }};
}
