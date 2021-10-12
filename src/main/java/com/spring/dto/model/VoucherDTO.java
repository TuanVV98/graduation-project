package com.spring.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.spring.model.Voucher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

    private String id;

    @NotNull(message = "Không được để trống nội dung")
    private String content;

    private String image;

    @NotNull(message = "Không được để trống giá sale")
    private Double sale;

    @NotNull(message = "Ngày bắt đầu không để trống")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "en-US", timezone = "Brazil/East")
    private LocalDateTime start ;

    //@Pattern(
    //            regexp = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]",
    //            message = "Ngày bắt đầu không đúng định dạng"
    //    )
    @NotNull(message = "Ngày kết thúc không để trống")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "en-US", timezone = "Brazil/East")
    private LocalDateTime end ;

    private Date createAt = new Date();

    private Boolean deleteAt;

    public Voucher convertDTOToEntity() {
        return new ModelMapper().map(this, Voucher.class);
    }

}
