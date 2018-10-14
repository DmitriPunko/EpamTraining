package com.epam.auction.service;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.Lot;
import com.epam.auction.model.LotPhoto;
import com.epam.auction.model.dto.LotDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LotDtoService {

    public List<LotDto> findAllActive() throws ServiceException {

        LotService lotService = new LotService();
        List<Lot> lots = lotService.findAllActive();

        List<LotDto> lotsDTO = new ArrayList<>();

        LotPhotoService lotPhotoService = new LotPhotoService();
        findLotsPhotos(lots, lotPhotoService, lotsDTO);

        return lotsDTO;
    }

    public List<LotDto> findAllByUserId(long id) throws ServiceException {
        LotService lotService = new LotService();
        List<Lot> lots = lotService.findAllByUserId(id);

        List<LotDto> lotsDTO = new ArrayList<>();
        LotPhotoService lotPhotoService = new LotPhotoService();
        findLotsPhotos(lots, lotPhotoService, lotsDTO);

        return lotsDTO;
    }

    public Optional<LotDto> findById(long id) throws ServiceException {
        LotService lotService = new LotService();
        Optional<Lot> lot = lotService.findById(id);

        LotPhotoService lotPhotoService = new LotPhotoService();
        List<LotPhoto> lotPhotos = lotPhotoService.getPhotoByLotId(id);

        LotDto lotDTO = new LotDto(lot.get(), lotPhotos);
        return Optional.of(lotDTO);
    }

    public List<LotDto> findByParameters(Map<String, String> parameters) throws ServiceException {
        LotService lotService = new LotService();
        List<Lot>  lots = lotService.findByParameters(parameters);

        LotPhotoService lotPhotoService = new LotPhotoService();
        List<LotDto> lotsDTO = new ArrayList<>();

        findLotsPhotos(lots, lotPhotoService, lotsDTO);

        return lotsDTO;
    }

    private void findLotsPhotos(List<Lot> lots, LotPhotoService lotPhotoService, List<LotDto> lotsDTO) throws ServiceException {
        for (Lot lot : lots) {
            long lotId = lot.getId();
            List<LotPhoto> lotPhotos = lotPhotoService.getPhotoByLotId(lotId);

            LotDto lotDTO = new LotDto(lot, lotPhotos);
            lotsDTO.add(lotDTO);
        }
    }

}
