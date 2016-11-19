package model;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import steps.session.SessionBuilder;

import java.util.List;

import static model.EnrollEnums.*;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class AtendeeModel {
    private EGZAM_PRODUCT egzamProduct;
    private EGZAM_LEVEL egzamLevel;

    private PREFFERED_LANGUAGE prefferedLanguage = PREFFERED_LANGUAGE.POLISH;
    private PREFFERED_EGZAM_TYPE prefferedExamType = PREFFERED_EGZAM_TYPE.PAPER;

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

    private INVOICE_TYPE invoice = INVOICE_TYPE.NONE;

    private boolean acceptLegalPolicy = true;
    private boolean acceptMarketingPolicy;


    public AtendeeModel(int  attendeeID) {
        Config conf = ConfigFactory.load();
        List<? extends Config> configList = conf.getConfigList("test.attendees");
        if(configList.size() < attendeeID) {
            throw new NotExistingAttendeeConfig();
        }
        Config configData = configList.get(attendeeID-1);

        //todo problem - jak nie ma pola w configu to leci exception
        setPrefferedLanguage(configData.getInt("prefferedLanguage")).
        setPrefferedExamType(configData.getString("prefferedExamType")).
        setOwnedCertificateNumber(configData.getString("ownedCertificateNumber")).
        setOwnedCertificateIssueDate(configData.getString("ownedCertificateIssueDate")).
        setOwnedCertificateIssuedBy(configData.getString("ownedCertificateIssueby")).
        setFirstName(configData.getString("firstName")).
        setLastName(configData.getString("lastName")).
        setAddress(configData.getString("address")).
        setZipCode(configData.getString("zipCode")).
        setCity(configData.getString("city")).
        setEmail(configData.getString("email")).
        setPhoneNumber(configData.getString("phoneNumber")).
        setCertificateFirstName(configData.getString("certificateFirstName")).
        setCertificateLastName(configData.getString("certificateLastName")).
        setAdditionalDetails(configData.getString("additionalDetails")).
        setInvoice(configData.getString("invoice")).
        setAcceptLegalPolicy(configData.getBoolean("acceptLegalPolicy")).
        setAcceptMarketingPolicy(configData.getBoolean("acceptMarketingPolicy"));
    }

    /*public AtendeeModel setExamProduct(EGZAM_PRODUCT egzamProduct) {
        this.egzamProduct = egzamProduct;
        return this;
    }

    public AtendeeModel setExamLevel(EGZAM_LEVEL egzamLevel) {
        this.egzamLevel = egzamLevel;
        return this;
    }*/

    public EGZAM_PRODUCT getExamProduct() {
        return egzamProduct;
    }

    public EGZAM_LEVEL getExamLevel() {
        return egzamLevel;
    }

    public PREFFERED_LANGUAGE getPrefferedLanguage() {
        return prefferedLanguage;
    }

    public AtendeeModel setPrefferedLanguage(int prefferedLanguage) {
        this.prefferedLanguage = PREFFERED_LANGUAGE.getValue(prefferedLanguage);
        return this;
    }

    public AtendeeModel setPrefferedLanguage(PREFFERED_LANGUAGE prefferedLanguage) {
        this.prefferedLanguage = prefferedLanguage;
        return this;
    }

    public PREFFERED_EGZAM_TYPE getPrefferedExamType() {
        return prefferedExamType;
    }

    public AtendeeModel setPrefferedExamType(String prefferedExamType) {
        this.prefferedExamType = PREFFERED_EGZAM_TYPE.getValue(prefferedExamType);
        return this;
    }

    public AtendeeModel setPrefferedExamType(PREFFERED_EGZAM_TYPE prefferedExamType) {
        this.prefferedExamType = prefferedExamType;
        return this;
    }

    public String getOwnedCertificateNumber() {
        return ownedCertificateNumber;
    }

    public AtendeeModel setOwnedCertificateNumber(String ownedCertificateNumber) {
        this.ownedCertificateNumber = ownedCertificateNumber;
        return this;
    }

    public String getOwnedCertificateIssueDate() {
        return ownedCertificateIssueDate;
    }

    public AtendeeModel setOwnedCertificateIssueDate(String ownedCertificateIssueDate) {
        this.ownedCertificateIssueDate = ownedCertificateIssueDate;
        return this;
    }

    public String getOwnedCertificateIssuedBy() {
        return ownedCertificateIssuedBy;
    }

    public AtendeeModel setOwnedCertificateIssuedBy(String ownedCertificateIssuedBy) {
        this.ownedCertificateIssuedBy = ownedCertificateIssuedBy;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AtendeeModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AtendeeModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AtendeeModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AtendeeModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCertificateFirstName() {
        return certificateFirstName;
    }

    public AtendeeModel setCertificateFirstName(String certificateFirstName) {
        this.certificateFirstName = certificateFirstName;
        return this;
    }

    public String getCertificateLastName() {
        return certificateLastName;
    }

    public AtendeeModel setCertificateLastName(String certificateLastName) {
        this.certificateLastName = certificateLastName;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public AtendeeModel setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AtendeeModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AtendeeModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public AtendeeModel setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
        return this;
    }

    public INVOICE_TYPE getInvoice() {
        return invoice;
    }

    public AtendeeModel setInvoice(String invoice) {
        this.invoice = INVOICE_TYPE.getValue(invoice);
        return this;
    }

    public AtendeeModel setInvoice(INVOICE_TYPE invoice) {
        this.invoice = invoice;
        return this;
    }

    public boolean isAcceptLegalPolicy() {
        return acceptLegalPolicy;
    }

    public AtendeeModel setAcceptLegalPolicy(boolean acceptLegalPolicy) {
        this.acceptLegalPolicy = acceptLegalPolicy;
        return this;
    }

    public boolean isAcceptMarketingPolicy() {
        return acceptMarketingPolicy;
    }

    public AtendeeModel setAcceptMarketingPolicy(boolean acceptMarketingPolicy) {
        this.acceptMarketingPolicy = acceptMarketingPolicy;
        return this;
    }

    private class NotExistingAttendeeConfig extends RuntimeException {
    }
}
