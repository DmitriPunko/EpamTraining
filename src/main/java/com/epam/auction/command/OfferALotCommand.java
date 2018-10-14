package com.epam.auction.command;

import com.epam.auction.exception.ServiceException;
import com.epam.auction.model.AuctionTypeEnum;
import com.epam.auction.model.ColorEnum;
import com.epam.auction.model.Lot;
import com.epam.auction.model.LotPhoto;
import com.epam.auction.model.dto.LotDto;
import com.epam.auction.service.LotDtoService;
import com.epam.auction.service.LotPhotoService;
import com.epam.auction.service.LotService;
import com.epam.auction.util.DateTimeParser;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfferALotCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(OfferALotCommand.class.getName());

    private static final String ID = "id";
    private static final String LOT_DTO_LIST = "lotDtoList";

    private static final String LOT_PHOTO_DIRECTORY = "/img/lot/";
    private static final String SAVE_PATH = "E:\\EPAM\\Training\\Projects\\FinalProject\\auction\\src\\main\\webapp\\img\\lot\\";

    private static final int HOUR_IN_MILLIS = 3600000;
    private static final String JAVAX_SERVLET_CONTEXT_TEMPDIR = "javax.servlet.context.tempdir";

    private static final String MAIN_PAGE = "/WEB-INF/main.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException {

        Lot lot = new Lot();
        List<String> lotPhotosPaths = new ArrayList<>();

        HttpSession session = request.getSession();
        long id = (Long) session.getAttribute(ID);
        lot.setOwnerId(id);

        try {
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletContext servletContext = request.getServletContext();
            File repository = (File) servletContext.getAttribute(JAVAX_SERVLET_CONTEXT_TEMPDIR);
            fileItemFactory.setRepository(repository);

            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> items = servletFileUpload.parseRequest(request);

            for (FileItem item : items) {
                if (item.isFormField()) {
                    buildLotParams(item, lot);
                } else {
                    uploadLotPhoto(item, lotPhotosPaths);
                }
            }
        } catch (FileUploadException e) {
            LOGGER.error(e.getMessage(), e);
        }

        LotService lotService = new LotService();
        long idLot = lotService.save(lot);

        List<LotPhoto> lotPhotos = buildLotPhotos(lotPhotosPaths, idLot);

        LotPhotoService lotPhotoService = new LotPhotoService();
        for (LotPhoto lotPhoto : lotPhotos) {
            lotPhotoService.saveLotPhoto(lotPhoto);
        }

        LotDtoService lotDtoService = new LotDtoService();
        List<LotDto> lotDtoList = lotDtoService.findAllActive();
        request.setAttribute(LOT_DTO_LIST, lotDtoList);

        return MAIN_PAGE;
    }

    private void buildLotParams(FileItem item, Lot lot) {

        String paramName = item.getFieldName();
        String paramValue = item.getString();

        switch (paramName) {
            case Lot.PRICE:
                BigDecimal price = new BigDecimal(paramValue);
                lot.setPrice(price);
                break;
            case Lot.DATE_OF_START:
                Date dateOfStart = DateTimeParser.parse(paramValue);
                lot.setDateOfStart(dateOfStart);

                long dateOfStartMillis = dateOfStart.getTime();
                long dateOfEndMillis = dateOfStartMillis + HOUR_IN_MILLIS;
                Date dateOfEnd = new Date(dateOfEndMillis);
                lot.setDateOfEnd(dateOfEnd);
                break;
            case Lot.BRAND:
                lot.setBrand(paramValue);
                break;
            case Lot.MODEL:
                lot.setModel(paramValue);
                break;
            case Lot.CLASS:
                lot.setClassOfLot(paramValue);
                break;
            case Lot.YEAR_OF_ISSUE:
                int year = Integer.parseInt(paramValue);
                lot.setYearOfIssue(year);
                break;
            case Lot.COLOR:
                String color = paramValue.toUpperCase();
                ColorEnum colorEnum = ColorEnum.valueOf(color);
                lot.setColorEnum(colorEnum);
                break;
            case Lot.ENGINE_VOLUME:
                BigDecimal engineVolume = new BigDecimal(paramValue);
                lot.setEngineVolume(engineVolume);
                break;
            case Lot.IS_DAMAGED:
                boolean isDamaged = Boolean.valueOf(paramValue);
                lot.setDamaged(isDamaged);
                break;
            case Lot.AUCTION_TYPE:
                String auctionTypeString = paramValue.toUpperCase();
                AuctionTypeEnum auctionType = AuctionTypeEnum.valueOf(auctionTypeString);
                lot.setAuctionType(auctionType);
                break;
            default:
                throw new IllegalArgumentException("Unknown type of parameter!");
        }
    }

    private void uploadLotPhoto(FileItem fileItem, List<String> photosPaths) throws ServletException {

        String fileName = fileItem.getName();
        String pathname = SAVE_PATH + fileName;

        try {
            File uploadedFile = new File(pathname);
            fileItem.write(uploadedFile);
        } catch (Exception e) {
            throw new ServletException(e.getMessage(), e);
        }

        String photoPath = LOT_PHOTO_DIRECTORY + fileName;
        photosPaths.add(photoPath);
    }

    private List<LotPhoto> buildLotPhotos(List<String> photosPaths, long lotId) {
        List<LotPhoto> lotPhotos = new ArrayList<>();
        for (String photoPath : photosPaths) {
            LotPhoto lotPhoto = new LotPhoto();

            lotPhoto.setIdLot(lotId);
            lotPhoto.setUrl(photoPath);

            lotPhotos.add(lotPhoto);
        }

        return lotPhotos;
    }

}
