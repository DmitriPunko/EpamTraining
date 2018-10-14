package com.epam.auction.model.dto;

import com.epam.auction.model.Lot;
import com.epam.auction.model.LotPhoto;

import java.util.List;
import java.util.Objects;

public class LotDto {

    private Lot lot;
    private List<LotPhoto> photos;

    public LotDto() {
    }

    public LotDto(Lot lot, List<LotPhoto> photos) {
        this.lot = lot;
        this.photos = photos;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public List<LotPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<LotPhoto> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LotDto lotDTO = (LotDto) o;
        return Objects.equals(getLot(), lotDTO.getLot()) &&
                Objects.equals(getPhotos(), lotDTO.getPhotos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLot(), getPhotos());
    }
}
