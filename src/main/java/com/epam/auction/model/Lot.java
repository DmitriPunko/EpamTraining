package com.epam.auction.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Lot implements Identifiable {

    private long idLot;
    private BigDecimal price;
    private Date dateOfStart;
    private Date dateOfEnd;
    private String brand;
    private String model;
    private String classOfLot;
    private int yearOfIssue;
    private ColorEnum colorEnum;
    private BigDecimal engineVolume;
    private boolean isDamaged;
    private AuctionTypeEnum auctionType;
    private long ownerId;
    private LotStatusEnum status;

    public static final String ID = "id_lot";
    public static final String PRICE = "price";
    public static final String DATE_OF_START = "date_of_start";
    public static final String DATE_OF_END = "date_of_end";
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String CLASS = "class";
    public static final String YEAR_OF_ISSUE = "year_of_issue";
    public static final String COLOR = "color";
    public static final String ENGINE_VOLUME = "engine_volume";
    public static final String IS_DAMAGED = "is_damaged";
    public static final String AUCTION_TYPE = "auction_type";
    public static final String OWNER_ID = "owner_id";
    public static final String STATUS = "status";

    public Lot() {
    }

    public Lot(long idLot, BigDecimal price, Date dateOfStart, Date dateOfEnd, String brand, String model,
               String classOfLot, int yearOfIssue, ColorEnum colorEnum, BigDecimal engineVolume, boolean isDamaged,
               AuctionTypeEnum auctionType, long ownerId, LotStatusEnum status) {
        this.idLot = idLot;
        this.price = price;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.brand = brand;
        this.model = model;
        this.classOfLot = classOfLot;
        this.yearOfIssue = yearOfIssue;
        this.colorEnum = colorEnum;
        this.engineVolume = engineVolume;
        this.isDamaged = isDamaged;
        this.auctionType = auctionType;
        this.ownerId = ownerId;
        this.status = status;
    }

    public long getIdLot() {
        return idLot;
    }

    public void setIdLot(long idLot) {
        this.idLot = idLot;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public Date getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getClassOfLot() {
        return classOfLot;
    }

    public void setClassOfLot(String classOfLot) {
        this.classOfLot = classOfLot;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public ColorEnum getColorEnum() {
        return colorEnum;
    }

    public void setColorEnum(ColorEnum colorEnum) {
        this.colorEnum = colorEnum;
    }

    public BigDecimal getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(BigDecimal engineVolume) {
        this.engineVolume = engineVolume;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public void setDamaged(boolean damaged) {
        isDamaged = damaged;
    }

    public AuctionTypeEnum getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(AuctionTypeEnum auctionType) {
        this.auctionType = auctionType;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public LotStatusEnum getStatus() {
        return status;
    }

    public void setStatus(LotStatusEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lot lot = (Lot) o;
        return getIdLot() == lot.getIdLot() &&
                getYearOfIssue() == lot.getYearOfIssue() &&
                isDamaged() == lot.isDamaged() &&
                getOwnerId() == lot.getOwnerId() &&
                Objects.equals(getPrice(), lot.getPrice()) &&
                Objects.equals(getDateOfStart(), lot.getDateOfStart()) &&
                Objects.equals(getDateOfEnd(), lot.getDateOfEnd()) &&
                Objects.equals(getBrand(), lot.getBrand()) &&
                Objects.equals(getModel(), lot.getModel()) &&
                Objects.equals(getClassOfLot(), lot.getClassOfLot()) &&
                getColorEnum() == lot.getColorEnum() &&
                Objects.equals(getEngineVolume(), lot.getEngineVolume()) &&
                getAuctionType() == lot.getAuctionType() &&
                getStatus() == lot.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdLot(), getPrice(), getDateOfStart(), getDateOfEnd(), getBrand(), getModel(), getClassOfLot(), getYearOfIssue(), getColorEnum(), getEngineVolume(), isDamaged(), getAuctionType(), getOwnerId(), getStatus());
    }

    @Override
    public long getId() {
        return idLot;
    }
}
