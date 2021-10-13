import React, {useState} from "react";
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import SaveIcon from '@mui/icons-material/Save';
import Button from '@mui/material/Button';
import ImageIcon from '@mui/icons-material/Image';
import CloseIcon from '@mui/icons-material/Close';

import {
  CardBody,
  FormGroup,
  Form,
  Input,
  Row,
  Col
} from "reactstrap";

const PostForm = () => {

    return(
        <CardBody>
                <Form>
                  <Row>
                    <Col className="px-1" md="12">
                      <FormGroup>
                        <label>Tiêu đề</label>
                        <Input
                          type="text"
                        />
                      </FormGroup>
                    </Col>
                  </Row>
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

export default PostForm;

