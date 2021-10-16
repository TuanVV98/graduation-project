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


const VoucherForm = () => {

    const options = [
        { value: '1', label: '15%' },
        { value: '2', label: '35%' },
        { value: '3', label: '55%' }
    ]

    return(
        <CardBody>
            <Form>
                <Row>
                    <Col className="px-1" md="12">
                        <FormGroup>
                        <label>Nội dung</label>
                        <div className="form-floating">
                            <textarea className="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style={{height: '100px', borderRadius: '10px', border: '1px solid #CCCCCC'}}></textarea>
                        </div>
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col className="px-1" md="12">
                        <label>Giảm giá</label>
                        <FormGroup>
                            <Select 
                                placeholder="15%"
                                options={options}
                            />
                        </FormGroup>
                    </Col>
                </Row>
                <Row style={{marginTop: '1%'}}>
                    <Col className="px-1" md="12">
                        <FormGroup>
                        <label><ImageIcon />Ảnh/Video</label>
                        <Input
                            type="file"
                        />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col className="px-1" md="12">
                        <FormGroup>
                        <label>Ngày bắt đầu</label>
                        <Input
                            defaultValue={new Date}
                            placeholder="Home Address"
                            type="date"
                        />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col className="px-1" md="12">
                        <FormGroup>
                        <label>Ngày kết thúc</label>
                        <Input
                            defaultValue={new Date}
                            placeholder="Home Address"
                            type="date"
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

export default VoucherForm;

