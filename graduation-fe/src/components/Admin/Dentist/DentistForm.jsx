import React, {useState} from "react";
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import SaveIcon from '@mui/icons-material/Save';
import Button from '@mui/material/Button';
import ImageIcon from '@mui/icons-material/Image';
import CloseIcon from '@mui/icons-material/Close';
import Select from "react-select";
import {
  CardBody,
  FormGroup,
  Form,
  Input,
  Row,
  Col
} from "reactstrap";


const DentistForm = () => {

    const city  = [
        { value: '1', label: 'Hà Nội' },
        { value: '2', label: 'Bắc Giang' },
        { value: '3', label: 'Bắc Ninh' } 
    ]

    const district = [
        { value: '1', label: 'Nam Từ Liêm' },
        { value: '2', label: 'Cầu Giấy' },
        { value: '3', label: 'Thanh Xuân' }
    ]

    const ward = [
        { value: '1', label: 'Đồng Tân' },
        { value: '2', label: 'Hiệp Hoà' },
        { value: '3', label: 'Bắc Giang' }
    ]

    return(
        <CardBody>
            <Form>
                <Row>
                    <Col className="px-1" md="6">
                        <FormGroup>
                        <label htmlFor="exampleInputEmail1">
                            Email
                        </label>
                        <Input placeholder="Email@gmail.com" type="email" />
                        </FormGroup>
                    </Col>
                    <Col className="px-1" md="6">
                        <label>Tỉnh/Thành phố</label>
                        <FormGroup>
                            <Select 
                                placeholder="Hà Nội"
                                options={city}
                            />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col className="px-1" md="6">
                        <FormGroup>
                        <label htmlFor="exampleInputEmail1">
                            CCCD/CMT
                        </label>
                        <Input placeholder="" type="text" />
                        </FormGroup>
                    </Col>
                    <Col className="px-1" md="6">
                        <label>Quận/Huyện</label>
                        <FormGroup>
                            <Select 
                                placeholder="Nam Từ Liêm"
                                options={district}
                            />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col className="px-1" md="6">
                        <FormGroup>
                        <label htmlFor="exampleInputEmail1">
                            Họ tên
                        </label>
                        <Input placeholder="" type="text" />
                        </FormGroup>
                    </Col>
                    <Col className="px-1" md="6">
                        <label>Phường/Xã</label>
                        <FormGroup>
                            <Select 
                                placeholder="Phương Canh"
                                options={ward}
                            />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col className="px-1" md="6">
                        <FormGroup>
                        <label>Số điện thoại</label>
                        <Input
                            placeholder="Phone number"
                            type="text"
                        />
                        </FormGroup>
                    </Col>
                    <Col className="px-1" md="6">
                        <FormGroup>
                        <label>Chọn tệp</label>
                        <br />
                        <label><ImageIcon />Ảnh</label>
                        <Input
                            type="file"
                        />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col className="px-1" md="6">
                        <FormGroup>
                        <label>Ngày sinh</label>
                        <Input
                            defaultValue={new Date}
                            placeholder="Home Address"
                            type="date"
                        />
                        </FormGroup>
                    </Col>
                    <Col className="px-1" md="6">
                        <FormGroup>
                        <label>Kinh nghiệm</label>
                        <div className="form-floating">
                            <textarea className="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style={{height: '100px', borderRadius: '10px', border: '1px solid #CCCCCC'}}></textarea>
                        </div>
                        </FormGroup>
                    </Col>
                </Row>
               
                <Row>
                    <Col className="px-1" md="6">
                        <FormGroup>
                        <label>Giới tính</label>
                        <br/>

                        <label>Nam</label>
                        <Input name="gender" style={{marginLeft: '0.2rem'}}
                            type="radio"
                        />
                        &emsp; &emsp;
                        <label style={{marginLeft: '0.8rem'}}>Nữ</label>
                        <Input name="gender" style={{marginLeft: '0.2rem'}}
                            type="radio"
                        />
                        </FormGroup>
                    </Col>
                </Row>

                <Row style={{marginTop: '1%'}}>
                    <Col className="px-1" md="12">
                        <Button variant="contained">
                            <SaveIcon />
                            Lưu
                        </Button>
                        <Button variant="contained" color="inherit" style={{marginLeft: '1em'}}>
                            <CloseIcon />
                            Huỷ
                        </Button>
                    </Col>
                </Row>
            </Form> 
            
        </CardBody>
        
    )
}

export default DentistForm;

