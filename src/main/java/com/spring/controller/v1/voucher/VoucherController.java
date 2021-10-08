package com.spring.controller.v1.voucher;

import com.spring.dto.model.PostDTO;
import com.spring.dto.model.VoucherDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.exception.NotParsableContentException;
import com.spring.model.Voucher;
import com.spring.service.voucher.VoucherService;
import com.spring.utils.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/posts")
public class VoucherController {

//    private VoucherService voucherService;
//
//    @Autowired
//    public VoucherController(VoucherService voucherService) {
//        this.voucherService = voucherService;
//    }
//
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Response<VoucherDTO>> create(
//            @RequestPart("properties") @Valid VoucherDTO dto,
//            @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,
//            BindingResult result
//    ) throws NotParsableContentException {
//
//        Response<VoucherDTO> response = new Response<>();
//
//        if (result.hasErrors()) {
//            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
//            return ResponseEntity.badRequest().body(response);
//        }
//
//        if (!ApiUtil.isEndDateGreaterThanStartDate(dto.getStart(), dto.getEnd())) {
//            throw new NotParsableContentException("Ngày bắt đầu voucher lớn hơn nhày kết thúc voucher");
//        }
//
//        response.setData(this.voucherService.save(dto));
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//
//    @PutMapping(value = "/{id}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Response<VoucherDTO>> updateWithFile(
//            @RequestPart("properties") @Valid VoucherDTO dto,
//            @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,
//            BindingResult result
//    ) throws NotParsableContentException {
//
//        Response<VoucherDTO> response = new Response<>();
//
//        if (result.hasErrors()) {
//            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
//            return ResponseEntity.badRequest().body(response);
//        }
//
//        if (!ApiUtil.isEndDateGreaterThanStartDate(dto.getStart(), dto.getEnd())) {
//            throw new NotParsableContentException("Ngày bắt đầu voucher lớn hơn nhày kết thúc voucher");
//        }
//
//        response.setData(this.voucherService.update(dto));
//
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Response<VoucherDTO>> update(
//            @Valid @RequestBody VoucherDTO dto,
//            BindingResult result
//    ) throws NotParsableContentException {
//
//        Response<VoucherDTO> response = new Response<>();
//
//        if (result.hasErrors()) {
//            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//
//        if (!ApiUtil.isEndDateGreaterThanStartDate(dto.getStart(), dto.getEnd())) {
//            throw new NotParsableContentException("Ngày bắt đầu voucher lớn hơn nhày kết thúc voucher");
//        }
//        response.setData(this.voucherService.update(dto));
//
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//    @GetMapping
//    public ResponseEntity<Response<List<VoucherDTO>>> findAllBetweenDates(
//            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
//            @PageableDefault(page = 1, size = 10, sort = {"id"}) Pageable pageable
//    ) throws NotFoundException {
//
//        Response<List<VoucherDTO>> response = new Response<>();
//
//        LocalDateTime startDateTime = ApiUtil.convertLocalDateToLocalDateTime(startDate);
//        LocalDateTime endDateTime = ApiUtil.convertLocalDateToLocalDateTime(endDate);
//
//        Page<Voucher> vouchers = this.voucherService.findBetweenDates(startDateTime, endDateTime, pageable);
//
//        if (vouchers.isEmpty()) {
//            throw new NotFoundException("Không có voucher nào đc tạo giữa startDate : "
//                    + startDateTime + " và endDate : " + endDate);
//        }
//
//        List<VoucherDTO> itemDTO = new ArrayList<>();
//
//        vouchers.stream().forEach(t -> itemDTO.add(t.convertEntityToDTO()));
//
//        response.setData(itemDTO);
//        return  ResponseEntity.status(HttpStatus.OK).body(response);
//    }

}
