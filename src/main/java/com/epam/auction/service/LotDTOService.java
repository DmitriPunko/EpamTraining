package com.epam.auction.service;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.Lot;
import com.epam.auction.model.LotPhoto;
import com.epam.auction.model.dto.LotDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LotDTOService {

    public List<LotDTO> findAllActive() throws ServiceException {

        LotService lotService = new LotService();
        List<Lot> lots = lotService.findAllActive();

        List<LotDTO> lotsDTO = new ArrayList<>();

        LotPhotoService lotPhotoService = new LotPhotoService();
        findLotsPhotos(lots, lotPhotoService, lotsDTO);

        return lotsDTO;
    }

    public List<LotDTO> findAllByUserId(long id) throws ServiceException {
        LotService lotService = new LotService();
        List<Lot> lots = lotService.findAllByUserId(id);

        List<LotDTO> lotsDTO = new ArrayList<>();
        LotPhotoService lotPhotoService = new LotPhotoService();
        findLotsPhotos(lots, lotPhotoService, lotsDTO);

        return lotsDTO;
    }

    public Optional<LotDTO> findById(long id) throws ServiceException {
        LotService lotService = new LotService();
        Optional<Lot> lot = lotService.findById(id);

        LotPhotoService lotPhotoService = new LotPhotoService();
        List<LotPhoto> lotPhotos = lotPhotoService.getPhotoByLotId(id);

        LotDTO lotDTO = new LotDTO(lot.get(), lotPhotos);
        return Optional.of(lotDTO);
    }

    public List<LotDTO> findByParameters(Map<String, String> parameters) throws ServiceException {
        LotService lotService = new LotService();
        List<Lot>  lots = lotService.findByParameters(parameters);

        LotPhotoService lotPhotoService = new LotPhotoService();
        List<LotDTO> lotsDTO = new ArrayList<>();

        findLotsPhotos(lots, lotPhotoService, lotsDTO);

        return lotsDTO;
    }

    private void findLotsPhotos(List<Lot> lots, LotPhotoService lotPhotoService, List<LotDTO> lotsDTO) throws ServiceException {
        for (Lot lot : lots) {
            long lotId = lot.getId();
            List<LotPhoto> lotPhotos = lotPhotoService.getPhotoByLotId(lotId);

            LotDTO lotDTO = new LotDTO(lot, lotPhotos);
            lotsDTO.add(lotDTO);
        }
    }

}
