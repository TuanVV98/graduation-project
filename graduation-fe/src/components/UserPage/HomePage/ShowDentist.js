import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import axios from 'axios';

export default function ShowDentist (){

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

    const [listTopBS, setListTopBS] = useState([]);

    useEffect(()=>{
        let top = "4";
        const url=`http://localhost:8080/api/v1/dentists/top/${top}`;
        axios({
            url: url,
            method:'GET',
        })
            .then((response)=>{
                const{data}=response;
                setListTopBS(data.data);
                
            })
            .catch((error)=>{
                console.log(error,error.response);
            });
    },[]);

    const srcUrl = "http://localhost:8080/api/v1/files/download/image?filename=";


    return(
        <div className="text-center pt-4 mt-5" style={{backgroundColor: 'rgb(246, 249, 249)',minHeight:'700px'}}> 
            <h1 className={classes.title}>Các chuyên gia nha khoa của <span className={classes.span}>Smile Dental</span></h1>
            <p>Nụ cười làm tăng giá trị khuôn mặt của bạn!</p>
            <br/><br/>
            <div className="card-group mx-5">
            {
                    listTopBS.map((val,idx)=>{
                                return(
                        <div className="card ms-3" key={idx}>
                            <img src={srcUrl + val.image} className="card-img-top" height="310px" alt="..."/>
                            <div className="card-body">
                            <h5 className="card-title">{val.fullName}</h5>
                            <p className="card-text">Ngày sinh: {val.birthday}.</p>
                            <p className="card-text">Email: {val.accounts.email}</p>
                            </div>
                        </div>
                    )
                })
            }
            </div>

        </div>
    )
}