package com.spring.service.voucher;

import com.spring.dto.model.VoucherDTO;
import com.spring.exception.NotFoundException;
import com.spring.model.Voucher;
import com.spring.repository.VoucherRepository;
import com.spring.service.email.MailServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;

import javax.mail.MessagingException;
import javax.xml.bind.DatatypeConverter;

@Service
public class VoucherServiceImpl implements VoucherService {

	private final VoucherRepository voucherRepository;

	@Autowired
	public VoucherServiceImpl(VoucherRepository voucherRepository) {
		this.voucherRepository = voucherRepository;
	}

	@Autowired
	MailServices mailServices;

	@Override
	public VoucherDTO save(VoucherDTO dto) {

		return this.voucherRepository.save(dto.convertDTOToEntity()).convertEntityToDTO();
	}
	
	public String createVoucher(int id){
		try {
			return RandomStringUtils.randomAlphanumeric(5) + new Date().getTime() + id +RandomStringUtils.randomAlphanumeric(5);
		} catch (Exception e) {
			return null;
		}
	}
	
	public VoucherDTO sentVoucher(int id, String EmailNhan) {
		VoucherDTO v = null;
		try {
			if (voucherRepository.getCountBooking(id) == 20) {
				String maVoucher = createVoucher(id);
				v = new VoucherDTO(maVoucher, "CHI ÂN KHÁCH HÀNG", "ẢNH", 50.0, LocalDateTime.now(), LocalDateTime.now(), new Date(), false);
				if(save(v) != null) {
					mailServices.push(EmailNhan, v.getContent(), "Mã Voucher: "+v.getId());
				}
			}
		} catch (Exception e) {
			v = null;
		}
		
		return v;
	}

	public VoucherDTO test(VoucherDTO dto) {
		Calendar c1 = Calendar.getInstance();
		c1.roll(Calendar.DATE, 30);
		Date d = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//    	dto.setEnd(new Date(dateFormat.format(c1.getTime())));
		System.out.println(c1);
		return dto;
	}
//    insert into voucher (id,content,image,sale,start,end,create_at,delete_at) 
//    values ('abcde12345','chi an khach hang','image','50','2021-10-10','2020-10-10','2020-10-10',0);

	@Override
	public VoucherDTO update(VoucherDTO dto) {
		return this.voucherRepository.save(dto.convertDTOToEntity()).convertEntityToDTO();
	}

	@Override
	public List<VoucherDTO> findByTitle(String title) {
		List<VoucherDTO> itemDTO = new ArrayList<>();
		this.voucherRepository.findByContent(title).forEach(t -> itemDTO.add(t.convertEntityToDTO()));
		return itemDTO;

	}

	@Override
	public Optional<VoucherDTO> findById(String id) {

		Optional<Voucher> voucher = this.voucherRepository.findById(id);
		if (voucher.isPresent()) {
			return voucher.map(Voucher::convertEntityToDTO);
		}
		return Optional.empty();
	}

	@Override
	public List<VoucherDTO> findAll() {
		List<VoucherDTO> itemDTO = new ArrayList<>();
		this.voucherRepository.findAll().forEach(t -> itemDTO.add(t.convertEntityToDTO()));
		return itemDTO;
	}

	@Override
	public List<Voucher> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
		return this.voucherRepository.findAllByStartGreaterThanEqualAndStartLessThanEqual(startDate, endDate);
	}

	@Override
	public void hardDelete(String id) throws NotFoundException {
		Voucher entity = this.voucherRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Voucher not found with :" + id));
		this.voucherRepository.delete(entity);
	}
}
