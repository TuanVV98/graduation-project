import React from 'react';
import { makeStyles } from '@material-ui/core/styles';

export default function ShowDichVu(){
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
            <div className="text-center pt-4 mt-5" style={{height:'700px'}}> 
            <h1 className={classes.title}>Các dịch vụ của <span className={classes.span}>Smile Dental</span></h1>
            <p>Nụ cười làm tăng giá trị khuôn mặt của bạn!</p>
            <br/><br/>
            <div className="row row-cols-1 row-cols-md-3 g-4 mx-5">
            <div className="col">
                <div className="card">
                <div className="card-body">
                    <img src="http://mauweb.monamedia.net/dental/wp-content/uploads/2019/09/special-1.jpg" className="card-img-top mb-2" style={{width:'150px',height:'80px'}} alt="..."/>
                    <h5 className="card-title">Card title</h5>
                    <p className="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                </div>
                </div>
            </div>
            <div className="col">
                <div className="card">
                <div className="card-body">
                    <img src="https://mdbootstrap.com/img/new/fluid/nature/018.jpg" className="card-img-top mb-2" style={{width:'150px',height:'80px'}} alt="..."/>
                    <h5 className="card-title">Card title</h5>
                    <p className="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                </div>
                </div>
            </div>
            <div className="col">
                <div className="card">
                <div className="card-body">
                    <img src="https://mdbootstrap.com/img/new/fluid/nature/018.jpg" className="card-img-top mb-2" style={{width:'150px',height:'80px'}} alt="..."/>
                    <h5 className="card-title">Card title</h5>
                    <p className="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                </div>
                </div>
            </div>
            <div className="col">
                <div className="card">
                <div className="card-body">
                    <img src="https://mdbootstrap.com/img/new/fluid/nature/018.jpg" className="card-img-top mb-2" style={{width:'150px',height:'80px'}} alt="..."/>
                    <h5 className="card-title">Card title</h5>
                    <p className="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                </div>
                </div>
            </div>
            <div className="col">
                <div className="card">
                <div className="card-body">
                    <img src="https://mdbootstrap.com/img/new/fluid/nature/018.jpg" className="card-img-top mb-2" style={{width:'150px',height:'80px'}} alt="..."/>
                    <h5 className="card-title">Card title</h5>
                    <p className="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                </div>
                </div>
            </div>
            <div className="col">
                <div className="card">
                <div className="card-body">
                    <img src="https://mdbootstrap.com/img/new/fluid/nature/018.jpg" className="card-img-top mb-2" style={{width:'150px',height:'80px'}} alt="..."/>
                    <h5 className="card-title">Card title</h5>
                    <p className="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                </div>
                </div>
            </div>
            </div>
            </div>
    )
}