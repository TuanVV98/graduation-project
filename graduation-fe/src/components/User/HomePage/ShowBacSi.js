import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
export default function ShowBacSi (){

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
        <div className="text-center pt-4 mt-5" style={{backgroundColor: 'rgb(246, 249, 249)',height:'700px'}}> 
            <h1 className={classes.title}>Các chuyên gia nha khoa của <span className={classes.span}>Smile Dental</span></h1>
            <p>Nụ cười làm tăng giá trị khuôn mặt của bạn!</p>
            <br/><br/>
            <div className="card-group mx-5">
            <div className="card ms-3">
                <img src="https://mdbootstrap.com/img/new/fluid/nature/018.jpg" className="card-img-top" alt="..."/>
                <div className="card-body">
                <h5 className="card-title">Card title</h5>
                <p className="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                </div>
            </div>
            <div className="card ms-3">
                <img src="https://mdbootstrap.com/img/new/fluid/nature/018.jpg" className="card-img-top" alt="..."/>
                <div className="card-body">
                <h5 className="card-title">Card title</h5>
                <p className="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
                <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                </div>
            </div>
            <div className="card ms-3">
                <img src="https://mdbootstrap.com/img/new/fluid/nature/018.jpg" className="card-img-top" alt="..."/>
                <div className="card-body">
                <h5 className="card-title">Card title</h5>
                <p className="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>
                <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                </div>
            </div>
            <div className="card ms-3">
                <img src="https://mdbootstrap.com/img/new/fluid/nature/018.jpg" className="card-img-top" alt="..."/>
                <div className="card-body">
                <h5 className="card-title">Card title</h5>
                <p className="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>
                <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                </div>
            </div>
            </div>

        </div>
    )
}