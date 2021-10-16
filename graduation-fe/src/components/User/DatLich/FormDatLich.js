import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';

export default function FormDatLich(){
    const useStyles = makeStyles((theme) => ({
        title: {
            fontSize : '2.2em',
            marginBottom: '0.2em',
            color: '#1c1c1c',
            fontWeight: 300,
            fontFamily: 'Roboto'
        },
        span:{
            color: '#00BCD5',
            fontWeight: 'bold'
        }

    })); 
    const classes = useStyles();


    return(
        <div className="pt-4 mt-5 text-center" style={{backgroundColor: 'rgb(246, 249, 249)',height:'700px'}}> 
            <h1 className={classes.title}>Đặt lịch hẹn với nha sĩ <span className={classes.span}>Smile Dental</span></h1>
            <br/><br/>
            <form>
                <div className="row text-start">
                <div className="col-sm-3 offset-2"> 

                    <div>
                        <label htmlFor="exampleFormControlInput1" className="form-label">Họ và tên:</label>
                        <input type="email" className="form-control" id="exampleFormControlInput1" placeholder="Nguyen Van An" readOnly/>
                    </div><br/>

                    <div>
                    <label htmlFor="exampleFormControlInput1" className="form-label">Dịch vụ:</label>
                    <select className="form-select" aria-label="Default select example">
                        <option defaultChecked>Chọn dịch vụ</option>
                        <option value="1">Niềng răng</option>
                        <option value="2">Chấp răng</option>
                        <option value="3">...</option>
                    </select>
                    </div><br/>

                    <div>
                    <label htmlFor="exampleFormControlInput1" className="form-label">Bác sĩ:</label>
                    <select className="form-select" aria-label="Default select example">
                        <option defaultChecked>Chọn bác sĩ</option>
                        <option value="1">Trung Kiên</option>
                        <option value="2">Thành Đạt</option>
                        <option value="3">...</option>
                    </select>
                    </div><br/>

                    <div>
                        <label htmlFor="exampleFormControlTextarea1" className="form-label">Ghi chú(mô tả tình trạng của bạn):</label>
                        <textarea className="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                    </div><br/>

                </div>
                
                <div className="col-sm-3 offset-2">

                <div>
                    <label htmlFor="date" className="form-label mt-1">Chọn ngày:</label><br/>
                    <TextField
                        className=""
                        id="date"
                        type="date"
                        defaultValue="2017-05-24"
                    />
                </div><br/>

                <div>
                    <label htmlFor="time" className="form-label">Thời gian:</label>
                    <select className="form-select" aria-label="Default select example" id="time">
                        <option value="1" defaultChecked>07:00 - 12:00</option>
                        <option value="2">01:00 - 5:30</option>
                        <option value="3">...</option>
                    </select>
                    </div><br/>

                <div>
                    <label htmlFor="exampleFormControlInput1" className="form-label">Nhập voucher(nếu có):</label>
                    <input type="email" className="form-control" id="exampleFormControlInput1" placeholder="VOUCHER12A1"/>
                    </div>
                </div> 
                </div>
                <br/>
                <div>
                    <button type="button" className="btn btn-primary">Đặt lịch</button>
                </div>
            </form>

            </div>
       
    )
}