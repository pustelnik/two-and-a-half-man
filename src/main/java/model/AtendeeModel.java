package model;

import static model.EnrollEnums.*;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class AtendeeModel {
    private EGZAM_PRODUCT egzamProduct;
    private EGZAM_LEVEL egzamLevel;

    private PREFFERED_LANGUAGE prefferedLanguage = PREFFERED_LANGUAGE.POLISH;
    private PREFFERED_EGZAM_TYPE prefferedEgzamType = PREFFERED_EGZAM_TYPE.PAPER;

    private String ownedCertificateNumber;
    private String ownedCertificateIssueDate;
    private String ownedCertificateIssuedBy;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private String certificateFirstName;
    private String certificateLastName;
    private String zipCode;
    private String city;
    private String address;
    private String additionalDetails;

    private INVOICE_TYPE invoice= INVOICE_TYPE.NONE;

    private boolean acceptLegalPolicy = true;
    private boolean acceptMarketingPolicy;

}
