import java.util.Date;

public class Cliente {
    private Integer age;
    private String job;
    private String marital;
    private String education;
    private Boolean defaultCredit;
    private Double balance;
    private Boolean housing;
    private Boolean loan;
    private String contact;
    private Integer day;
    private String month;
    private Integer duration;
    private Integer campaign;
    private Integer pDays;
    private Integer previous;
    private String pOutcome;
    private Boolean y;

    public Integer getAge() {
        return age;
    }

    public Double getAgeValue() {
        double result = 0;
        if(age < 18) result = 0;
        if(age > 18 && age < 70) result = 1;
        if(age >= 70) result = 0;
        return result;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public double getJobValue() {
        return 0;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public double getMaritalValue() {
        return 0;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public double getEducationValue() {
        return 0;
    }

    public Boolean getDefaultCredit() {
        return defaultCredit;
    }

    public double getDefaultCreditValue() {
        return defaultCredit ? 1 : 0;
    }

    public void setDefaultCredit(Boolean defaultCredit) {
        this.defaultCredit = defaultCredit;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalanceValue() {

        return balance;
    }

    public Boolean getHousing() {
        return housing;
    }

    public void setHousing(Boolean housing) {
        this.housing = housing;
    }

    public double getHousingValue() {
        return housing ? 1 : 0;
    }

    public Boolean getLoan() {
        return loan;
    }

    public void setLoan(Boolean loan) {
        this.loan = loan;
    }

    public double getLoanValue() {
        return loan ? 1 : 0;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getContactValue() {
        return 0;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getDateValue() {
        return 0;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public double getDurationValue() {
        return 0;
    }

    public Integer getCampaign() {
        return campaign;
    }

    public void setCampaign(Integer campaign) {
        this.campaign = campaign;
    }

    public double getCampaignValue() {
        return 0;
    }

    public Integer getpDays() {
        return pDays;
    }

    public void setpDays(Integer pDays) {
        this.pDays = pDays;
    }

    public double getpDaysValue() {
        return 0;
    }

    public Integer getPrevious() {
        return previous;
    }

    public void setPrevious(Integer previous) {
        this.previous = previous;
    }

    public double getPreviousValue() {
        return 0;
    }

    public String getpOutcome() {
        return pOutcome;
    }

    public void setpOutcome(String pOutcome) {
        this.pOutcome = pOutcome;
    }

    public double getpOutcomeValue() {
        return 0;
    }

    public Boolean getY() {
        return y;
    }

    public double getYValue() {
        int a = y ? 1 : 0;
        return 1;
    }

    public void setY(String y) {
        boolean result = false;
        if(y.equals("yes")) result = true;
        this.y = result;
    }
}
