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


const AccountForm = () => {

    const options = [
        { value: 'admin', label: 'Quản trị viên' },
        { value: 'dentist', label: 'Nha sỹ' },
        { value: 'user', label: 'Lễ tân' },
        { value: 'guest', label: 'Khách hàng' }
    ]

    return(
        <CardBody>
            <Form>
                <Row>
                    <Col className="px-1" md="12">
                        <FormGroup>
                        <label htmlFor="exampleInputEmail1">
                            Email
                        </label>
                        <Input placeholder="Email@gmail.com" type="email" />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col className="px-1" md="12">
                        <FormGroup>
                        <label>
                            Mật khẩu
                        </label>
                        <Input type="text" />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col className="px-1" md="12">
                        <FormGroup>
                        <label >
                            Xác nhận mật khẩu
                        </label>
                        <Input type="text" />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col className="px-1" md="12">
                        <FormGroup>
                        <label >
                            Số điện thoại
                        </label>
                        <Input type="text" />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <Col className="px-1" md="12">
                        <label>Phân quyền</label>
                        <FormGroup>
                            <Select
                                placeholder="Khách hàng"
                                options={options}
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

export default AccountForm;

