import React from 'react';
import { makeStyles } from '@material-ui/core/styles';

export default function LoiKhuyen() {

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
        <div className="pt-4 mt-5 text-center" style={{height:'700px'}}>
            <h1 className={classes.title}>Lời khuyên đến từ các chuyên gia của <span className={classes.span}>Smile Dental</span></h1>
            <p>Nụ cười làm tăng giá trị khuôn mặt của bạn!</p>
            <br/><br/>
            <div className="text-start mx-5">
                <h3>Chăm sóc răng và nướu của bạn</h3>
                Các nhà nghiên cứu đã phát hiện ra rằng những người<br/>
                mắc bệnh nướu răng có nguy cơ mắc bệnh tim mạch vành cao gần gấp đôi
                <br/><br/>
                <i className='far fa-heart'></i> Đánh răng đúng cách.<br/>
                <i className='far fa-heart'></i> Bổ sung các thực phẩm chứa canxi, photpho, flo,..	<br/>
                <i className='far fa-heart'></i> Dùng các sản phẩm làm sạch răng khác nhau.<br/>
                <i className='far fa-heart'></i> Chăm sóc nướu cẩn thận.<br/>
                <i className='far fa-heart'></i> Ăn nhiều thực phẩm giàu chất xơ.<br/>
                <i className='far fa-heart'></i> Nên đi khám răng định kỳ
            </div>
            <br/><br/><br/>
            <div className="card-group mx-5">
            <div className="card ms-3 p-3">
                <i className='fas fa-user-md' style={{fontSize: '50px'}} ></i>
                <div className="card-body">
                <h5 className="card-title">100</h5>
                <p className="card-text">Bác sĩ tận tâm.</p>
                </div>
            </div>
            <div className="card ms-3 p-3">
                <i className='far fa-handshake' style={{fontSize: '50px'}} ></i>
                <div className="card-body">
                <h5 className="card-title">50000</h5>
                <p className="card-text">Khách hàng đã sử dụng dịch vụ của Smile Dental.</p>
                </div>
            </div>
            <div className="card ms-3 p-3">
                <i className='far fa-money-bill-alt' style={{fontSize: '50px'}} ></i>
                <div className="card-body">
                <h5 className="card-title">169</h5>
                <p className="card-text">Giải thưởng đã đạt được trong cuộc thi nha khoa.</p>
               </div>
            </div></div>
        </div>
    );
}